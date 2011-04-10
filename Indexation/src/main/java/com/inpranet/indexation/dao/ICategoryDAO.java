package com.inpranet.indexation.dao;

import java.util.List;

import com.inpranet.core.model.Category;

/**
 * Inteface DAO pour la gestion de l'acces au donnes relatives aux categories
 * @author Stephane
 */
public interface ICategoryDAO {
	/**
	 * Recherche des categories ayant un certain identifiant
	 * @param id L'identifiant sur lequel faire une recherche
	 * @return La liste des categories correspondant aux parametres de la recherche
	 */
	public List<Category> findCategoryById(int id);
	
	/**
	 * Recherche des categories ayant un certain nom
	 * @param name Le nom de la categorie sur lequel faire une recherche
	 * @return La liste des categories correspondant aux parametres de la recherche
	 */
	public List<Category> findCategoryByName(String name);
}
