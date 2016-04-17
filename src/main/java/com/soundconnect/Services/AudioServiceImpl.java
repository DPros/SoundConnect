package com.soundconnect.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Dao.AudioDao;
import com.soundconnect.Dao.AudioDaoImpl;

@Service
public class AudioServiceImpl implements AudioService{

	@Autowired
	AudioDao audioDao = new AudioDaoImpl();
	
	@Override
	public List<Audio> getAudioByUser(long id){
		return audioDao.getAudioByUser(id);
	}

	@Override
	public List<Audio> getAudioByConference(long id) {
		return audioDao.getAudioByConference(id);
	}

	@Override
	public Audio getAudioById(long id) {
		Audio a = audioDao.getAudioById(id); 
		return a;
	}

	@Override
	public void createAudio(Audio audio) throws SQLException {
		audioDao.createAudio(audio);
	}

	@Override
	public void deleteAudio(long id) {
		// TODO Auto-generated method stub
		
	}
	
}
