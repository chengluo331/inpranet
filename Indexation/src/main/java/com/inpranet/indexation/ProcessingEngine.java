package com.inpranet.indexation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.Category;
import com.inpranet.core.model.Document;
import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Zone;
import com.inpranet.core.ws.bus.IInternalService;
import com.inpranet.indexation.regex.GeographicalLexicalRegexEngine;
import com.inpranet.indexation.regex.GeographicalRegexEngine;
import com.inpranet.indexation.regex.GeographicalRegexResults;
import com.inpranet.indexation.regex.TemporalLexicalRegexEngine;
import com.inpranet.indexation.regex.TemporalRegexEngine;
import com.inpranet.indexation.regex.TemporalRegexResults;
import com.inpranet.indexation.service.CategoryManager;
import com.inpranet.indexation.service.DocumentManager;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * Moteur de traitement de documents
 * @author Stephane
 */
@WebService(endpointInterface = "com.inpranet.indexation.ProcessingEngineSEI")
public class ProcessingEngine implements ProcessingEngineSEI {
	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(ProcessingEngine.class);
	
	/**
	 * Utilisation des services metiers lies aux objets Documents
	 */
	private static DocumentManager documentManager = new DocumentManager();
	
	/**
	 * Utilisation de moteurs Regex
	 */
	private static TemporalRegexEngine temporalRegexEngine;
	private static TemporalLexicalRegexEngine temporalLexicalRegexEngine;
	private static GeographicalRegexEngine geographicalRegexEngine;
	private static GeographicalLexicalRegexEngine geographicalLexicalRegexEngine;
	
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
	 * Le module Indexation est client du Bus
	 */
	private static IInternalService internalService;
	
	/**
	 * Constructeur statique
	 */
	static {
		// Chargement des beans Spring
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexation-data.xml");
		internalService = (IInternalService) applicationContext.getBean("busService");
		
		// Initialisation des moteurs Regex
		try {
			temporalRegexEngine = new TemporalRegexEngine();
			temporalLexicalRegexEngine = new TemporalLexicalRegexEngine();
			geographicalRegexEngine = new GeographicalRegexEngine();
			geographicalLexicalRegexEngine = new GeographicalLexicalRegexEngine();
		} catch (FileNotFoundException e) {
			logger.error("L'un des fichiers contenant les listes Regex n'a pas pu etre trouve");
		}
	}
	
	/**
	 * Recherche une periode temporelle dans un document
	 * @param documentPath Le chemin d'acces au document a traiter
	 */
	private static String temporalProcessing(InputDocument inputDocument) {
		TemporalRegexResults endDateRegexResults;
		TemporalRegexResults startDateRegexResults;
		TemporalRegexResults dateRegexResults;
		
		logger.debug("Recherche de dates...");
		
		// Verifie si les donnees de la source sont suffisantes
		if (inputDocument.IsTemporalSourceReliable()) {
			// Cas ou les donnees de la source sont suffisantes
			// On ne va pas analyser le texte du document mais que les donnees liees a la source
			startDateRegexResults = temporalRegexEngine.TemporalRegexSearch(temporalLexicalRegexEngine.TemporalLexicalSearch(inputDocument.GetTemporalSourceStartData(), inputDocument.GetCreationDate()));
			endDateRegexResults = temporalRegexEngine.TemporalRegexSearch(temporalLexicalRegexEngine.TemporalLexicalSearch(inputDocument.GetTemporalSourceEndData(), inputDocument.GetCreationDate()));
			
			// Formattage des resultats trouves (regroupement des informations uniquement)
			startDateRegexResults.FormatResults(inputDocument.GetCreationDate());
			endDateRegexResults.FormatResults(inputDocument.GetCreationDate());
			
			// Debug
			logger.debug("Dates de debut :");
			startDateRegexResults.DisplayResults();
			
			logger.debug("Dates de fin :");
			endDateRegexResults.DisplayResults();
			
			// Interpretation des resultats obtenus (par exemple, on sait que la date de fin ne peut pas etre depassee)
			// On utilise deux fois GetStartDate ici, on est cense n'avoir qu'une seule date dans ces champs
			endDate = endDateRegexResults.GetStartDate();
			startDate = startDateRegexResults.GetStartDate();
			
			// Debug
			logger.debug("Date de debut : " + startDate.toString());
			logger.debug("Date de fin : " + endDate.toString());
		} else {
			// Cas ou il faut faire une analyse sur le texte du document
			// On lance une recherche sur le contenu du document
			dateRegexResults = temporalRegexEngine.TemporalRegexSearch(temporalLexicalRegexEngine.TemporalLexicalSearch(inputDocument.GetData(), inputDocument.GetCreationDate()));
			
			// Formattage des resultats trouves
			dateRegexResults.FormatResults(inputDocument.GetCreationDate());
			
			// Debug
			logger.debug("Dates trouvees :");
			dateRegexResults.DisplayResults();
			
			// Interpretation des resultats obtenus (par exemple, on sait que la date de fin ne peut pas etre depassee)
			// On ne recherche une periode que si on a au moins deux resultats (condition interne aux methodes)
			endDate = dateRegexResults.GetEndDate();
			startDate = dateRegexResults.GetStartDate();
			
			// Debug
			logger.debug("Date de debut : " + startDate.toString());
			logger.debug("Date de fin : " + endDate.toString());
		}
		
		logger.debug("Debut : " + startDate.toString() + ", Fin : " + endDate.toString());
		return "Debut : " + startDate.toString() + ", Fin : " + endDate.toString(); 
	}
	
