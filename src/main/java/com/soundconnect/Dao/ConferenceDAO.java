package com.soundconnect.Dao;

import com.soundconnect.Beans.Conference;

public interface ConferenceDAO {

	Conference getConferenceById(long id);
	
	void deleteConferenceById(long id);
	
	long createConference(Conference conference);
	
	void updateConference(Conference conference);
}
