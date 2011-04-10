package com.inpranet.indexation.service;

import java.util.List;

import com.inpranet.core.model.Category;
import com.inpranet.indexation.dao.CategoryDAO;

/**
 * Implementation des services objet metier relatives aux categories
 * @author Stephane
 */
public class CategoryManager {
	/**
	 * DAO qui gere l'acces aux donnes relatives aux categories
	 */
	private CategoryDAO categoryDao = new CategoryDAO();
	
	/**
	 * Recupere des categories ayant un certain identifiant
	 * @param id L'identifiant sur lequel faire une recherche
	 * @return La liste des categories correspondant aux parametres de la recherche
	 */
	public List<Category> getCategoryById(int id) {
		return categoryDao.findCategoryById(id);
	}
	
	/**
	 * Recupere des categories ayant un certain nom
	 * @param name Le nom de la categorie sur lequel faire une recherche
	 * @return La liste des categories correspondant aux parametres de la recherche
	 */
	public List<Category> getCategoryByName(String name) {
		return categoryDao.findCategoryByName(name);
	}
}
