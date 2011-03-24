package com.inpranet.habit.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.inpranet.habit.model.WeeklyHabit;



@Repository("weeklyHabitDao")
public class JdbcWeeklyHabitDAO extends JdbcDaoSupport implements IWeeklyHabitDAO {

//	public String DateToTimeString(Date dateTime) {
//		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
//		return formatter.format(dateTime);
//	}
//	
//	public String DateToString(Date dateTime) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		return formatter.format(dateTime);
//	}
//	
//	public int CountOccurrence(int dayOfWeek, int userId, Date dateTime, int zoneId, String interest) {
//		String sCurrentTime = DateToTimeString(dateTime); //probleme de date,heure
//		String sql = "SELECT COUNT(\"userID\") FROM habitude.geo_tempo_events WHERE \"userID\" ="+userId+" AND \"zoneID\" ="+zoneId+" AND interest ='"+interest+"' AND EXTRACT(DOW from \"timeIn\")="+dayOfWeek+" AND '"+sCurrentTime+"' BETWEEN \"time\"(\"timeIn\") AND \"time\"(\"timeOut\");";
//		return getJdbcTemplate().queryForInt(sql);
//	}
//	
//	public int StockWeeklyHabit(int dayOfWeek, int userId, Date dateTime, int zoneId, String interest, int nbrOcc){
//		String sCurrentTime = DateToTimeString(dateTime);
//		String sql = "INSERT INTO habitude.weekly_habit(\"userID\", \"dayOfWeek\", \"time\", \"zoneID\", interest, \"nbrOccurrence\") VALUES ("+userId+", "+dayOfWeek+", '"+sCurrentTime+"', "+zoneId+", '"+interest+"', "+nbrOcc+");";	
//		return getJdbcTemplate().update(sql);
//	}
	
	public void InsertWeeklyHabit(WeeklyHabit weeklyHabit){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getJdbcTemplate().getDataSource());
        SimpleJdbcCall simpleGetCall = new SimpleJdbcCall(jdbcTemplate);
        simpleGetCall.withProcedureName("insertweeklyhabit");
        simpleGetCall.withCatalogName("habit");
        simpleGetCall.withoutProcedureColumnMetaDataAccess();
        simpleGetCall.declareParameters(new SqlParameter("user_id", java.sql.Types.INTEGER));
        simpleGetCall.declareParameters(new SqlParameter("time_of_week", java.sql.Types.INTEGER));  
        simpleGetCall.declareParameters(new SqlParameter("zone_id", java.sql.Types.INTEGER));
        MapSqlParameterSource mapParam = new MapSqlParameterSource();
        mapParam.addValue("user_id", weeklyHabit.getmUserId());
        mapParam.addValue("time_of_week",weeklyHabit.getmDateTime() );
        mapParam.addValue("zone_id", weeklyHabit.getmZoneId());
        Map<String, Object> execute = simpleGetCall.execute(mapParam);
	}
	
}

