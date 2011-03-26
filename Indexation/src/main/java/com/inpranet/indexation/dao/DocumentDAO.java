package com.inpranet.indexation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;

import com.inpranet.core.model.Document;

/**
 * Implementation de la couche d'acces aux donnees relatives aux documents
 * @author Stephane
 */
public class DocumentDAO implements IDocumentDAO {
	/**
	 * Cree un document dans la base
	 * @param document Le document a creer dans la base
	 */
	public void createDocument(Document document) {
		// Construction de la requete
		final String INSERT_DOCUMENT = "INSERT INTO indexation.document (id, reference, title, urgent, category, uri, start_date, end_date, latitude, longitude, data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexation-data.xml");
		
		// Generation de la cle primaire
		PostgreSQLSequenceMaxValueIncrementer documentIncrementer = (PostgreSQLSequenceMaxValueIncrementer) applicationContext.getBean("documentIncrementer");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
		
		// Execution de la requete
		jdbcTemplate.update(INSERT_DOCUMENT, new Object[] {documentIncrementer.nextIntValue(), document.getTitle(), document.getUri(), document.getStart_date(), document.getEnd_date(), document.getLatitude(), document.getLongitude(), document.getData()});
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
		 * Realise le mapping entre les resultats et les objets Documents
		 */
		public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
			Document document = new Document(rs.getString("reference"), rs.getString("title"), rs.getBoolean("urgent"), rs.getString("category"), rs.getString("uri"), rs.getDate("start_date"), rs.getDate("end_date"), rs.getFloat("latitude"), rs.getFloat("longitude"), rs.getString("data"));
			return document;
		}
	}
	
	/**
	 * Recherche des documents qui couvrent une certaine date
	 * @param date La date a couvrir
	 */
	public List<Document> findDocumentByDate(Date date) {
		// TODO : Filtrer la recherche en fonction de la date entree
		
		// Construction de la requete
		final String SELECT_DOCUMENT = "SELECT * FROM indexation.\"document\"";		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexation-data.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
		
		// Execution de la requete
		return jdbcTemplate.query(SELECT_DOCUMENT, new DocumentRowMapper());
	}
}
