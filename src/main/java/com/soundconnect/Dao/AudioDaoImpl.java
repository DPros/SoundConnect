package com.soundconnect.Dao;

import java.net.MalformedURLException;
import java.net.URL;
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

import com.soundconnect.Beans.Audio;

@Repository
public class AudioDaoImpl implements AudioDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	final String getAudioByUser = "SELECT * FROM audios WHERE ARRAY[id] && (SELECT audios FROM users WHERE id=?)";
	final String getAudioByConference = "SELECT * FROM audios WHERE id IN (SELECT audios FROM conferences WHERE id=?)";
	final String deleteAudio = "DELETE FROM audios WHERE id=?";
	final String createAudio = "INSERT INTO audios (id, url, length, title , artist, genre) VALUES (?, ?, ?, ?, ?, ?)";
	final String getAudioById = "SELECT * FROM audios WHERE id=?";
	
	@Override
	public List<Audio> getAudioByUser(long id) {
		return jdbcTemplate.query(getAudioByUser, new Object[]{id}, new AudioMapper());
	}
	
	@Override
	public List<Audio> getAudioByConference(long id) {
		return jdbcTemplate.query(getAudioByConference, new Object[]{id}, new AudioMapper());
	}

	@Override
	public void deleteAudioById(long id) {
		jdbcTemplate.update(deleteAudio, id);
	}

	@Override
	public long createAudio(Audio audio) throws SQLException {
		PreparedStatement preparedStatement = null;
		long id = 0;
		try{
			preparedStatement = jdbcTemplate.getDataSource().getConnection()
					.prepareStatement(createAudio, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, audio.getId());
			preparedStatement.setLong(2, audio.getSource());
			preparedStatement.setLong(3, audio.getLength());
			preparedStatement.setString(4, audio.getTitle());
			preparedStatement.setString(5, audio.getArtist());
			preparedStatement.setLong(6, audio.getGenre());
			preparedStatement.executeUpdate();
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
	};
	
	@Override
	public Audio getAudioById(long id) throws EmptyResultDataAccessException{
		try{
			return jdbcTemplate.queryForObject(getAudioById, new Object[]{id}, new AudioMapper());
		}catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	private class AudioMapper implements RowMapper<Audio>{

		@Override
		public Audio mapRow(ResultSet rs, int rowNum) throws SQLException {
			Audio audio = null;
			audio = new Audio(rs.getLong("id"), rs.getLong("url"),
					rs.getLong("length"), rs.getString("title"), rs.getString("artist"),
					rs.getLong("genre"));
			return audio;
		}
		
	}
}
