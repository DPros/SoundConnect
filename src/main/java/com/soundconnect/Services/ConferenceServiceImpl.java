package com.soundconnect.Services;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Beans.Conference;
import com.soundconnect.Dao.AudioDao;
import com.soundconnect.Dao.ConferenceDAO;

@Service
@EnableScheduling
public class ConferenceServiceImpl implements ConferenceService{

	private Map<Long, Conference> cache = new HashMap<Long, Conference>();
	
	@Autowired
	ConferenceDAO conferenceDAO;
	
	@Autowired
	AudioDao audioDao;
	
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
		Iterator<Map.Entry<Long, Conference>> iterator = cache.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Long, Conference> entry = iterator.next();
			if(entry.getValue().getSongStarted() < Calendar.getInstance().getTimeInMillis()-86400000)iterator.remove();
		}
	}

	@Override
	public void updateConferenceAudios(Conference conference) throws DataAccessException, SQLException {
		conferenceDAO.updateConferenceAudios(conference);
		cache.put(conference.getId(), conference);
	}

	@Override
	public List<Audio> getConferenceAudio(Conference conference) {
		if(conference.getTracks()==null)conference.setTracks(audioDao.getAudioByConference(conference.getId()));
		return conference.getTracks();
	}

	@Override
	public Conference playConference(long id) throws DataAccessException, SQLException {
		Conference conference = getConferenceById(id);
		getConferenceAudio(conference);
		if(!conference.getTracks().isEmpty()){
			long now = Calendar.getInstance().getTimeInMillis();
			System.out.println("Started: "+conference.getSongStarted());
			System.out.println("now: "+now);
			System.out.println("-----------");
			if(conference.getSongStarted() < now - conference.getTracks().get(0).getLength()*1000){
				if(conference.getSongStarted()!=0){
					conference.getTracks().remove(0);
					conferenceDAO.updateConferenceAudios(conference);
				}
				conference.setSongStarted(now);
			}
		}
		return conference;
	}
}
