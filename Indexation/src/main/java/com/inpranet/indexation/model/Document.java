package com.inpranet.indexation.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represente un objet Document persistant
 * @author Stephane
 * @deprecated Classe remplacee par la classe du package core, {@link com.inpranet.core.model.Document}
 */
public class Document implements Serializable {
	/**
	 * Version de cet objet Document
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * L'identifiant du document
	 */
	private int id;
	
	/**
	 * Le titre du document
	 */
	private String title;
	
	/**
	 * L'URI du document
	 */
	private String uri;
	
	/**
	 * La date de debut du document
	 */
	private Date start_date;
	
	/**
	 * La date de fin du document
	 */
	private Date end_date;
	
	/**
	 * La latitude de la position spatiale du document
	 */
	private float latitude;
	
	/**
	 * La longitude de la position spatiale du document
	 */
	private float longitude;
	
	/**
	 * Le texte du document
	 */
	private String data;

	/**
	 * L'ID du document (a revoir)
	 */
	private static int generated_id = 1;
	
	
	/**
	 * Constructeur par defaut de la classe Document
	 * @param id L'identifiant du document
	 * @param title Le titre du document
	 * @param uri L'URI du document
	 * @param start_date La date de debut du document
	 * @param end_date La date de fin du document
	 * @param latitude La latitude de la position spatiale du document
	 * @param longitude La longitude de la position spatiale du document
	 * @param data Le texte du document 
	 */
	public Document(int id, String title, String uri, Date start_date, Date end_date, float latitude, float longitude, String data) {
		// Initialisation des attributs
		this.id = id;
		this.title = title;
		this.uri = uri;
		this.start_date = start_date;
		this.end_date = end_date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.data = data;
		
		// Correction de l'id
		this.id = generated_id++;
	}
	
	/**
	 * @return L'identifiant du document
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id L'identifiant du document
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return Le titre du document
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title Le titre du document
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return L'URI du document
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri L'URI du document
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return La date de debut du document
	 */
	public Date getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date La date de debut du document
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return La date de fin du document
	 */
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date La date de fin du document
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return La latitude de la position spatiale du document
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude La latitude de la position spatiale du document
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return La longitude de la position spatiale du document
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude La longitude de la position spatiale du document
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return Le texte du document
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data Le texte du document
	 */
	public void setData(String data) {
		this.data = data;
	}
}
