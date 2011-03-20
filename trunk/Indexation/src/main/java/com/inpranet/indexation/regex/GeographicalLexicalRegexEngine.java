package com.inpranet.indexation.regex;

/*import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Moteur de recherche d'expressions lexicales pour les lieux
 * @author Stephane
 */
public class GeographicalLexicalRegexEngine extends RegexEngine {
	/**
	 * Constructeur de la classe GeographicalLexicalRegexEngine
	 */
	public GeographicalLexicalRegexEngine() {
		// Initialisation des patterns et des format mappers
		// Lecture des fichiers contenant les expressions lexicales geographiques
		loadRegexLists("C:\\Temp\\geographicalLexicalList.txt");
	}
	
	/**
	 * Genere le texte de remplacement en fonction du format mapper
	 * @param formatMapper Le format mapper concerne 
	 */
	private String generateGeographicalReplacement(String formatMapper) {
		// Le format mapper peut indiquer une formule temporelle
		if (formatMapper.startsWith("�")) {
			/*
			// Enleve le caractere �
			formatMapper = formatMapper.substring(1);
			
			// La methode nextInt de Scanner ne supporte pas le symoble '+'
			formatMapper = formatMapper.replace('+', ' ');
			
			// Gere le cas ou le formatMapper n'est pas l'information a generer
			Scanner scanner = new Scanner(formatMapper);
			
			// Lecture des variables de decalage, c'est l'operation a effectuer sur la date de reference
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(referenceDate);
			System.out.println(">> " + calendar.get(Calendar.DAY_OF_WEEK));
			
			// Planifie les operations de transformations
			List<Integer> operationList = new ArrayList<Integer>();
			operationList.add(Calendar.YEAR);
			operationList.add(Calendar.MONTH);
			operationList.add(Calendar.DAY_OF_WEEK);
			operationList.add(Calendar.HOUR);
			operationList.add(Calendar.MINUTE);
			
			// Effectue les transformations sur la date de reference
			String readOperation;
			int readShift;
			boolean dayModification = false;
			boolean hourModification = false;
			for (int i = 0; i < 5; i++) {
				// Lit l'operation passee en parametre
				readOperation = scanner.next();
				
				// Lit la valeur passee en parametre
				readShift = scanner.nextInt();
				
				// Garde une trace des modifications qui ont ete effectuees
				if (readShift != 0) {
					if (i <= 2) {
						dayModification = true;
					} else {
						hourModification = true;
					}
				}
				
				if (readOperation.equals("s")) {
					// Effectue un decalage sur un champ
					calendar.add(operationList.get(i), readShift);
				} else if (readOperation.equals("e")) {
					// Effectue une succession de decalage jusqu'a obtenir la valeur desiree sur le champ
					System.out.println(">> " + calendar.get(Calendar.DAY_OF_WEEK));
					while (calendar.get(operationList.get(i)) != readShift) {
						calendar.add(operationList.get(i), 1);
						System.out.println(">> " + calendar.get(Calendar.DAY_OF_WEEK));
					}
				}
			}
			
			// Cree le nouveau format d'affichage
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
			if ((dayModification) && (!hourModification)) {
				simpleDateFormat.applyPattern("yyyy-MM-dd");
			} else if ((!dayModification) && (hourModification)) {
				simpleDateFormat.applyPattern("HH:mm");
			} else {
				simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm");
			}
			
			// Debug
			System.out.println("TemporalLexicalRegexEngine : Ancienne date : " + referenceDate.toString());
			System.out.println("TemporalLexicalRegexEngine : Nouvelle date : " + calendar.getTime().toString());
			
			// Effectue le remplacement
			return simpleDateFormat.format(calendar.getTime());
			*/
			return new String();
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
		System.out.println("GeographicalLexicalEngine : > Texte a examiner : " + text);
		System.out.println();
		
		// Recherche toutes les expressions lexicales
		for (int i = 0; i < patterns.size(); i++) {
			pattern = Pattern.compile(patterns.get(i), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			matcher = pattern.matcher(text);
			
			// Lance la recherche pour une expression reguliere
			System.out.println("GeographicalLexicalEngine : Recherche de '" + pattern.toString() + "'...");
			while (matcher.find()) {
				// Si une date a ete identifiee
				System.out.println("GeographicalLexicalEngine : > Trouve '" + matcher.group().trim() + "'");
				
				// Remplacement du resultat selon le mapper
				text = matcher.replaceFirst(generateGeographicalReplacement(formatMappers.get(i)));
				matcher = pattern.matcher(text);
				System.out.println("GeographicalLexicalEngine : > Nouveau texte : " + text);
			}
			
			System.out.println("GeographicalLexicalEngine : Aucun autre resultat trouve");
			System.out.println();
		}
		
		// Fin de la recherche
		return text;
	}
}
