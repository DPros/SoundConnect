package com.soundconnect.Dao;

import java.sql.SQLException;
import java.util.List;

import com.soundconnect.Beans.User;

public interface UserDao {
	
	User getUserById(long id) throws SQLException;
	
	User getUserByUName(String name) throws SQLException;
	
	void deleteUserById(long id);
	
	long createUser(User user) throws SQLException;

	void updateUserName(String name, long id) throws SQLException;
	
	void updateUserConference(long conferenceId, long userId)throws SQLException;
	
	void addAudio(long audioId, long userId) throws SQLException;
	
	void deleteAudio(long audioId, long userId) throws SQLException;
	
	List<User> getFollowings(long userId);
}
