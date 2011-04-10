package com.inpranet.indexation.regex;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.geonames.WikipediaArticle;

/**
 * Moteur de recherche d'expressions lexicales pour les lieux
 * @author Stephane
 */
public class GeographicalLexicalRegexEngine extends RegexEngine {
	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(GeographicalLexicalRegexEngine.class);
	
	/**
	 * Constructeur de la classe GeographicalLexicalRegexEngine
	 * @throws FileNotFoundException Si le fichier contenant les Regex geographiques lexicales n'a pas pu etre utilise
	 */
	public GeographicalLexicalRegexEngine() throws FileNotFoundException {
		// Initialisation des patterns et des format mappers
		// Lecture des fichiers contenant les expressions lexicales geographiques
		loadRegexLists("src/main/resources/geographicalLexicalList.txt");
	}
	
	/**
	 * Genere le texte de remplacement en fonction du format mapper
	 * @param formatMapper Le format mapper concerne
	 * @param matchedGroup Le texte source representant un lieu et qui a ete trouve par le moteur Regex
	 * @return Le texte de remplacement qui sera place dans le document source
	 */
	private String generateGeographicalReplacement(String formatMapper, String matchedGroup) {
		// Le format mapper peut indiquer une formule temporelle
		if (formatMapper.startsWith("§")) {
			// Lance une requete vers le service GeoNames
			ToponymSearchCriteria toponymSearchCriteria;
			ToponymSearchResult toponymSearchResult;
			List<WikipediaArticle> wikipediaArticlesList;
			WikipediaArticle wikipediaArticle;
			
			try {
				toponymSearchCriteria = new ToponymSearchCriteria();
				toponymSearchCriteria.setQ(matchedGroup);
				toponymSearchCriteria.setCountryCode("FR");
				toponymSearchResult = WebService.search(toponymSearchCriteria);

				// On a au moins un resultat trouve, on s'en contente
				if (toponymSearchResult.getTotalResultsCount() != 0) {
					// Debug
					for (Toponym toponym : toponymSearchResult.getToponyms()) {
						logger.debug("> Position de " + toponym.getName() + " = (" + toponym.getLatitude() + ", " + toponym.getLongitude() + ")");
					}
					
					// On ne s'interesse qu'au premier resultat
					return "(" + toponymSearchResult.getToponyms().get(0).getLatitude() + ", " + toponymSearchResult.getToponyms().get(0).getLongitude() + ")";
				} else {
					// Cas ou aucun resultat n'a ete trouve, on utilise Wikipedia
					wikipediaArticlesList = WebService.wikipediaSearch(matchedGroup, "FR");
					
					// On ne garde que le premier article trouve
					if (wikipediaArticlesList.size() != 0) {
						wikipediaArticle = wikipediaArticlesList.get(0);
					} else {
						// Echec de la localisation
						// On n'effectue aucun changement dans le texte source
						return matchedGroup;
					}
					
					// Debug
					logger.debug("> Position de " + wikipediaArticle.getTitle() + " = (" + wikipediaArticle.getLatitude() + ", " + wikipediaArticle.getLongitude() + ")");
					
					return "(" + wikipediaArticle.getLatitude() + ", " + wikipediaArticle.getLongitude() + ")";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// En cas d'erreur, ne remplace pas le texte
			return matchedGroup;
		} else {
			// Cas ou le formatMapper contient directement les informations a generer
			return formatMapper;
		}
	}
	
	/**
	 * Recherche des correspondances aux expressions lexicales passees en parametre
	 * Remplace a la volee les occurences trouvees par un texte genere en fonction de la semantique
	 * @param text Le texte a traiter
	 * @return Le texte apres que les remplacements aient ete effectues
	 */
	public String GeographicalLexicalSearch(String text) {
		Pattern pattern;
		Matcher matcher;
		
		// Debug
		logger.debug("Texte a examiner : " + text);
		
		// Recherche toutes les expressions lexicales
		for (int i = 0; i < patterns.size(); i++) {
			pattern = Pattern.compile(patterns.get(i), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			matcher = pattern.matcher(text);
			
			// Lance la recherche pour une expression reguliere
			logger.debug("Recherche de '" + pattern.toString() + "'...");
			while (matcher.find()) {
				// Si un lieu a ete identifie
				logger.debug("> Trouve '" + matcher.group().trim() + "'");
				
				// Remplacement du resultat selon le mapper
				text = matcher.replaceFirst(generateGeographicalReplacement(formatMappers.get(i), matcher.group()));
				matcher = pattern.matcher(text);
				
				logger.debug("> Nouveau texte : " + text);
			}
			
			logger.debug("Aucun autre resultat trouve");
		}
		
		// Fin de la recherche
		return text;
	}
}
