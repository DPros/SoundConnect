package com.soundconnect.Services;

import java.sql.SQLException;

import com.soundconnect.Beans.Conference;

public interface ConferenceService {

	Conference getConferenceById(long id);
	
	void createConference(Conference conference) throws SQLException;
	
	
}
