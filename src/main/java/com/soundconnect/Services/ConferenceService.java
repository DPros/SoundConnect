package com.soundconnect.Services;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import com.soundconnect.Beans.Conference;

public interface ConferenceService {

	Conference getConferenceById(long id);
	
	void createConference(Conference conference) throws SQLException;
	
	void updateConferenceAudios(Conference conference)throws DataAccessException, SQLException ;
	
	
}