	/**
	 * Recherche une position geographique dans un document
	 * @param inputDocument Le chemin d'acces au document a traiter
	 */
	private static String geographicalProcessing(InputDocument inputDocument) throws NullPointerException {
		GeographicalRegexResults geographicalRegexResults;
		
		logger.debug("Recherche de lieux...");
		
		// Verifie si les donnees de la source sont suffisantes
		if (inputDocument.IsGeographicalSourceReliable()) {
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
		logger.debug("Lieux trouves :");
		geographicalRegexResults.DisplayResults();
		
		// Interpretation des resultats obtenus
		coordinate = geographicalRegexResults.GetBestResult();
		
		// Il se peut que la recherche de lieux ne soit pas concluante
		// TODO : Gerer ce cas
		if (coordinate == null) {
			logger.error("Aucun lieu n'a ete trouve pour ce document");
			throw new NullPointerException();
		}
		
		// Debug
		logger.debug("Lieu trouve : " + coordinate.toString());
		
		return "Lieu : " + coordinate.toString();
	}
	
	/**
	 * Realise un mapping entre les coordonnes trouvees dans le document et les objet Zone
	 */
	private static List<Zone> zoneProcessing() throws NullPointerException {
		// TODO : Uniformiser les types de donnees
		GeoPos geoPos = new GeoPos(coordinate.y, coordinate.x, null);
		
		// Lance une requete vers le bus pour l'identification des coordonnees
		logger.debug("Lance une requête vers le Bus");
		List<Zone> mappedZonesList = internalService.getZoneListFromGeoPos(geoPos);
		
		// Debug
		logger.debug("La requête a bien ete lancee");
		logger.debug("Nombre de zones : " + mappedZonesList.size());
		
		// Il se peut que la recherche de lieux ne soit pas concluante
		if (mappedZonesList.size() == 0) {
			logger.error("Aucun lieu n'a ete trouve pour ce document");
			throw new NullPointerException();
		}
		
		logger.debug("Premiere zone trouvee : " + mappedZonesList.get(0).getIdZone());
		
		return mappedZonesList;
	}
	
	/**
	 * Realise un mapping entre les categories trouvees dans le document et les objet Category
	 */
	private static List<Category> categoryProcessing(InputDocument inputDocument) {
		// Utilisation des services metiers des categories
		CategoryManager categoryManager = new CategoryManager();
		List<Category> mappedCategoriesList = new ArrayList<Category>();
		
		// Realise la mapping pour toutes les categories detectees
		List<String> categoriesList = inputDocument.GetCategoriesList();
		for (int i = 0; i < categoriesList.size(); i++) {
			// Debug
			logger.debug("Categorie trouvee : " + categoriesList.get(i).toString());
			
			// On ne recupere qu'un seul resultat
			mappedCategoriesList.add(categoryManager.getCategoryByName(categoriesList.get(i)).get(0));
		}
		
		return mappedCategoriesList;
	}
	
	/**
	 * Lance le traitement d'un document
	 * @param inputDocumentPath Le chemin d'acces au document a traiter
	 */
	public String StartProcessing(String inputDocumentPath) {
		// Stocke les retours des analyseurs
		String temporalProcessingResults = "";
		String geographicalProcessingResults = "";
		List<Category> mappedCategoriesList;
		List<Zone> mappedZonesList;
		
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
			logger.error("Le fichier n'est pas valide");
		} else {
			try {
				// Recherche de donnees temporelles
				temporalProcessingResults = temporalProcessing(inputDocument);
				
				// Recherche de donnees geographiques
				geographicalProcessingResults = geographicalProcessing(inputDocument);
				
				// Realise le mapping entre les donnees geographiques et les objets Zone
				mappedZonesList = zoneProcessing();
				
				// Realise le mapping entre les categories donnees dans le document et les objets Category
				mappedCategoriesList = categoryProcessing(inputDocument);
				
				// Ajout du document dans la base de donnees
				// TODO : Traitements d'erreurs
				// TODO : Passer les dates en float
				Document document = new Document(inputDocument.GetReference(), inputDocument.GetTitle(), inputDocument.IsUrgent(), mappedCategoriesList, inputDocument.GetUri(), startDate, endDate, (float)coordinate.x, (float)coordinate.y, mappedZonesList, inputDocument.GetData());
				documentManager.saveDocument(document);
			} catch (NullPointerException e) {
				System.out.println("Erreur : Le document est inutilisable");
				System.out.println();
			}
		}
		
		// Fin du traitement
		System.out.println("> Traitement termine, appuyez sur [ENTREE] pour revenir au menu...");

		// Attend un appui
		scanner.nextLine();
		
		return temporalProcessingResults + ", " + geographicalProcessingResults; 
	}
}
