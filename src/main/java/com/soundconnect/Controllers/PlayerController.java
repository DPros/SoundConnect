package com.soundconnect.Controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soundconnect.Beans.Conference;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@RequestMapping(path = "/content")
	public String getPlaying(Model model, HttpServletRequest request) {
		Conference conference = (Conference) request.getAttribute("CurrentConference");
		model.addAttribute("conference", conference);
		return "player";
	}
}
