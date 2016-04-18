package com.soundconnect.Controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soundconnect.Beans.Conference;
import com.soundconnect.Services.ConferenceService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	ConferenceService conferenceService;

	@RequestMapping(path = "/content")
	public String getPlaying(Model model, HttpServletRequest request) {
//		long conferenceId = Long.parseLong((String) request.getAttribute("confId"));
//		Conference conference = conferenceService.getConferenceById(conferenceId);
//		conference.getTracks();
//		model.addAttribute("conference", conference);
		return "main";
	}
}
