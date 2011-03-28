package com.inpranet.indexation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.inpranet.core.model.Category;

/**
 * Implementation de la couche d'acces aux donnees relatives aux categories
 * @author Stephane
 */
public class CategoryDAO {
	/**
	 * Classe de construction des objets Document recuperes apres une requete
	 * @author Stephane
	 */
	public class CategoryRowMapper implements RowMapper<Category> {
		/**
		 * Realise le mapping entre les resultats et les objets Documents
		 */
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category(rs.getInt("id"), rs.getString("name"));
			
			// Debug
			System.out.println("CategoryDao : Resultat de la recherche : (" + rs.getInt("id") + ", " + rs.getString("name") + ")");
			
			return category;
		}
	}
	
	/**
	 * Recherche des categories ayant un certain identifiant
	 * @param id L'identifiant sur lequel faire une recherche
	 */
	public List<Category> findCategoryById(int id) {
		// Debug
		System.out.println("CategoryDao : Lancement de la recherche avec " + id);
		
		// Construction de la requete
		final String SELECT_CATEGORY = "SELECT * FROM indexation.category WHERE id = ?";
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexation-data.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
		
		// Execution de la requete
		return jdbcTemplate.query(SELECT_CATEGORY, new Object[] {id}, new CategoryRowMapper());
	}
	
	/**
	 * Recherche des categories ayant un certain nom
	 * @param name Le nom de la categorie sur lequel faire une recherche
	 */
	public List<Category> findCategoryByName(String name) {
		// Debug
		System.out.println("CategoryDao : Lancement de la recherche avec " + name);
		
		// Construction de la requete
		final String SELECT_CATEGORY = "SELECT * FROM indexation.category WHERE name = '" + name + "'";
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexation-data.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
		
		// Execution de la requete
		return jdbcTemplate.query(SELECT_CATEGORY, new CategoryRowMapper());
	}
}
