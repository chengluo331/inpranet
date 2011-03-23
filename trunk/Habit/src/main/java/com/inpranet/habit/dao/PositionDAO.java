package com.inpranet.habit.dao;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.inpranet.habit.model.Position;

/**
 * Implementation de la couche d'acces aux donnees relatives aux positions
 * @author Wenshao, Yiquan
 */
public class PositionDAO implements IPositionDAO{

	private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public void createPosition(Position position) {
		final String INSERT_POSITION = "INSERT INTO " + TABLE_POSITION + "(user_id, date_time, longitude, latitude) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(INSERT_POSITION, new Object[] {position.getUserId(), position.getTime(), position.getLongitude(), position.getLatitude()});
	}
}
