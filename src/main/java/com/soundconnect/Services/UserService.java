package com.soundconnect.Services;

import java.sql.SQLException;

import com.soundconnect.Beans.User;

public interface UserService {

	User createUser(String name) throws SQLException;
	
	void updateUserName(String name, long id) throws SQLException;
	
	void updateUserConference(long conferenceId, long userId) throws SQLException;
	
	void addAudio(long audioId, long userId) throws SQLException;
	
	void deleteAudio(long audioId, long userId) throws SQLException;
}
