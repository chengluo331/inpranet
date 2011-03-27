package com.inpranet.habit.dao;


import java.util.logging.Logger;

import javax.sql.DataSource;
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
	
	private JdbcTemplate jdbcTemplate;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	/*public void createPosition(Position position) {
		final String INSERT_POSITION = "INSERT INTO " + TABLE_POSITION + "(user_id, date_time, longitude, latitude) VALUES (?, ?, ?, ?)";
		try {
			jdbcTemplate.update(INSERT_POSITION, new Object[] {
					position.getUserId(), position.getTime(), 
					position.getLongitude(), position.getLatitude()});
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}*/
    
    public void createPosition(Position position) {
		final String INSERT_POSITION = "INSERT INTO " + TABLE_POSITION + "(user_id, date_time, point) " +
				"VALUES (?, ?, ST_GeographyFromText('SRID=4326;POINT(" + 
				position.getLongitude() + " " + position.getLatitude() + ")'))";
		try {
			jdbcTemplate.update(INSERT_POSITION, new Object[] {
					position.getUserId(), position.getTime()});
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
}