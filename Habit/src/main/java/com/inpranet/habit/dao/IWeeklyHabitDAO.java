package com.inpranet.habit.dao;

import java.sql.Timestamp;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.inpranet.zone.model.Zone;

@WebService
public interface IWeeklyHabitDAO {
	
	@WebMethod
	public String DateToTimeString(Date dateTime);
	public int CountOccurrence(int dayOfWeek, int userId, Date dateTime, int zoneId, String interest);
	public int StockWeeklyHabit(int dayOfWeek, int userId, Date dateTime, int zoneId, String interest, int nbrOcc);
}
