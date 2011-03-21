package com.inpranet.habit.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.support.JdbcDaoSupport;



@Repository("weeklyHabitDao")
public class JdbcWeeklyHabitDAO extends JdbcDaoSupport implements IWeeklyHabitDAO {

	public String DateToTimeString(Date dateTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
		return formatter.format(dateTime);
	}
	
	public String DateToString(Date dateTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return formatter.format(dateTime);
	}
	
	public int CountOccurrence(int dayOfWeek, int userId, Date dateTime, int zoneId, String interest) {
		String sCurrentTime = DateToTimeString(dateTime); //probleme de date,heure
		String sql = "SELECT COUNT(\"userID\") FROM habitude.geo_tempo_events WHERE \"userID\" ="+userId+" AND \"zoneID\" ="+zoneId+" AND interest ='"+interest+"' AND EXTRACT(DOW from \"timeIn\")="+dayOfWeek+" AND '"+sCurrentTime+"' BETWEEN \"time\"(\"timeIn\") AND \"time\"(\"timeOut\");";
		return getJdbcTemplate().queryForInt(sql);
	}
	
	public int StockWeeklyHabit(int dayOfWeek, int userId, Date dateTime, int zoneId, String interest, int nbrOcc){
		String sCurrentTime = DateToTimeString(dateTime);
		String sql = "INSERT INTO habitude.weekly_habit(\"userID\", \"dayOfWeek\", \"time\", \"zoneID\", interest, \"nbrOccurrence\") VALUES ("+userId+", "+dayOfWeek+", '"+sCurrentTime+"', "+zoneId+", '"+interest+"', "+nbrOcc+");";	
		return getJdbcTemplate().update(sql);
	}
	
}

