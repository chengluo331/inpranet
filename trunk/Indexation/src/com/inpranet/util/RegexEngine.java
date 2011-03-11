package com.inpranet.util;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Moteur de recherche d'expressions regulieres en utilisant des motifs Regex
 * et des mappers, liant la syntaxe a la semantique
 * @author Stephane
 */
public abstract class RegexEngine {
	/**
	 * Liste des expressions regulieres utilisees pour la recherche Regex
	 */
	protected static List<String> patterns = new ArrayList<String>();
	
	/**
	 * Liste des formats des objets qui sont reconnues par les expressions regulieres,
	 * il doit y avoir une correspondance au niveau des index entre patterns et formatMapper 
	 */
	protected static List<String> formatMappers = new ArrayList<String>();
	
	/**
	 * Charge les patterns et les formatMappers Regex utilises lors de la recherche depuis un fichier
	 * @param filePath Le chemin d'acces au fichier contenant les patterns et les format mappers.
	 */
	protected static void loadRegexLists(String filePath) {
		File file = new File(filePath);
		Scanner scanner;
		
		try {
			scanner = new Scanner(new FileReader(file));
		} catch (Exception e) {
			System.out.println("Erreur : Le fichier contenant les listes Regex n'est pas valide");
			System.out.println();
			
			return;
		}
		
		// Lecture ligne par ligne du fichier
		String readLine;
		String readPattern;
		String readFormatMapper;
		while (scanner.hasNextLine()) {
			readLine = scanner.nextLine();
			
			// Verifie si la ligne en cours est un commentaire (commence par un '%')
			if (readLine.startsWith("%") || readLine.isEmpty()) {
				// Saute le traitement et passe a la ligne suivante
				continue;
			}
			
			// Reconnaissance des elements lus
			readPattern = readLine;
			readFormatMapper = scanner.nextLine();
			
			// Ajout du couple dans les listes associees
			patterns.add(readPattern);
			formatMappers.add(readFormatMapper);
		}
		
		// Fermeture du fichier
		scanner.close();
	}
}
