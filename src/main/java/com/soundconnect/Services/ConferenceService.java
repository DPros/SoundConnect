package com.soundconnect.Services;

import org.springframework.stereotype.Service;

import com.soundconnect.Beans.Conference;

public interface ConferenceService {

	Conference getConferenceById(long id);
	
	void createConference(Conference conference);
	
	
}
