package com.soundconnect.Controllers;

import java.util.List;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soundconnect.Beans.Conference;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@RequestMapping(path = "/content")
	public String getPlaying(Model model, SecurityContextHolderAwareRequestWrapper request) {
		Conference conference = (Conference) request.getAttribute("CurrentConference");
		model.addAttribute("conference", conference);
		return "player";
	}
}
