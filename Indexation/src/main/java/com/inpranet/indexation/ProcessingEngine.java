package com.inpranet.indexation;

import java.util.Date;
import java.util.Scanner;

import com.inpranet.core.model.Document;
import com.inpranet.indexation.regex.GeographicalLexicalRegexEngine;
import com.inpranet.indexation.regex.GeographicalRegexEngine;
import com.inpranet.indexation.regex.GeographicalRegexResults;
import com.inpranet.indexation.regex.TemporalLexicalRegexEngine;
import com.inpranet.indexation.regex.TemporalRegexEngine;
import com.inpranet.indexation.regex.TemporalRegexResults;
import com.inpranet.indexation.service.DocumentManager;

import javax.jws.WebService;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Moteur de traitement de documents
 * @author Stephane
 */
@WebService(endpointInterface = "com.inpranet.indexation.ProcessingEngineSEI")
public class ProcessingEngine implements ProcessingEngineSEI {
	/**
	 * Utilisation des services metiers lies aux objets Documents
	 */
	private static DocumentManager documentManager = new DocumentManager();
	
	/**
	 * Utilisation de moteurs Regex
	 */
	private static TemporalRegexEngine temporalRegexEngine = new TemporalRegexEngine();
	private static TemporalLexicalRegexEngine temporalLexicalRegexEngine = new TemporalLexicalRegexEngine();
	private static GeographicalRegexEngine geographicalRegexEngine = new GeographicalRegexEngine();
	private static GeographicalLexicalRegexEngine geographicalLexicalRegexEngine = new GeographicalLexicalRegexEngine();
	
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
	 * Coordonnees de la position geographique de l'evenement geo-temporel
	 */
	private static Coordinate coordinate;
	
	/**
	 * Recherche une periode temporelle dans un document
	 * @param documentPath Le chemin d'acces au document a traiter
	 */
	private static String temporalProcessing(InputDocument inputDocument) {
		TemporalRegexResults endDateRegexResults;
		TemporalRegexResults startDateRegexResults;
		TemporalRegexResults dateRegexResults;
		
		System.out.println("ProcessingEngine : Recherche de dates...");
		System.out.println();
		
		// Verifie si les donnees de la source sont suffisantes
		if (inputDocument.GetTemporalSourceReliable()) {
			// Cas ou les donnees de la source sont suffisantes
			// On ne va pas analyser le texte du document mais que les donnees liees a la source
			startDateRegexResults = temporalRegexEngine.TemporalRegexSearch(temporalLexicalRegexEngine.TemporalLexicalSearch(inputDocument.GetTemporalSourceStartData(), inputDocument.GetCreationDate()));
			endDateRegexResults = temporalRegexEngine.TemporalRegexSearch(temporalLexicalRegexEngine.TemporalLexicalSearch(inputDocument.GetTemporalSourceEndData(), inputDocument.GetCreationDate()));
			
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
			dateRegexResults = temporalRegexEngine.TemporalRegexSearch(temporalLexicalRegexEngine.TemporalLexicalSearch(inputDocument.GetData(), inputDocument.GetCreationDate()));
			
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
		
		return "Debut : " + startDate.toString() + ", Fin : " + endDate.toString(); 
	}
	
	/**
	 * Recherche une position geographique dans un document
	 * @param inputDocument Le chemin d'acces au document a traiter
	 */
	private static String geographicalProcessing(InputDocument inputDocument) {
		GeographicalRegexResults geographicalRegexResults;
		
		System.out.println("ProcessingEngine : Recherche de lieux...");
		System.out.println();
		
		// Verifie si les donnees de la source sont suffisantes
		if (inputDocument.GetGeographicalSourceReliable()) {
			// Cas ou les donnees de la source sont suffisantes
			// On ne va pas analyser le texte du document mais que les donnees liees a la source
			geographicalRegexResults = geographicalRegexEngine.GeographicalRegexSearch(geographicalLexicalRegexEngine.GeographicalLexicalSearch(inputDocument.GetGeographicalSourceData()));
		} else {
			// Cas ou il faut faire une analyse sur le texte du document
			geographicalRegexResults = geographicalRegexEngine.GeographicalRegexSearch(geographicalLexicalRegexEngine.GeographicalLexicalSearch(inputDocument.GetData()));
		}
		
		// Formattage des resultats trouves
		//geographicalRegexResults.FormatResults(inputDocument.GetCreationDate());
		
		// Debug
		System.out.println("ProcessingEngine : Lieux trouves :");
		geographicalRegexResults.DisplayResults();
		System.out.println();
		
		// Interpretation des resultats obtenus (par exemple, on sait que la date de fin ne peut pas etre depassee)
		coordinate = geographicalRegexResults.GetBestResult();
		
		// Debug
		System.out.println("InputDocument : Lieu trouve : " + coordinate.toString());
		System.out.println();
		
		return "Lieu : " + coordinate.toString();
	}
	
	/**
	 * Lance le traitement d'un document
	 * @param documentPath Le chemin d'acces au document a traiter
	 */
	public String StartProcessing(String inputDocumentPath) {
		// Stocke les retours des analyseurs
		String temporalProcessingResults = "";
		String geographicalProcessingResults = "";
		
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
			temporalProcessingResults = temporalProcessing(inputDocument);
			
			// Recherche de donnees geographiques
			geographicalProcessingResults = geographicalProcessing(inputDocument);
			
			// Ajout du document dans la base de donnees
			// TODO : Traitements d'erreurs
			// TODO : Passer les dates en float
			Document document = new Document(inputDocument.GetReference(), inputDocument.GetTitle(), inputDocument.IsUrgent(), "Category", inputDocument.GetUri(), startDate, endDate, (float)coordinate.x, (float)coordinate.y, inputDocument.GetData());
			documentManager.saveDocument(document);
		}
		
		// Fin du traitement
		System.out.println("> Traitement termine, appuyez sur [ENTREE] pour revenir au menu...");

		// Attend un appui
		scanner.nextLine();
		
		return temporalProcessingResults + ", " + geographicalProcessingResults; 
	}
}
