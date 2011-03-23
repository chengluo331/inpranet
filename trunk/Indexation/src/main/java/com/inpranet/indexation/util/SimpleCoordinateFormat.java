package com.inpranet.indexation.util;

import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Classe qui fournit des methodes statiques pour la construction d'objets de type Coordinate
 * @author Stephane
 */
public class SimpleCoordinateFormat {
	/**
	 * Variables necessaires pour l'extraction de nombre depuis une chaine source
	 */
	private static Pattern pattern = Pattern.compile("-?(\\d+)");
	
	/**
	 * Parse des coordonnees et renvoie l'objet de type Coordinate correspondant
	 * @param source Les coordonnees a analyser et a transformer
	 * @param formatMapper Le format mapper associe a la chaine a parser
	 * @return L'objet Coordinate correspondant
	 */
	public static Coordinate Parse(String source, String formatMapper) throws ParseException {
		// Debug
		System.out.println("SimpleCoordinateFormat : " + source);
		
		// Decompose la chaine qui a ete entree pour en extraire les nombres
		Matcher matcher = pattern.matcher(source);
		
		// La methode nextInt de Scanner ne supporte pas le symoble '+'
		formatMapper = formatMapper.replace('+', ' ');
		
		// Decompose le format mapper en plusieurs commandes interpretables
		Scanner scanner = new Scanner(formatMapper);
		scanner.useDelimiter(" ");
		
		// Construit les coordonnees selon le format mapper associe
		int readValue;
		String readOperation;
		double readFactor;
		double latitude = 0;
		double longitude = 0;
		while (matcher.find()) {
			// Initialisation des variables tampon
			readValue = Integer.parseInt(matcher.group());
			readOperation = scanner.next();
			readFactor = Double.valueOf(scanner.next()).doubleValue();
			
			// Debug
			System.out.println("SimpleCoordinateFormat : Trouve " + readValue + ", Format : " + readOperation + ", Multiplicator : " + readFactor);
			
			// Effectue les operations decrites dans le formatMapper associe
			// Le signe est porte par le format mapper
			if (readOperation.equals("l")) {
				latitude += Math.abs(readValue) * readFactor;
			} else if (readOperation.equals("L")) {
				longitude += Math.abs(readValue) * readFactor;
			} else {
				// Commande non renconnue, ne fait rien
			}
		}
		
		// Construit l'objet de type Coordinate
		// Pas de prise en compte de l'altitude
		return new Coordinate(latitude, longitude, 0);
	}
}
