package DAO;

import com.soundconnect.Beans.Conference;

public interface ConferenceDAO {

	Conference getConferenceById(long id);
	
	void deleteConferenceById(long id);
	
	void createConference(Conference conference);
}
