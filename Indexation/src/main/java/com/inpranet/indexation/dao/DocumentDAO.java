package com.inpranet.indexation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;

import com.inpranet.core.model.Category;
import com.inpranet.core.model.Document;
import com.inpranet.indexation.service.CategoryManager;

/**
 * Implementation de la couche d'acces aux donnees relatives aux documents
 * @author Stephane
 */
public class DocumentDAO implements IDocumentDAO {
	/**
	 * Services qui gerent l'acces aux donnes relatives aux categories
	 */
	CategoryManager categoryManager = new CategoryManager();
	
	/**
	 * Cree un document dans la base
	 * @param document Le document a creer dans la base
	 */
	public void createDocument(Document document) {
		// Construction des requetes
		final String INSERT_DOCUMENT = "INSERT INTO indexation.document (id, reference, title, urgent, uri, start_date, end_date, latitude, longitude, data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexation-data.xml");
		
		// Generation de la cle primaire
		PostgreSQLSequenceMaxValueIncrementer documentIncrementer = (PostgreSQLSequenceMaxValueIncrementer) applicationContext.getBean("documentIncrementer");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
		
		// Conservation de la cle qui a ete generee
		int documentPrimaryKey = documentIncrementer.nextIntValue();
		
		// Execution de la requete
		jdbcTemplate.update(INSERT_DOCUMENT, new Object[] {documentPrimaryKey, document.getReference(), document.getTitle(), document.isUrgent(), document.getUri(), document.getStart_date(), document.getEnd_date(), document.getLatitude(), document.getLongitude(), document.getData()});
		
		// Lie le document ajoute a ses differentes categories
		final String INSERT_DOCUMENT_CATEGORY = "INSERT INTO indexation.document_category (document_id, category_id) VALUES (?, ?)";
		List<Category> categoriesList = document.getCategoriesList();
		for (int i = 0; i < categoriesList.size(); i++) {
			jdbcTemplate.update(INSERT_DOCUMENT_CATEGORY, new Object[] {documentPrimaryKey, categoriesList.get(i).getIdCategory()});
		}
	}
	
	/**
	 * Supprime un document dans la base
	 * @param document Le document a supprimer
	 */
	public void deleteDocument(Document document) {
		// TODO : Gerer la suppression de documents
	}
	
	/**
	 * Classe de construction des objets Document recuperes apres une requete
	 * @author Stephane
	 */
	public class DocumentRowMapper implements RowMapper<Document> {
		/**
		 * Classe de construction des objets Category recuperes apres une requete
		 * @author Stephane
		 */
		public class DocumentCategoryRowMapper implements RowMapper<Category> {
			/**
			 * Services qui gerent l'acces aux donnes relatives aux categories
			 */
			CategoryManager categoryManager = new CategoryManager();
			
			/**
			 * Realise le mapping entre les resultats et les objets Category
			 */
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				// On ne garde que le seul resultat trouve
				Category category = categoryManager.getCategoryById(rs.getInt("category_id")).get(0);
				return category;
			}
		}
		
		/**
		 * Realise le mapping entre les resultats et les objets Documents
		 */
		public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO : Recuperer les categories auxquelles sont attachees un document
			// Constuction de la requete pour la recuperation des categories
			final String SELECT_DOCUMENT_CATEGORY = "SELECT * FROM indexation.\"document_category\" WHERE document_id = ?";
			ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexation-data.xml");
			JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
			List<Category> categoriesList = jdbcTemplate.query(SELECT_DOCUMENT_CATEGORY, new Object[] {rs.getInt("id")}, new DocumentCategoryRowMapper());
			
			// Construction de l'objet Document
			Document document = new Document(rs.getInt("id"), rs.getString("reference"), rs.getString("title"), rs.getBoolean("urgent"), categoriesList, rs.getString("uri"), rs.getDate("start_date"), rs.getDate("end_date"), rs.getFloat("latitude"), rs.getFloat("longitude"), rs.getString("data"));
			return document;
		}
	}
	
	/**
	 * Recherche des documents qui couvrent une certaine date
	 * @param date La date a couvrir
	 */
	public List<Document> findDocumentByDate(Date date) {
		// Formattage de la date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// Construction de la requete
		final String SELECT_DOCUMENT = "SELECT * FROM indexation.\"document\" WHERE start_date <= '" + simpleDateFormat.format(date) + "' AND end_date >= '" + simpleDateFormat.format(date) + "'";
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexation-data.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
		
		// Execution de la requete
		return jdbcTemplate.query(SELECT_DOCUMENT, new DocumentRowMapper());
	}
	
	/**
	 * Recherche des documents qui correspondent a une certaine categorie
	 */
	public List<Document> findDocumentByCategory(Category category) {
		return new ArrayList<Document>();
	}
}
