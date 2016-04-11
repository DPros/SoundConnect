package Cache;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.soundconnect.Beans.Conference;
import com.soundconnect.dao.ConferenceDAO;

public class ConferenceCacher {

	@Autowired
	ConferenceDAO conferenceDAO;
	
	Map<Long, Conference> map = new TreeMap<Long, Conference>();
	
	public Conference getConferenceById(long id){
		Conference res = map.get(id);
		if(res==null){
			res = conferenceDAO.getConferenceById(id);
			map.put(id, res);
		}
		return res;
	}
}
