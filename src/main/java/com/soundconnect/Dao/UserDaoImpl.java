package com.soundconnect.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.ConferenceService;

@Repository
public class UserDaoImpl implements UserDao{

	final String getUser = "SELECT * FROM users WHERE id=?";
	final String deleteUser = "DELETE FROM users WHERE id=?";
	final String createUser = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
	final String updateUserName = "UPDATE users SET name=? WHERE id=?";
	final String updateUserConference = "UPDATE users SET conference=? WHERE id=?";
	final String addAudio = "UPDATE users SET audios=(audios || ?) WHERE id=?";
	final String deleteAudio = "UPDATE users SET audios=array_erase(audios, ?) WHERE id=?";
	final String getFollowings = "SELECT * FROM users WHERE id IN(SELECT following FROM users WHERE id=?)";
	private String getUserByUName = "SELECT * FROM users WHERE username=?"; 
	
	@Autowired
	ConferenceService conferenceService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> getFollowings(long userId){
		return jdbcTemplate.query(getFollowings, new Object[]{userId}, new UserMapper());
	}
	
	@Override
	public User getUserByUName(String name) throws SQLException {
		try{
			return jdbcTemplate.queryForObject(getUserByUName, new Object[]{name}, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	@Override
	public User getUserById(long id) throws SQLException {
		try{
			return jdbcTemplate.queryForObject(getUser, new Object[]{id}, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

	@Override
	public void deleteUserById(long id) {
		jdbcTemplate.update(deleteUser, id);
	}

	@Override
	public long createUser(User user) throws SQLException {
		PreparedStatement preparedStatement = null;
		long id = 0;
		try{
			preparedStatement = jdbcTemplate.getDataSource().getConnection()
					.prepareStatement(createUser, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.executeUpdate();
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
					rs.getLong("conference"), null, null, rs.getString("username"), rs.getShort("role"));
			return user;
		}
	}

}
