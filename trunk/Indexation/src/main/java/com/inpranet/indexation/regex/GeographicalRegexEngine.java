package com.inpranet.indexation.regex;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.inpranet.indexation.util.SimpleCoordinateFormat;
import com.inpranet.indexation.util.Toolbox;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * Moteur de recherche d'expressions regulieres pour les lieux
 * @author Stephane
 */
public class GeographicalRegexEngine extends RegexEngine {
	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(GeographicalRegexEngine.class);
	
	/**
	 * Constructeur de la classe GeographicalRegexEngine
	 * @throws FileNotFoundException Si le fichier contenant les Regex geographiques n'a pas pu etre utilise
	 */
	public GeographicalRegexEngine() throws FileNotFoundException {
		// Initialisation des patterns et des format mappers
		// Lecture des fichiers contenant les Regex temporelles
		loadRegexLists("src/main/resources/geographicalRegexList.txt");
	}
	
	/**
	 * Recherche des correspondances aux expressions regulieres passees en parametre 
	 * @param text Le texte a traiter
	 * @return Liste des instances trouvees dans le texte
	 */
	public GeographicalRegexResults GeographicalRegexSearch(String text) {
		Pattern pattern;
		Matcher matcher;
		Coordinate coordinate;
		GeographicalRegexResults geographicalRegexResults = new GeographicalRegexResults();
		
		// Debug
		logger.debug("Texte a examiner : " + text);
		
		// Recherche toutes les expressions regulieres
		for (int i = 0; i < patterns.size(); i++) {
			pattern = Pattern.compile(patterns.get(i), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			matcher = pattern.matcher(text);
			
			// Lance la recherche pour une expression reguliere
			logger.debug("Recherche de '" + pattern.toString() + "'...");
			while (matcher.find()) {
				// Si un lieu a ete identifie
				logger.debug("> Trouve '" + matcher.group().trim() + "'");
				
				// Formattage du resultat selon le mapper
				try {
					coordinate = SimpleCoordinateFormat.Parse(matcher.group().trim(), formatMappers.get(i));
					
					// Conservation des coordonnees trouvees
					geographicalRegexResults.AddResult(coordinate, formatMappers.get(i), matcher.start());
					
					// Effacement de l'expression trouvee dans le texte source
					text = matcher.replaceFirst(Toolbox.generateString(pattern.toString().length(), '_'));
					matcher = pattern.matcher(text);
					
					logger.debug("> Nouveau texte : " + text);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			logger.debug("Aucun autre resultat trouve");
		}
		
		// Fin de la recherche
		return geographicalRegexResults;
	}
}
