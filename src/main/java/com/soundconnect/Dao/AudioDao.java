package com.soundconnect.Dao;

import java.sql.SQLException;
import java.util.List;

import com.soundconnect.Beans.Audio;

public interface AudioDao {

	List<Audio> getAudioByUser(long id) throws SQLException;
	
	void deleteAudioById(long id);
	
	long createAudio(Audio audio) throws SQLException;
}
