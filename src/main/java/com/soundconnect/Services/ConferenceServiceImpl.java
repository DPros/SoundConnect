package com.soundconnect.Services;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.soundconnect.Beans.Conference;
import com.soundconnect.Dao.ConferenceDAO;
import com.soundconnect.Dao.ConferenceDaoImpl;

@Service
@EnableScheduling
public class ConferenceServiceImpl implements ConferenceService{

	Map<Long, Conference> cache = new HashMap<Long, Conference>();
	
	@Autowired
	ConferenceDAO conferenceDAO = new ConferenceDaoImpl();
	
	@Override
	public Conference getConferenceById(long id) {
		Conference conference = cache.get(id);
		if(conference==null){
			conference=conferenceDAO.getConferenceById(id);
			if(conference!=null)cache.put(id, conference);
		}
		return conference;
	}

	@Override
	public void createConference(Conference conference) throws SQLException {
		conference.setId(conferenceDAO.createConference(conference));
		cache.put(conference.getId(), conference);
	}
	
	@Scheduled(cron="0 0 1 * * *")
	public void collectGarbage(){
		Timestamp dayAgo = new Timestamp(Calendar.getInstance().getTimeInMillis()-86400000);
		Iterator<Map.Entry<Long, Conference>> iterator = cache.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Long, Conference> entry = iterator.next();
			if(entry.getValue().getSongStarted().before(dayAgo))iterator.remove();
		}
	}

	@Override
	public void updateConferenceAudios(Conference conference) throws DataAccessException, SQLException {
		conferenceDAO.updateConferenceAudios(conference);
		cache.put(conference.getId(), conference);
	}

}
