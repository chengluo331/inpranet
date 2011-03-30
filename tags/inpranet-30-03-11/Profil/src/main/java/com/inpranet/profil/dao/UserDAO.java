package com.inpranet.profil.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;

/**
 * Implementation de la couche d'acces aux donnees relatives aux users
 * @author Yiquan
 */
public class UserDAO implements IUserDAO {
	/** Logger */
	static Logger log = Logger.getLogger(HabitTypeDAO.class.getName());
	
	private JdbcTemplate jdbcTemplate;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	public void createUser(User user) {
		final String INSERT_USER = "INSERT INTO " + TABLE_USER 
		+ "(login, password) VALUES (?, ?)";
		try {
		jdbcTemplate.update(INSERT_USER, new Object[] {
				user.getLogin(), user.getPassword()});
		} catch (Exception e) {
			e.printStackTrace();		
		}

	}

	/**public class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
			return user;
		}
	}*/
	
	public User getUser(String login, String password) {
		String sql = "select * from " + TABLE_USER + " where login=" + login + " AND password=" + password;
		return jdbcTemplate.queryForObject(sql, User.class);
	}

}
