package com.soundconnect.Services;

import java.sql.SQLException;

import com.soundconnect.Beans.User;

public interface UserService {

	User createUser(String name) throws SQLException;
	
	void updateUser(User user) throws SQLException;
}
