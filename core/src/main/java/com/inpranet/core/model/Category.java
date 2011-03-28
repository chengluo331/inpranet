package com.inpranet.core.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represente un objet Category persistant
 * @author Stephane
 */
@XmlRootElement(name="category")
public class Category {
	/**
	 * L'identifiant de la categorie
	 */
	@XmlElement
	private int idCategory;
	
	/**
	 * Le nom de la categorie
	 */
	@XmlElement
	private String name;
	
	/**
	 * Constructeur par defaut sans parametre
	 */
	public Category(){}
	
	/**
	 * Constructeur par defaut de la classe Category
	 * @param idCategory L'identifiant de la categorie
	 * @param name Le nom de la categorie
	 */
	public Category(int idCategory, String name) {
		// Initialisation des attributs
		this.idCategory = idCategory;
		this.name = name;
	}
	
	/**
	 * Constructeur par defaut de la classe Category
	 * @param name Le nom de la categorie
	 */
	public Category(String name) {
		// Initialisation des attributs
		this.name = name;
	}
	
	/**
	 * @return L'identifiant de la categorie
	 */
	public int getIdCategory() {
		return idCategory;
	}
	
	/**
	 * @return Le nom de la categorie
	 */
	public String getName() {
		return name;
	}
}
