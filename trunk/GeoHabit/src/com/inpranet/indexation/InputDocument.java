package com.inpranet.indexation;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import com.inpranet.util.TemporalRegexEngine;
import com.inpranet.util.TemporalRegexResults;

/**
 * Classe qui fournit des methodes pour gerer les E/S sur les documents
 * @author Stephane
 */
public class InputDocument {
	/**
	 * Fichier representant le document traite (chemin d'acces, etc.)
	 */
	private File file;
	
	/**
	 * Arbre DOM correspondant au document XML traite
	 */
	private Document document;
	
	// Donnees brutes extraites du document
	private String _urgent;
	private String id;
	private String title;
	private String uri;
	private String temporalCreationData;
	private String _temporalSourceReliable;
	private String temporalSourceStartData;
	private String temporalSourceEndData;
	private String _geographicalSourceReliable;
	private String geographicalSourceData;
	private String data;
	
	// Interpretation des elements lus
	private boolean urgent;
	private Date creationDate;
	private boolean temporalSourceReliable;
	private boolean geographicalSourceReliable;
	
	/**
	 * Teste si le document est valide d'un point de vue syntaxique
	 * @return true si le document est valide, false sinon
	 */
	public boolean IsValid() {
		// Teste si le fichier existe
		if (!file.exists()) {
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
			
			// Lecture des metadonnees
			_urgent = xPath.evaluate("//@urgent", document.getDocumentElement());
			id = xPath.evaluate("//metaData/id/text()", document.getDocumentElement());
			title = xPath.evaluate("//metaData/title/text()", document.getDocumentElement());
			uri = xPath.evaluate("//metaData/uri/text()", document.getDocumentElement());
			temporalCreationData = xPath.evaluate("//metaData/temporalCreationData/text()", document.getDocumentElement());
			_temporalSourceReliable = xPath.evaluate("//metaData/temporalSourceData/@temporalSourceReliable", document.getDocumentElement());
			temporalSourceStartData = xPath.evaluate("//metaData/temporalSourceData/temporalSourceStartData/text()", document.getDocumentElement());
			temporalSourceEndData = xPath.evaluate("//metaData/temporalSourceData/temporalSourceEndData/text()", document.getDocumentElement());
			_geographicalSourceReliable = xPath.evaluate("//metaData/geographicalSourceData/@geographicalSourceReliable", document.getDocumentElement());
			geographicalSourceData = xPath.evaluate("//metaData/geographicalSourceData/text()", document.getDocumentElement());
			
			// Lecture du texte du document
			data = xPath.evaluate("//data/text()", document.getDocumentElement());
			
			// Interpretation de la valeur des elements lus
			urgent = Boolean.parseBoolean(_urgent);
			temporalSourceReliable = Boolean.parseBoolean(_temporalSourceReliable);
			geographicalSourceReliable = Boolean.parseBoolean(_geographicalSourceReliable);
			
			// Traitement de la date de creation avec le moteur Regex
			// La date de reference pour cette recherche sera la date d'aujourd'hui
			TemporalRegexResults creationDateRegexResults = TemporalRegexEngine.TemporalRegexSearch(temporalCreationData);
			creationDateRegexResults.FormatResults(Calendar.getInstance().getTime());
			
			System.out.println("InputDocument : Dates de creation :");
			creationDateRegexResults.DisplayResults();
			System.out.println();
			
			creationDate = creationDateRegexResults.GetStartDate();
			
			System.out.println("InputDocument : Date de creation : " + creationDate.toString());
			System.out.println();
		} catch (Exception e) {
			// Certaines donnees sont manquantes
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
	 * @return L'identifiant du document
	 */
	public String GetId() {
		return id;
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
	 * @return La date de creation du document
	 */
	public Date GetCreationDate() {
		return creationDate;
	}
	
	/**
	 * @return true si l'analyse temporelle ne doit pas etre effectuee, false sinon
	 */
	public boolean GetTemporalSourceReliable() {
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
	public boolean GetGeographicalSourceReliable() {
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
	 * @param documentPath Le chemin d'acces du document
	 */
	InputDocument(String inputDocumentPath) {
		// Initialisation des attributs
		this.file = new File(inputDocumentPath);
	}
}
