package com.inpranet.zone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.inpranet.zone.model.Zone;


/**
 * dilazgdu
 * @author sopra
 *
 */
/*
@Repository("zoneDao")
public class JdbcZoneDao extends JdbcDaoSupport implements IZoneDAO {

	public class ZoneRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Zone zone = new Zone(rs.getString("isInside"));
			return zone;
		}
	}
	
	public List<Zone>  getZones(int lon, int lat) {
		String sql = "select geog && ST_GeomFromText('SRID=4326;POINT(4.8768 45.7766)') as isInside from decoupage3";
		return getJdbcTemplate().query(sql, new ZoneRowMapper());
	}
}*/
