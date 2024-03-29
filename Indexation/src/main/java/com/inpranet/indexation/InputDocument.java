package com.inpranet.indexation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.inpranet.indexation.regex.TemporalRegexEngine;
import com.inpranet.indexation.regex.TemporalRegexResults;

/**
 * Classe qui fournit des methodes pour gerer les E/S sur les documents
 * @author Stephane
 */
public class InputDocument {
	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(InputDocument.class);
	
	/**
	 * Utilisation de moteurs Regex
	 */
	private TemporalRegexEngine temporalRegexEngine;
	
	/**
	 * Objet de type Fichier representant le document traite
	 */
	private File file;
	
	/**
	 * Arbre DOM correspondant au document XML traite
	 */
	private Document document;
	
	/**
	 * Chaine extraite des metadonnees identifiant la nature urgente (importante) ou non du document
	 */
	private String _urgent;
	
	/**
	 * Chaine extraite des metadonnees representant la reference du document
	 * Il s'agit d'un identifiant unique non rattache au contenu du document en lui-meme
	 */
	private String reference;
	
	/**
	 * Chaine extraite des metadonnees representant le titre du docuement
	 */
	private String title;
	
	/**
	 * Chaine extraite des metadonnees representant l'URI du document
	 */
	private String uri;
	
	/**
	 * Liste de chaines extraite des metadonnees representant les categories du document
	 */
	private List<String> categoriesList = new ArrayList<String>();
	
	/**
	 * Chaine extraite des metadonnees identifiant la fiabilite des categories donnees ou inferees par la source
	 */
	private String _categoriesSourceReliable;
	
	/**
	 * Chaine extraite des metadonnees representant les informations sur la date de creation du document 
	 */
	private String temporalCreationData;
	
	/**
	 * Chaine extraite des metadonnees identifiant la fiabilite des dates donnees ou inferees par la source
	 */
	private String _temporalSourceReliable;
	
	/**
	 * Chaine extraite des metadonnees representant les informations sur la date de debut du document 
	 */
	private String temporalSourceStartData;
	
	/**
	 * Chaine extraite des metadonnees representant les informations sur la date de fin du document 
	 */
	private String temporalSourceEndData;
	
	/**
	 * Chaine extraite des metadonnees identifiant la fiabilite des lieux donnees ou inferees par la source
	 */
	private String _geographicalSourceReliable;
	
	/**
	 * Chaine extraite des metadonnees representant les informations sur le lieu du document 
	 */
	private String geographicalSourceData;
	
	/**
	 * Chaine extraite du document representant le contenu de celui-ci 
	 */
	private String data;
	
	/**
	 * Interpretation de la chaine extraite des metadonnees qui lui est liee
	 */
	private boolean urgent;
	
	/**
	 * Interpretation de la chaine extraite des metadonnees qui lui est liee
	 */
	private Date creationDate;
	
	/**
	 * Interpretation de la chaine extraite des metadonnees qui lui est liee
	 */
	private boolean categoriesSourceReliable;
	
	/**
	 * Interpretation de la chaine extraite des metadonnees qui lui est liee
	 */
	private boolean temporalSourceReliable;
	
	/**
	 * Interpretation de la chaine extraite des metadonnees qui lui est liee
	 */
	private boolean geographicalSourceReliable;
	
