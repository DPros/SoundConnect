package com.soundconnect.Controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soundconnect.Beans.Conference;
import com.soundconnect.Services.ConferenceService;
import com.soundconnect.Utils.Calendar;

@Controller
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	ConferenceService conferenceService;
	
	@RequestMapping(path = "/content", method = RequestMethod.POST)
	public String getPlaying(Model model, HttpServletRequest request) {
		long conferenceId = Long.parseLong((String) request.getSession().getAttribute("confId"));
		Conference conference = conferenceService.getConferenceById(conferenceId);
		conference.getTracks();
		model.addAttribute("time", Calendar.getCurrentTime()-conference.getSongStarted());
		model.addAttribute("conference", conference);
		return "player";
	}
}
