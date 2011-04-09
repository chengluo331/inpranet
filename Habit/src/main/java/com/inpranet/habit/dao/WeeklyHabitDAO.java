package com.inpranet.habit.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.inpranet.habit.model.WeeklyHabit;

/**
 * Implementation de la couche d'acces aux donnees relatives aux WeeklyHabit
 * @author Yiquan
 */
public class WeeklyHabitDAO implements IWeeklyHabitDAO {
	/** Logger */ 
	static Logger log = Logger.getLogger(WeeklyHabitDAO.class.getName());
	
	/** Spring jdbcTemplate pour gérer l'accès à la base de données */
	private JdbcTemplate jdbcTemplate;
	
	/** 
	 * Set la source de données définie dans inpranet-data.xml 
	 * @param dataSource La source de données
	 */
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public void createWeeklyHabit(WeeklyHabit weeklyHabit) throws SQLException{
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
				"AND hour_of_day=? AND (? BETWEEN begin_minute AND end_minute)";
		int idInterval = 0;
		try {
			idInterval = jdbcTemplate.queryForInt(SELECT_ID_INTERVAL, new Object[] {
					c.get(Calendar.DAY_OF_WEEK), c.get(Calendar.HOUR_OF_DAY), 
					c.get(Calendar.MINUTE)});
		} catch (DataAccessException e) {
			log.error("No data found");	
		}
		return idInterval;
	}
	
	
	public int DeduceIdZoneByInterest(int userId, int interestId, Date time) throws SQLException, NullPointerException  {
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
		
		// Exécution de la fonciton SQL
		int	result = simpleGetCall.executeFunction(Integer.class, mapParam);
		if (result == 0) {
			log.error("no habit found");
		}
		else {
			log.info("zone id = " + result);
		}
		return result;
	}

	
	
}

