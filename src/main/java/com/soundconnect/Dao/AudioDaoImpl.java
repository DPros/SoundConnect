package com.soundconnect.Dao;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.soundconnect.Beans.Audio;

@Repository
public class AudioDaoImpl implements AudioDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	final String getAudioByUser = "SELECT * FROM audios WHERE id IN (SELECT audios FROM users WHERE id=?)";
	final String deleteAudio = "DELETE FROM audios WHERE id=?";
	final String createAudio = "INSERT INTO audios (name) VALUES (?)";
	
	@Override
	public List<Audio> getAudioByUser(long id) throws SQLException {
		return jdbcTemplate.query(getAudioByUser, new Object[]{id}, new AudioMapper());
	}

	@Override
	public void deleteAudioById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long createAudio(Audio audio) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	private class AudioMapper implements RowMapper<Audio>{

		@Override
		public Audio mapRow(ResultSet rs, int rowNum) throws SQLException {
			Audio audio = null;
			try {
				audio = new Audio(rs.getLong("id"), new URL("https://"+rs.getString("url")),
						rs.getLong("length"), rs.getString("title"), rs.getString("artist"),
						rs.getLong("genre"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return audio;
		}
		
	}
}
