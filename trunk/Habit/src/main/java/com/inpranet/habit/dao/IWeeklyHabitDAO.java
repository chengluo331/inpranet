package com.inpranet.habit.dao;

import java.sql.Timestamp;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.inpranet.core.model.User;
import com.inpranet.habit.model.WeeklyHabit;

public interface IWeeklyHabitDAO {
	
//	public String DateToTimeString(Date dateTime);
//	public int CountOccurrence(int dayOfWeek, int userId, Date dateTime, int zoneId, String interest);
//	public int StockWeeklyHabit(int dayOfWeek, int userId, Date dateTime, int zoneId, String interest, int nbrOcc);
	public void InsertWeeklyHabit(WeeklyHabit weeklyHabit);
	public int GetIdInterval(Date time);
	public int DeduceIdZoneByInterest(User user, Date time);
}
