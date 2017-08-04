package com.maxcheung.funddata.service;

import java.util.List;

import com.maxcheung.funddata.domain.UserData;

public interface UserService {
	
	UserData findById(long id);
	
	UserData findByName(String name);
	
	void saveUser(UserData user);
	
	void updateUser(UserData user);
	
	void deleteUserById(long id);

	List<UserData> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(UserData user);
	
}