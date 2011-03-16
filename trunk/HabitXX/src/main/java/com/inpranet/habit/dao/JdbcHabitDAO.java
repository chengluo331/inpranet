package com.inpranet.habit.dao;

import java.sql.Timestamp;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


@Repository("habitDao")
public class JdbcHabitDAO extends JdbcDaoSupport implements IHabitDAO {
	
	public int StockRawHabit(int userId, Timestamp dateTime, double longitude, double latitude) {
	String sql = "INSERT INTO habitude.localisation_raw(userID, date_time, longitude, latitude) VALUES (1001, '2011-03-15 10:06:20', 4.8349, 45.764);";	
	return getJdbcTemplate().update(sql);
	}
	
}
