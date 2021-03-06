package com.soundconnect.Controllers;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Beans.Conference;
import com.soundconnect.Services.AudioService;
import com.soundconnect.Services.ConferenceService;
import com.soundconnect.Services.UserService;
import com.soundconnect.Services.VKAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@Controller
@SessionAttributes({ "userId", "confId" })
public class AudioController {

	@Autowired
	AudioService audioserv;
	@Autowired							
	VKAudioService service;
	@Autowired
	UserService userserv;
	@Autowired
	ConferenceService confserv;

	@RequestMapping("/search")
	// @RequestMapping(method=RequestMethod.POST)
	public String seekAudio(@RequestBody String searchText, Model model, HttpServletRequest request) {
		if (searchText != null && searchText.length() > 0) { // if query is
																// defined show
			try {
				List<Audio> audios = service.searchForAudio(URLEncoder.encode(searchText, "UTF-8"));
				model.addAttribute("audios", audios);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "includes/findmusic";
	}
	
	@RequestMapping("/list-music")
	public String listMusic(Model model, HttpServletRequest request){
		model.addAttribute("myaudios", audioserv.getAudioByUser((Long) request.getSession().getAttribute("userId")));
		return "includes/mymusic";
	}

	@RequestMapping("/add-to-user")
	public @ResponseBody Boolean addAudioToUser(@RequestBody Audio audio, Model model, HttpServletRequest req) {
		if (audio == null) {
			System.out.println("null pointer audio request");
			return false;
		}
		if (audioserv.getAudioById(audio.getId()) == null)
			try {
				// analyze song before creating!!
				audioserv.createAudio(audio);
			} catch (SQLException e1) {
				return false;
			}
		try {
			// add audio to user here!!!
			userserv.addAudio(audio.getId(), (Long) req.getSession().getAttribute("userId"));
		} catch (NumberFormatException e) {
		} catch (SQLException e) {
			return false;
		}
		System.out.println("Current session info:\nuserId: " + req.getSession().getAttribute("userId") + "\nconfId: "
				+ req.getSession().getAttribute("confId"));

		System.out.println("ADDING AUDIO TO USER; AID=" + audio.getId());
		return true;
	}

	@RequestMapping("/add-to-conference")
	public @ResponseBody Boolean addAudioToConference(@RequestBody Audio audio, Model model, HttpServletRequest req) {
		System.out.println("Current session info:\nuserId: " + req.getSession().getAttribute("userId") + "\nconfId: "
				+ req.getSession().getAttribute("confId"));
		if (audio == null) {
			System.out.println("null pointer audio request");
			return false;
		}
		// check whether we already have this audio in our DB
		// update if needed
		if (audioserv.getAudioById(audio.getId()) == null)
			try {
				// analyze song before creating!!
				audioserv.createAudio(audio);
			} catch (SQLException e1) {
				return false;
			}
		try {
			Conference conf = confserv
					.getConferenceById((Long) req.getSession().getAttribute("confId"));
			conf.addAudioToConference(audio);
			confserv.updateConferenceAudios(conf);
		} catch (NumberFormatException e) {
		} catch (SQLException e) {
			return false;
		}
		System.out.println("ADDING AUDIO TO CONFERENCE; AID=" + audio.getId());
		return true;
	}
	
	@RequestMapping("/remove-from-user")
	public @ResponseBody Boolean removeAudioFromUser(@RequestBody Audio audio, Model model, HttpServletRequest req){
		if (audio == null) {
			System.out.println("null pointer audio request");
			return false;
		}
		try {
			// add audio to user here!!!
			userserv.deleteAudio(audio.getId(), (Long) req.getSession().getAttribute("userId"));
		} catch (NumberFormatException e) {
		} catch (SQLException e) {
			return false;
		}
		System.out.println("Current session info:\nuserId: " + req.getSession().getAttribute("userId") + "\nconfId: "
				+ req.getSession().getAttribute("confId"));

		System.out.println("DELETING AUDIO FROM USER; AID=" + audio.getId());
		return true;
	}
}
