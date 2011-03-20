package com.inpranet.habit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.Object;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.inpranet.habit.model.Habit;
import com.inpranet.zone.model.Zone;


@Repository("habitDao")
public class JdbcHabitDAO extends JdbcDaoSupport implements IHabitDAO {
	
	public String DateToString(Date dateTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return formatter.format(dateTime);
	}
	
	public int StockRawHabit(int userId, Date dateTime, double longitude, double latitude) {
		String sCurrentTime = DateToString(dateTime);
		String sql = "INSERT INTO habitude.localisation_raw(\"userID\", date_time, longitude, latitude) VALUES (" +userId+", '"+sCurrentTime+"', "+longitude+", "+latitude+");";	
		return getJdbcTemplate().update(sql);
	}
	
	public int StockEventTimeIn(int userId, Date timeIn, int zoneId, String myInterest) {
		String sTimeIn = DateToString(timeIn);
		String sql = "INSERT INTO habitude.geo_tempo_events(\"userID\", \"timeIn\", \"timeOut\", \"zoneID\", interest) VALUES (" +userId+", '"+sTimeIn+"', NULL, "+zoneId+", '"+myInterest+"');";
		return getJdbcTemplate().update(sql);
	}
	
	public int StockEventTimeOut(int userId, Date timeOut, int zoneId) {
		String sTimeOut = DateToString(timeOut);
		String sql = "UPDATE habitude.geo_tempo_events SET \"timeOut\" = '" +sTimeOut+"' WHERE \"userID\" =" +userId+" AND \"zoneID\" ="+zoneId+";";
		return getJdbcTemplate().update(sql);
	}

	public class ZoneRowMapper implements RowMapper<Zone> {
			
		public Zone mapRow(ResultSet rs, int rowNum) throws SQLException {
			Zone zoneId = new Zone(rs.getInt("zoneID"));
			return zoneId;
		}
	}
	
	public List<Zone> DetermineCurrentZone(int userId){
		String sql = "SELECT \"zoneID\" FROM habitude.geo_tempo_events WHERE \"userID\" =" +userId+" AND \"timeOut\" is NULL ;";
		return getJdbcTemplate().query(sql, new ZoneRowMapper());
	}
	
	public List<Zone> CompareCurrentAndNewZones(int userId, List<Zone> newZones){
		List<Zone> curZones = DetermineCurrentZone(userId);
		//newZones doit etre la liste retourné par le module Zone
		List<Zone> resZones = ListUtils.subtract(curZones,newZones); 
		//attention à la méthode de comparaison utilise.. faut peut-etre utiliser equals car on compare les idZone
		return resZones;
	}
}
