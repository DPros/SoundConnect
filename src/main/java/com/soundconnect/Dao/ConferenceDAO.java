package com.soundconnect.Dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Beans.Conference;

public interface ConferenceDAO {

	Conference getConferenceById(long id);
	
	void deleteConferenceById(long id);
	
	long createConference(Conference conference) throws SQLException;
	
	void updateConferenceAudios(Conference conference) throws DataAccessException, SQLException;
}
