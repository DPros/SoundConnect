package com.soundconnect.Services;

import java.sql.SQLException;
import java.util.List;

import com.soundconnect.Beans.Audio;

public interface AudioService {

	List<Audio> getAudioByUser(long id);
	
	List<Audio> getAudioByConference(long id);
	
	Audio getAudioById(long id);
	
	void createAudio(Audio audio) throws SQLException;
	
	void deleteAudio(long id);
}
