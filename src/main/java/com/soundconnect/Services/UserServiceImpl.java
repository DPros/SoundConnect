package com.soundconnect.Services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soundconnect.Beans.User;
import com.soundconnect.Dao.UserDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public User createUser(String name) throws SQLException {
		return new User(userDao.createUser(name), name, 0, null);
	}

	@Override
	public void updateUserName(String name, long id) throws SQLException {
		userDao.updateUserName(name, id);
	}

	@Override
	public void updateUserConference(long conferenceId, long userId) throws SQLException {
		userDao.updateUserConference(conferenceId, userId);
	}

	@Override
	public void addAudio(long audioId, long userId) throws SQLException {
		userDao.addAudio(audioId, userId);
	}

	@Override
	public void deleteAudio(long audioId, long userId) throws SQLException {
		userDao.deleteAudio(audioId, userId);
	}

	@Override
	public User getUserById(long id) throws SQLException {
		return userDao.getUserById(id);
	}

}
