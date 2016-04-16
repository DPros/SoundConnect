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
		return new User(userDao.createUser(name), name, null);
	}

	@Override
	public void updateUser(User user) throws SQLException {
		userDao.updateUser(user);
	}

}
