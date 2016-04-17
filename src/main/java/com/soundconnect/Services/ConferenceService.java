package com.soundconnect.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Beans.Conference;

public interface ConferenceService {

	Conference getConferenceById(long id);
	
	void createConference(Conference conference) throws SQLException;
	
	void updateConferenceAudios(Conference conference)throws DataAccessException, SQLException ;
	
	List<Audio> getConferenceAudio(Conference conference);
}
