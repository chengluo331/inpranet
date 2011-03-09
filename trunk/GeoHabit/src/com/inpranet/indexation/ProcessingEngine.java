package com.inpranet.indexation;

import java.util.Date;
import java.util.Scanner;

import com.inpranet.util.TemporalRegexEngine;
import com.inpranet.util.TemporalRegexResults;

/**
 * Moteur de traitement de documents
 * @author Stephane
 */
public class ProcessingEngine {
	/**
	 * Date de debut de l'evenement geo-temporel
	 */
	private static Date startDate;
	
	/**
	 * Date de fin de l'evenement geo-temporel
	 * Cette date est forcement dans le futur
	 */
	private static Date endDate;
	
	/**
	 * Recherche une periode temporelle dans un document
	 * @param documentPath Le chemin d'acces au document a traiter
	 */
	private static void temporalProcessing(InputDocument inputDocument) {
		TemporalRegexResults endDateRegexResults;
		TemporalRegexResults startDateRegexResults;
		TemporalRegexResults dateRegexResults;
		
		System.out.println("ProcessingEngine : Recherche de dates...");
		System.out.println();
		
		// Verifie si les donnees de la source sont suffisantes
		if (inputDocument.GetTemporalSourceReliable()) {
			// Cas ou les donnees de la source sont suffisantes
			// On ne va pas analyser le texte du document mais que les donnees liees a la source
			startDateRegexResults = TemporalRegexEngine.TemporalRegexSearch(inputDocument.GetTemporalSourceStartData());
			endDateRegexResults = TemporalRegexEngine.TemporalRegexSearch(inputDocument.GetTemporalSourceEndData());
			
			// Formattage des resultats trouves (regroupement des informations uniquement)
			startDateRegexResults.FormatResults(inputDocument.GetCreationDate());
			endDateRegexResults.FormatResults(inputDocument.GetCreationDate());
			
			// Debug
			System.out.println("ProcessingEngine : Dates de debut :");
			startDateRegexResults.DisplayResults();
			System.out.println();
			
			System.out.println("ProcessingEngine : Dates de fin :");
			endDateRegexResults.DisplayResults();
			System.out.println();
			
			// Interpretation des resultats obtenus (par exemple, on sait que la date de fin ne peut pas etre depassee)
			endDate = endDateRegexResults.GetEndDate();
			startDate = startDateRegexResults.GetStartDate();
			
			// Debug
			System.out.println("InputDocument : Date de debut : " + startDate.toString());
			System.out.println();
			
			System.out.println("InputDocument : Date de fin : " + endDate.toString());
			System.out.println();
		} else {
			// Cas ou il faut faire une analyse sur le texte du document
			// On lance une recherche sur le contenu du document
			dateRegexResults = TemporalRegexEngine.TemporalRegexSearch(inputDocument.GetData());
			
			// Formattage des resultats trouves
			dateRegexResults.FormatResults(inputDocument.GetCreationDate());
			
			// Debug
			System.out.println("ProcessingEngine : Dates trouvees :");
			dateRegexResults.DisplayResults();
			System.out.println();
			
			// Interpretation des resultats obtenus (par exemple, on sait que la date de fin ne peut pas etre depassee)
			// On ne recherche une periode que si on a au moins deux resultats (condition interne aux methodes)
			endDate = dateRegexResults.GetEndDate();
			startDate = dateRegexResults.GetStartDate();
			
			// Debug
			System.out.println("InputDocument : Date de debut : " + startDate.toString());
			System.out.println();
			
			System.out.println("InputDocument : Date de fin : " + endDate.toString());
			System.out.println();
		}
	}
	
	/**
	 * Recherche une position géographique dans un document
	 * @param documentPath Le chemin d'acces au document a traiter
	 */
	private static void geographicalProcessing(InputDocument inputDocument) {
		System.out.println("ProcessingEngine : Recherche de lieux...");
		System.out.println();
	}
	
	/**
	 * Lance le traitement d'un document
	 * @param documentPath Le chemin d'acces au document a traiter
	 */
	public static void StartProcessing(String inputDocumentPath) {
		Scanner scanner = new Scanner(System.in);
		
		// Log
		System.out.println("TRAITEMENT");
		System.out.println("**********");
		System.out.println();
		System.out.println("ProcessingEngine : Fichier : " + inputDocumentPath);
		System.out.println();
		
		// Analyse du document
		InputDocument inputDocument = new InputDocument(inputDocumentPath);
		
		// L'analyse est terminee, le document n'est pas valide
		if (!inputDocument.IsValid()) {
			// Selon le type d'erreur, le document est soit detruit, soit mis de cote
			System.out.println("Erreur : Le fichier n'est pas valide");
			System.out.println();
		} else {
			// Recherche de donnees temporelles
			temporalProcessing(inputDocument);
			
			// Recherche de donnees geographiques
			geographicalProcessing(inputDocument);
		}
		
		// Fin du traitement
		System.out.println("> Traitement termine, appuyez sur [ENTREE] pour revenir au menu...");

		// Attend un appui
		scanner.nextLine();
	}
}
