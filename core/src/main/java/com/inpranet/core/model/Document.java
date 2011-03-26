package com.inpranet.core.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represente un objet Document persistant
 * @author Stephane
 */
@XmlRootElement(name="document")
public class Document {
	/**
	 * La reference du document (non utilise comme cle primaire lors du stockage)
	 */
	@XmlElement
	private String reference;
	
	/**
	 * Le titre du document
	 */
	@XmlElement
	private String title;
	
	/**
	 * L'urgence du document
	 */
	@XmlElement
	private boolean urgent;
	
	/**
	 * La categorie du document
	 */
	@XmlElement
	private String category;
	
	/**
	 * L'URI du document
	 */
	@XmlElement
	private String uri;
	
	/**
	 * La date de debut du document
	 */
	@XmlElement
	private Date start_date;
	
	/**
	 * La date de fin du document
	 */
	@XmlElement
	private Date end_date;
	
	/**
	 * La latitude de la position spatiale du document
	 */
	@XmlElement
	private float latitude;
	
	/**
	 * La longitude de la position spatiale du document
	 */
	@XmlElement
	private float longitude;
	
	/**
	 * Le texte du document
	 */
	@XmlElement
	private String data;
	
	/**
	 * Constructeur par defaut sans parametre
	 */
	public Document(){}
	
	/**
	 * Constructeur par defaut de la classe Document
	 * Attention a l'ordre de passage des parametres longitude et latitude
	 * @param reference La réference du document
	 * @param title Le titre du document
	 * @param uri L'URI du document
	 * @param start_date La date de debut du document
	 * @param end_date La date de fin du document
	 * @param longitude La longitude de la position spatiale du document
	 * @param latitude La latitude de la position spatiale du document
	 * @param data Le texte du document 
	 */
	public Document(String reference, String title, boolean urgent, String category, String uri, Date start_date, Date end_date, float longitude, float latitude, String data) {
		// Initialisation des attributs
		this.reference = reference;
		this.title = title;
		this.urgent = urgent;
		this.category = category;
		this.uri = uri;
		this.start_date = start_date;
		this.end_date = end_date;
		this.longitude = longitude;
		this.latitude = latitude;
		this.data = data;
	}
	
	/**
	 * @return La reference du document
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @return Le titre du document
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return L'URI du document
	 */
	public String getUri() {
		return uri;
	}
	
	/**
	 * @return L'urgence du document
	 */
	public boolean isUrgent(){
		return urgent;
	}

	/**
	 * @return La categorie du document
	 */
	public String getCategory(){
		return category;
	}

	/**
	 * @return La date de debut du document
	 */
	public Date getStart_date() {
		return start_date;
	}

	/**
	 * @return La date de fin du document
	 */
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @return La latitude de la position spatiale du document
	 */
	public float getLatitude() {
		return latitude;
	}


	/**
	 * @return La longitude de la position spatiale du document
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * @return Le texte du document
	 */
	public String getData() {
		return data;
	}
}
