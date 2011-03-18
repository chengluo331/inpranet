package com.inpranet.profile.dao;

import java.util.List;

import com.inpranet.profile.model.Profile;


public interface IProfileDao {

	public void saveProfile(Profile employe);
	public Profile getEmployeById(int id);
	public Profile getEmployeByLogin(String login);
	public int getEmployesCount();
	public List<Profile> getAllEmployes();
}
