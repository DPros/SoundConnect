package com.soundconnect.Controllers;


import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soundconnect.Beans.Conference;
import com.soundconnect.Beans.User;
import com.soundconnect.Services.AudioService;
import com.soundconnect.Services.ConferenceService;

@Controller
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	ConferenceService conferenceService;
	
	@Autowired
	AudioService audioService;
	
	@RequestMapping(path = "/content")
	public String getPlaying(Model model, HttpServletRequest request) throws DataAccessException, SQLException {
		long now = Calendar.getInstance().getTimeInMillis();
		Conference conference = conferenceService.playConference(((User)request.getSession().getAttribute("user")).getConference(), now);
		if((!conference.getTracks().isEmpty())&&conference.getSongStarted()+conference.getTracks().get(0).getLength()+10000<now)now=1;
		model.addAttribute("time", (now-conference.getSongStarted())/1000);
		model.addAttribute("conference", conference);
		return "player";
	}
}