	/**
	 * Teste si le document est valide d'un point de vue syntaxique
	 * @return true si le document est valide, false sinon
	 */
	public boolean IsValid() {
		// Teste si le fichier existe
		if (!file.exists()) {
			logger.error("Le fichier " + file.getAbsolutePath() + " n'existe pas");
			return false;
		}
		
		// Construction de l'arbre DOM correspondant au document
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			document = builder.parse(file.getCanonicalPath());
			
			// Initialisation de XPath
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			
			// Lecture des metadonnees unitaires
			_urgent = xPath.evaluate("//@urgent", document.getDocumentElement());
			reference = xPath.evaluate("//metaData/reference/text()", document.getDocumentElement());
			title = xPath.evaluate("//metaData/title/text()", document.getDocumentElement());
			uri = xPath.evaluate("//metaData/uri/text()", document.getDocumentElement());
			_categoriesSourceReliable = xPath.evaluate("//metaData/categories/@categoriesSourceReliable", document.getDocumentElement());
			temporalCreationData = xPath.evaluate("//metaData/temporalCreationData/text()", document.getDocumentElement());
			_temporalSourceReliable = xPath.evaluate("//metaData/temporalSourceData/@temporalSourceReliable", document.getDocumentElement());
			temporalSourceStartData = xPath.evaluate("//metaData/temporalSourceData/temporalSourceStartData/text()", document.getDocumentElement());
			temporalSourceEndData = xPath.evaluate("//metaData/temporalSourceData/temporalSourceEndData/text()", document.getDocumentElement());
			_geographicalSourceReliable = xPath.evaluate("//metaData/geographicalSourceData/@geographicalSourceReliable", document.getDocumentElement());
			geographicalSourceData = xPath.evaluate("//metaData/geographicalSourceData/text()", document.getDocumentElement());
			
			// Lecture des metadonnees en liste
			XPathExpression xPathExpression;
			xPathExpression = xPath.compile("//metaData/categoriesList/category/text()");
			Object result = xPathExpression.evaluate(document.getDocumentElement(), XPathConstants.NODESET);
			NodeList nodesList = (NodeList) result;
			for (int i = 0; i < nodesList.getLength(); i++) {
				categoriesList.add(nodesList.item(i).getNodeValue());
			}
			
			// Lecture du texte du document
			data = xPath.evaluate("//data/text()", document.getDocumentElement());
			
			// Interpretation de la valeur des elements lus
			urgent = Boolean.parseBoolean(_urgent);
			categoriesSourceReliable = Boolean.parseBoolean(_categoriesSourceReliable);
			temporalSourceReliable = Boolean.parseBoolean(_temporalSourceReliable);
			geographicalSourceReliable = Boolean.parseBoolean(_geographicalSourceReliable);
			
			// Traitement de la date de creation avec le moteur Regex
			// La date de reference pour cette recherche sera la date d'aujourd'hui
			TemporalRegexResults creationDateRegexResults = temporalRegexEngine.TemporalRegexSearch(temporalCreationData);
			creationDateRegexResults.FormatResults(Calendar.getInstance().getTime());
			
			logger.debug("Dates de creation :");
			creationDateRegexResults.DisplayResults();
			creationDate = creationDateRegexResults.GetStartDate();
			
			logger.debug("Date de creation :" + creationDate.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * @return true si le document est considere comme urgent, false sinon
	 */
	public boolean IsUrgent() {
		return urgent;
	}
	
	/**
	 * @return La reference du document
	 */
	public String GetReference() {
		return reference;
	}
	
	/**
	 * @return Le titre du document
	 */
	public String GetTitle() {
		return title;
	}
	
	/**
	 * @return L'URI du document
	 */
	public String GetUri() {
		return uri;
	}
	
	/**
	 * @return Les categories auxquelles sont attachees le document d'apres la source
	 */
	public List<String> GetCategoriesList() {
		return categoriesList;
	}
	
	/**
	 * @return true si l'analyse des categories ne doit pas etre effectuee, false sinon
	 */
	public boolean IsCategoriesSourceReliable() {
		return categoriesSourceReliable;
	}
	
	/**
	 * @return La date de creation du document
	 */
	public Date GetCreationDate() {
		return creationDate;
	}
	
	/**
	 * @return true si l'analyse temporelle ne doit pas etre effectuee, false sinon
	 */
	public boolean IsTemporalSourceReliable() {
		return temporalSourceReliable;
	}
	
	/**
	 * @return Les donnees relatives a la date de debut de l'evenement selon la source
	 */
	public String GetTemporalSourceStartData() {
		return temporalSourceStartData;
	}
	
	/**
	 * @return Les donnees relatives a la date de fin de l'evenement selon la source
	 */
	public String GetTemporalSourceEndData() {
		return temporalSourceEndData;
	}
	
	/**
	 * @return true si l'analyse geographique ne doit pas etre effectuee, false sinon
	 */
	public boolean IsGeographicalSourceReliable() {
		return geographicalSourceReliable;
	}
	
	/**
	 * @return Les donnees geographiques brutes liees a la source du document
	 */
	public String GetGeographicalSourceData() {
		return geographicalSourceData;
	}
	
	/**
	 * @return Le texte du document
	 */
	public String GetData() {
		return data;
	}
	
	/**
	 * Constructeur par defaut de la classe Document
	 * @param inputDocumentPath Le chemin d'acces du document
	 */
	InputDocument(String inputDocumentPath) {
		// Initialisation des attributs
		this.file = new File(inputDocumentPath);
		
		try {
			temporalRegexEngine = new TemporalRegexEngine();
		} catch (FileNotFoundException e) {
			logger.error("L'un des fichiers contenant les listes Regex n'a pas pu etre trouve");
		}
	}
}
