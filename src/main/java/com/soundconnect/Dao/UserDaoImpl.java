package com.soundconnect.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.AudioService;
import com.soundconnect.Services.ConferenceService;

@Repository
public class UserDaoImpl implements UserDao{

	final String getUser = "SELECT * FROM users WHERE id=?";
	final String deleteUser = "DELETE FROM users WHERE id=?";
	final String createUser = "INSERT INTO users (name) VALUES (?)";
	final String updateUserName = "UPDATE users SET name=? WHERE id=?";
	final String updateUserConference = "UPDATE users SET conference=? WHERE id=?";
	final String addAudio = "UPDATE users SET audios=(array_append(SELECT audio FROM users WHERE id=?), ?) WHERE id=?";
	final String deleteAudio = "UPDATE users SET audios=array_remove(audios, ?) WHERE id=?";
	
	@Autowired
	ConferenceService conferenceService;
	
	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	@Override
	public User getUserById(long id) throws SQLException {
		List<User> res = jdbcTemplate.query(getUser, new Object[]{id}, new UserMapper());
		if(!res.isEmpty())	return res.get(0);
		return null;
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
			preparedStatement = jdbcTemplate.getDataSource().getConnection()
					.prepareStatement(createUser, Statement.RETURN_GENERATED_KEYS);
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
	public void updateUserName(String name, long id) throws SQLException {
		jdbcTemplate.update(updateUserName, name, id);
	}
	
	@Override
	public void updateUserConference(long conferenceId, long userId) throws SQLException {
		jdbcTemplate.update(updateUserConference, conferenceId, userId);
	}
	
	@Override
	public void addAudio(long audioId, long userId) throws SQLException {
		jdbcTemplate.update(addAudio, audioId, userId);
	}
	
	@Override
	public void deleteAudio(long audioId, long userId) throws SQLException{
		jdbcTemplate.update(deleteAudio, audioId, userId);
	}
	
	 class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User(rs.getLong("id"), rs.getString("name"), 
					conferenceService.getConferenceById(rs.getLong("conference")), 
					null);
			return user;
		}
		
	}
}
