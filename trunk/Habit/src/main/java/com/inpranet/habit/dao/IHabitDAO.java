package com.inpranet.habit.dao;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.inpranet.zone.model.Zone;


@WebService
public interface IHabitDAO {

	@WebMethod
	public int StockRawHabit(int userId, Date currentTime, double longitude, double latitude);
	public int StockEventTimeIn(int userId, Date timeIn, int zoneId, String interest);
	public int StockEventTimeOut(int userId, Date timeOut, int zoneId);
	public List<Zone> DetermineCurrentZone(int userId);
	public List<Zone> CompareCurrentAndNewZones(int userId, List<Zone> newZones);
}