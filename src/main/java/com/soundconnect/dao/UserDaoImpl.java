package com.soundconnect.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.ConferenceService;

@Repository
public class UserDaoImpl implements UserDao{

	final String getUser = "SELECT * FROM users WHERE id=?";
	final String deleteUser = "DELETE FROM users WHERE id=?";
	final String createUser = "INSERT INTO users (name) VALUES (?)";
	final String updateUser = "UPDATE users SET name=?, conference=? WHERE id=?";
	
	@Autowired
	ConferenceService conferenceService;
	
	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public User getUserById(long id) throws SQLException {
		return jdbcTemplate.query(getUser, new Object[]{id}, new UserMapper()).get(0);
	}

	@Override
	public void deleteUserById(long id) {
		jdbcTemplate.update(deleteUser, id);
	}

	@Override
	public long createUser(String name) throws SQLException {
		PreparedStatement preparedStatement = null;
		long id = 0;
		try{
			preparedStatement = dataSource
					.getConnection().prepareStatement(createUser, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, name);
			preparedStatement.executeQuery();
			if(preparedStatement.getGeneratedKeys().next()){
				id = preparedStatement.getGeneratedKeys().getLong(1);
			}
		}catch (SQLException e){
			if(preparedStatement!=null)
				preparedStatement.close();
			throw e;
		}
		if(preparedStatement!=null)
			preparedStatement.close();
		return id;
	}
	
	@Override
	public void updateUser(User user) throws SQLException {
		jdbcTemplate.update(updateUser, user.getName(), user.getConference(), user.getId());
	}
	
	 class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User(rs.getLong("id"), rs.getString("name"), 
					conferenceService.getConferenceById(rs.getLong("conference")));
			return user;
		}
		
	}
}
