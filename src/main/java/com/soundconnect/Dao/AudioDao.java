package com.soundconnect.Dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.soundconnect.Beans.Audio;

public interface AudioDao {

	List<Audio> getAudioByUser(long id);
	
	List<Audio> getAudioByConference(long id);
	
	List<Audio> getRecomendedAudio(long genre);
	
	void deleteAudioById(long id) throws EmptyResultDataAccessException;
	
	long createAudio(Audio audio) throws SQLException;
	
	Audio getAudioById(long id);
}
