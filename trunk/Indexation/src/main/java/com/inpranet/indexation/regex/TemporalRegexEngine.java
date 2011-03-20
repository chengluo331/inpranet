package com.inpranet.indexation.regex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.inpranet.indexation.util.Toolbox;

/**
 * Moteur de recherche d'expressions regulieres pour les dates
 * @author Stephane
 */
public class TemporalRegexEngine extends RegexEngine {
	/**
	 * Constructeur de la classe TemporalRegexEngine
	 */
	public TemporalRegexEngine() {
		// Initialisation des patterns et des format mappers
		// Lecture des fichiers contenant les Regex temporelles
		loadRegexLists("C:\\Temp\\temporalRegexList.txt");
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
		System.out.println("TemporalRegexEngine : > Texte a examiner : " + text);
		System.out.println();
		
		// Recherche toutes les expressions regulieres
		for (int i = 0; i < patterns.size(); i++) {
			pattern = Pattern.compile(patterns.get(i), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			matcher = pattern.matcher(text);
			
			// Lance la recherche pour une expression reguliere
			System.out.println("TemporalRegexEngine : Recherche de '" + pattern.toString() + "'...");
			while (matcher.find()) {
				// Si une date a ete identifiee
				System.out.println("TemporalRegexEngine : > Trouve '" + matcher.group().trim() + "'");
				
				// Formattage du resultat selon le mapper
				try {
					simpleDateFormat = new SimpleDateFormat(formatMappers.get(i), Locale.FRANCE);
					date = simpleDateFormat.parse(matcher.group().trim());
					
					// Conservation de la date trouvee
					temporalRegexResults.AddResult(date, formatMappers.get(i), matcher.start());
					
					// Effacement de l'expression trouvee dans le texte source
					text = matcher.replaceFirst(Toolbox.generateString(pattern.toString().length(), '_'));
					matcher = pattern.matcher(text);
					System.out.println("TemporalRegexEngine : > Nouveau texte : " + text);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("TemporalRegexEngine : Aucun autre resultat trouve");
			System.out.println();
		}
		
		// Fin de la recherche
		return temporalRegexResults;
	}
}
