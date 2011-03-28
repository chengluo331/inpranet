package com.inpranet.habit.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.inpranet.habit.model.WeeklyHabit;

public class WeeklyHabitDAO implements IWeeklyHabitDAO {
	/** Logger */ 
	static Logger log = Logger.getLogger(WeeklyHabitDAO.class.getName());
	
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
	
	public void createWeeklyHabit(WeeklyHabit weeklyHabit){
        // Creation d'une requête pour appeler la procédure stockée
		SimpleJdbcCall simpleGetCall = new SimpleJdbcCall(jdbcTemplate)
		// Nom de la fonction sql
        .withProcedureName(FUNCTION_INSERT_HABIT)
        // Schéma
        .withCatalogName(SCHEMA_NAME)
        .withoutProcedureColumnMetaDataAccess()
        // Déclaration des paramètres
        .declareParameters(new SqlParameter("user_id", java.sql.Types.INTEGER))
        .declareParameters(new SqlParameter("time_of_week", java.sql.Types.INTEGER))
        .declareParameters(new SqlParameter("zone_id", java.sql.Types.INTEGER));
        
		// Affectation des paramètres 
        MapSqlParameterSource mapParam = new MapSqlParameterSource()
        .addValue("user_id", weeklyHabit.getmUserId())
        .addValue("time_of_week",weeklyHabit.getmDateTime())
        .addValue("zone_id", weeklyHabit.getmZoneId());
        
        // Execution de la requête
       	simpleGetCall.execute(mapParam);
        
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
		} catch (DataAccessException e) {
			e.printStackTrace();	
		}
		return idInterval;
	}
	
	
	public int DeduceIdZoneByInterest(int userId, int interestId, Date time) {
		// Creation d'une requête pour appeler la procédure stockée
		SimpleJdbcCall simpleGetCall = new SimpleJdbcCall(jdbcTemplate)
		// Nom de la fonction sql
		.withFunctionName(FUNCTION_GET_HABIT_NAME)
		// Schéma
		.withCatalogName(SCHEMA_NAME)
		.withoutProcedureColumnMetaDataAccess()
		// Déclaration des paramètres
		.declareParameters(new SqlOutParameter("idZone", java.sql.Types.INTEGER))
		.declareParameters(new SqlParameter("uid", java.sql.Types.INTEGER))
		.declareParameters(new SqlParameter("idinter", java.sql.Types.INTEGER))
		.declareParameters(new SqlParameter("pday", java.sql.Types.INTEGER))
		.declareParameters(new SqlParameter("phour", java.sql.Types.INTEGER))
		.declareParameters(new SqlParameter("pminutes", java.sql.Types.INTEGER));

		Calendar c = Calendar.getInstance();
		c.setTime(time);
		// Affectation des paramètres
		MapSqlParameterSource mapParam = new MapSqlParameterSource()
		.addValue("uid", userId)
		.addValue("idinter", interestId)
		.addValue("pday", c.get(Calendar.DAY_OF_WEEK))
		.addValue("phour", c.get(Calendar.HOUR_OF_DAY))
		.addValue("pminutes", c.get(Calendar.MINUTE));
				
		int result = 0;
		try {
			// Exécution de la fonciton SQL
			result = simpleGetCall.executeFunction(Integer.class, mapParam);
		}
		catch (NullPointerException e) {
			// Aucun résultat retourné
			log.info("no habit found");
			return 0;
		}
		return result;
	}

	
	
}

