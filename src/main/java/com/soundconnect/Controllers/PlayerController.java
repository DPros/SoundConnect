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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.soundconnect.Beans.Conference;
import com.soundconnect.Beans.User;
import com.soundconnect.Services.AudioService;
import com.soundconnect.Services.ConferenceService;

@Controller
@SessionAttributes({ "user"})
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	ConferenceService conferenceService;
	
	@Autowired
	AudioService audioService;
	
	@RequestMapping(path = "/content")
	public String getPlaying(Model model, HttpServletRequest request) throws DataAccessException, SQLException {
		long now = Calendar.getInstance().getTimeInMillis();
		System.out.println("Request by "+(Long)request.getSession().getAttribute("userId"));
		Conference conference = conferenceService.playConference((Long) request.getSession().getAttribute("confId"), now);
		model.addAttribute("time", (now-conference.getSongStarted())/1000);
		System.out.println("Gonna start at "+(now-conference.getSongStarted())/1000);
		model.addAttribute("conference", conference);
		model.addAttribute("members", conference.getUsers());
		return "includes/playerAndMembers";
	}
}
