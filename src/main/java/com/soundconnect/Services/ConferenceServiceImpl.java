package com.soundconnect.Services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soundconnect.Beans.Conference;
import com.soundconnect.Dao.ConferenceDAO;

@Service
public class ConferenceServiceImpl implements ConferenceService{

	Map<Long, Conference> map = new HashMap<Long, Conference>();
	
	@Autowired
	ConferenceDAO conferenceDAO;
	
	@Override
	public Conference getConferenceById(long id) {
		Conference conference = map.get(id);
		if(conference==null){
			conference=conferenceDAO.getConferenceById(id);
			if(conference!=null)map.put(id, conference);
		}
		return conference;
	}

	@Override
	public void createConference(Conference conference) throws SQLException {
		conference.setId(conferenceDAO.createConference(conference));
		map.put(conference.getId(), conference);
	}

}
