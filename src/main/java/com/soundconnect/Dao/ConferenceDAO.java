package com.soundconnect.Dao;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.soundconnect.Beans.Conference;

public interface ConferenceDAO {

	Conference getConferenceById(long id);
	
	void deleteConferenceById(long id);
	
	long createConference(Conference conference) throws SQLException;
	
	void updateConferenceAudios(Conference conference) throws DataAccessException, SQLException;
}
