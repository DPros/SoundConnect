package com.soundconnect.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.soundconnect.Beans.Conference;
import com.soundconnect.Beans.User;

@Repository
public class ConferenceDaoImpl implements ConferenceDAO{

	final String getConference = "SELECT * FROM conferences WHERE id=?";
	final String deleteConference = "DELETE FROM conferences WHERE id=?";
	final String createConference = "INSERT INTO conferences (name, audios, audioStarted, password) VALUES (?, ?, ?, ?, ?)";
	final String updateConferenceAudios = "UPDATE conferences SET audios=? WHERE id=?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	AudioDao audioDao;
	
	@Override
	public Conference getConferenceById(long id) {
		try{
			return jdbcTemplate.queryForObject(getConference, new Object[]{id}, new ConferenceMapper());
		}catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	@Override
	public void deleteConferenceById(long id) {
		jdbcTemplate.update(deleteConference, id);
	}

	@Override
	public long createConference(Conference conference) throws SQLException {
		PreparedStatement preparedStatement = null;
		long id = 0;
		try{
			preparedStatement = jdbcTemplate.getDataSource().getConnection()
					.prepareStatement(createConference, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, conference.getName());
			preparedStatement.setArray(2, jdbcTemplate.getDataSource().getConnection().createArrayOf("bigint", conference.getAudioIds()));
			preparedStatement.setTimestamp(3, conference.getSongStarted());
			preparedStatement.setString(4, conference.getPassword());
			preparedStatement.executeQuery();
			if(preparedStatement.getGeneratedKeys().next()){
				id = preparedStatement.getGeneratedKeys().getLong(1);
			}
		}catch (SQLException e){
			throw e;
		}finally{
			if(preparedStatement!=null)
				preparedStatement.close();
		}
		return id;
	}

	@Override
	public void updateConferenceAudios(Conference conference) throws DataAccessException, SQLException {
		jdbcTemplate.update(updateConferenceAudios, new Object[]{
				jdbcTemplate.getDataSource().getConnection().createArrayOf("bigint", conference.getAudioIds()),
				conference.getId()});  
	}
	
	private class ConferenceMapper implements RowMapper<Conference>{
		@Override
		public Conference mapRow(ResultSet rs, int rowNum) throws SQLException {
			Conference conference = new Conference(rs.getString("name"), 
					rs.getString("password"), new HashSet<User>(), 
					audioDao.getAudioByConference(rs.getLong("id")), 
					rs.getTimestamp("audioStarted"));
			return conference;
		}
	}
}
