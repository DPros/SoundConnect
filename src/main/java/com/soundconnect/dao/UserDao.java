package com.soundconnect.dao;

import java.sql.SQLException;

import com.soundconnect.Beans.User;

public interface UserDao {
	
	User getUserById(long id) throws SQLException;
	
	void deleteUserById(long id);
	
	long createUser(String name) throws SQLException;

	void updateUser(User user) throws SQLException;
}
