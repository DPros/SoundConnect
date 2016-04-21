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
//		long conferenceId = Long.parseLong((String) request.getSession().getAttribute("confId"));
		
		//////////////////
		
		
		Conference conference = conferenceService.playConference(3);
		
		
		///////////////////////
		model.addAttribute("time", Calendar.getInstance().getTimeInMillis()-conference.getSongStarted());
		model.addAttribute("conference", conference);
		System.err.println(conference.getTracks().get(0).getOwnerId());
		return "player";
	}
}
