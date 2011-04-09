package com.inpranet.habit.dao;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.inpranet.habit.model.Position;
import com.inpranet.habit.service.HabitService;

/**
 * Implementation de la couche d'acces aux donnees relatives aux positions
 * @author Wenshao, Yiquan
 */
public class PositionDAO implements IPositionDAO{

	/** Logger */
	static Logger log = Logger.getLogger(HabitService.class.getName());
	
	/** Spring jdbcTemplate pour gérer l'accès à la base de données */
	private JdbcTemplate jdbcTemplate;
	
	/** 
	 * Set la source de données définie dans inpranet-data.xml 
	 * @param dataSource La source de données
	 */
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
    
    public void createPosition(Position position) throws SQLException {
		final String INSERT_POSITION = "INSERT INTO " + TABLE_POSITION + "(user_id, date_time, point) " +
				"VALUES (?, ?, ST_GeographyFromText('SRID=4326;POINT(" + 
				position.getLongitude() + " " + position.getLatitude() + ")'))";
		jdbcTemplate.update(INSERT_POSITION, new Object[] {
					position.getUserId(), position.getTime()});
		}
}