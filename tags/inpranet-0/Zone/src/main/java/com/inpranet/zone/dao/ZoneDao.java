package com.inpranet.zone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.inpranet.core.model.Interest;
import com.inpranet.core.model.Zone;



@Repository("zoneDao")
public class ZoneDao extends JdbcDaoSupport implements IZoneDAO {

	public class ZoneRowMapper implements RowMapper<Zone> {
		public Zone mapRow(ResultSet rs, int rowNum) throws SQLException {
			Zone zone = new Zone(rs.getInt("id"), new Interest());
			return zone;
		}
	}
	
	public Collection<Zone>  getZones(double longitude , double latitude) {
		String sql = "select * from zone.zone where geog && " +
				"ST_GeomFromText('SRID=4326;POINT(" + longitude + " " +  latitude + ")')";
		return getJdbcTemplate().query(sql, new ZoneRowMapper());
	}
}
