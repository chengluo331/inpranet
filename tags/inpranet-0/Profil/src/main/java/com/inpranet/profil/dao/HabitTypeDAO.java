package com.inpranet.profil.dao;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.inpranet.profil.model.HabitType;

/**
 * Implementation de la couche d'acces aux donnees relatives aux habitTypes
 * @author Yiquan
 */
public class HabitTypeDAO implements IHabitTypeDAO {
	/** Logger */
	static Logger log = Logger.getLogger(HabitTypeDAO.class.getName());
	
	private JdbcTemplate jdbcTemplate;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	public void createHabitType(HabitType habitType) {
		final String INSERT_HABITTYPE = "INSERT INTO " + TABLE_HABITTYPE 
					+ "(user_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) " +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(INSERT_HABITTYPE, new Object[] {
					habitType.getUserId(), habitType.getMondayHabit(), habitType.getThursdayHabit(),
					habitType.getWednesdayHabit(), habitType.getThursdayHabit(),
					habitType.getFridayHabit(), habitType.getSaturdayHabit(), habitType.getSundayHabit()});
		} catch (Exception e) {
			e.printStackTrace();		
		}


	}

}
