package com.inpranet.habit.dao;

import java.sql.Timestamp;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.inpranet.habit.model.Coordinate;


@WebService
public interface IHabitDAO {

	@WebMethod
	public int StockRawHabit(int userId, Timestamp dateTime, double longitude, double latitude);
}
