package com.inpranet.indexation.regex;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.inpranet.indexation.util.Toolbox;

/**
 * Moteur de recherche d'expressions regulieres pour les dates
 * @author Stephane
 */
public class TemporalRegexEngine extends RegexEngine {
	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(TemporalRegexEngine.class);
	
	/**
	 * Constructeur de la classe TemporalRegexEngine
	 */
	public TemporalRegexEngine() throws FileNotFoundException {
		// Initialisation des patterns et des format mappers
		// Lecture des fichiers contenant les Regex temporelles
		loadRegexLists("src/main/resources/temporalRegexList.txt");
	}
	
	/**
	 * Recherche des correspondances aux expressions regulieres passees en parametre 
	 * @param text Le texte a traiter
	 * @return Liste des instances trouvees dans le texte
	 */
	public TemporalRegexResults TemporalRegexSearch(String text) {
		Pattern pattern;
		Matcher matcher;
		Date date;
		SimpleDateFormat simpleDateFormat;
		TemporalRegexResults temporalRegexResults = new TemporalRegexResults();
		
		// Debug
		logger.debug("Texte a examiner : " + text);
		
		// Recherche toutes les expressions regulieres
		for (int i = 0; i < patterns.size(); i++) {
			pattern = Pattern.compile(patterns.get(i), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			matcher = pattern.matcher(text);
			
			// Lance la recherche pour une expression reguliere
			logger.debug("Recherche de '" + pattern.toString() + "'...");
			while (matcher.find()) {
				// Si une date a ete identifiee
				logger.debug("> Trouve '" + matcher.group().trim() + "'");
				
				// Formattage du resultat selon le mapper
				try {
					simpleDateFormat = new SimpleDateFormat(formatMappers.get(i), Locale.FRANCE);
					date = simpleDateFormat.parse(matcher.group().trim().replaceAll("\\s", ""));
					
					// Conservation de la date trouvee
					temporalRegexResults.AddResult(date, formatMappers.get(i), matcher.start());
					
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
		return temporalRegexResults;
	}
}
