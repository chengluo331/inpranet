package com.inpranet.habit.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;


import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.inpranet.habit.model.WeeklyHabit;
import com.inpranet.habit.service.HabitService;

@Repository("weeklyHabitDao")
public class JdbcWeeklyHabitDAO implements IWeeklyHabitDAO {
	/** Logger */ 
	static Logger log = Logger.getLogger(JdbcWeeklyHabitDAO.class.getName());
	
	private JdbcTemplate jdbcTemplate;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
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
		//JdbcTemplate jdbcTemplate = new JdbcTemplate(getJdbcTemplate().getDataSource());
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
	
	public int GetIdInterval(Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		final String SELECT_ID_INTERVAL = "SELECT id FROM habit.interval WHERE day_of_week=? " +
				"AND hour_of_day=? AND begin_minute<=? AND end_minute>?";
		int idInterval = 0;
		try {
			idInterval = jdbcTemplate.queryForInt(SELECT_ID_INTERVAL, new Object[] {
					c.get(Calendar.DAY_OF_WEEK), c.get(Calendar.HOUR_OF_DAY), 
					c.get(Calendar.MINUTE), c.get(Calendar.MINUTE)});
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return idInterval;
	}
	
}

