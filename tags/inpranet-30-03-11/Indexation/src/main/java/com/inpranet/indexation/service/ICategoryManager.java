package com.inpranet.indexation.service;

import java.util.List;

import com.inpranet.core.model.Category;

/**
 * Interface offrant des services metiers pour la gestion des objets Category
 * @author Stephane
 */
public interface ICategoryManager {
	/**
	 * Recupere des categories ayant un certain identifiant
	 * @param id L'identifiant sur lequel faire une recherche
	 */
	public List<Category> getCategoryById(int id);
	
	/**
	 * Recupere des categories ayant un certain nom
	 * @param name Le nom de la categorie sur lequel faire une recherche
	 */
	public List<Category> getCategoryByName(String name);
}
