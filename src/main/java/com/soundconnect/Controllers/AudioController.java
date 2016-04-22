package com.soundconnect.Controllers;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Beans.Conference;
import com.soundconnect.Beans.User;
import com.soundconnect.Services.AudioService;
import com.soundconnect.Services.ConferenceService;
import com.soundconnect.Services.UserService;
import com.soundconnect.Services.VKAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes({ "user" })
public class AudioController {

	@Autowired
	AudioService audioserv;
	@Autowired
	VKAudioService service;
	@Autowired
	UserService userserv;
	@Autowired
	ConferenceService confserv;

	@RequestMapping("/recommended")
	public String getRecommended(Model model, HttpServletRequest request) {
		List<Audio> list = audioserv.getAudioByUser((Long) request.getSession().getAttribute("userId"));
		List<Long> alreadyAdded = new ArrayList<Long>(list.size());
		int maxcount = 0, nn = 0;
		for (int i = 0; i < list.size(); i++) {
			alreadyAdded.add(list.get(i).getId());
			int count = 0;
			for (int j = i; j < list.size(); j++)
				if (list.get(i).getGenre() == list.get(j).getGenre())
					count++;

			if (maxcount < count) {
				maxcount = count;
				nn = i;
			}
		}
		List<Audio> list2 = audioserv.getRecommended(list.get(nn).getGenre());
		list=new ArrayList<Audio>(list2.size());
		Audio a;
		for(int i=0; i<list2.size(); ++i){
			a=list2.get(i);
			//my crunches
			a.setArtist(a.getArtist().replace(" ", "_"));
			a.setTitle(a.getTitle().replace(" ", "_"));
			if(!alreadyAdded.contains(a.getId()))
				list.add(a);
		}
		model.addAttribute("recaudios", list);
		return "includes/recommended";
	}

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

	@RequestMapping(value = "/list-music", method = { RequestMethod.POST })
	public String listMusic(Model model, HttpServletRequest request) {
		List<Audio> list = new LinkedList<Audio>();
		for (Audio a : audioserv.getAudioByUser((Long) request.getSession().getAttribute("userId"))) {
			a.setTitle(a.getTitle().replaceAll(" ", "_"));
			a.setArtist(a.getArtist().replaceAll(" ", "_"));
			list.add(a);
		}
		model.addAttribute("myaudios", list);
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
				// crunches =(
				audio.setTitle(audio.getTitle().replaceAll("_", " "));
				audio.setArtist(audio.getArtist().replaceAll("_", " "));
				// analyze song before creating!!
				audioserv.createAudio(audio);
				// add audio to user here!!!
			} catch (SQLException e1) {
				return false;
			}
		try {
			userserv.addAudio(audio.getId(), (Long)req.getSession().getAttribute("userId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Current session info:\nuserId: " + (Long)req.getSession().getAttribute("userId") + "\nconfId: "
				+ (Long)req.getSession().getAttribute("confId"));

		System.out.println("ADDING AUDIO TO USER; AID=" + audio.getId());
		return true;
	}

	@RequestMapping("/add-to-conference")
	public @ResponseBody Boolean addAudioToConference(@RequestBody Audio audio, Model model, HttpServletRequest req) {
		if (audio == null) {
			System.out.println("null pointer audio request");
			return false;
		}
		// check whether we already have this audio in our DB
		// update if needed
		if (audioserv.getAudioById(audio.getId()) == null)
			try {
				audio.setTitle(audio.getTitle().replaceAll("_", " "));
				audio.setArtist(audio.getArtist().replaceAll("_", " "));
				// analyze song before creating!!
				audioserv.createAudio(audio);
			} catch (SQLException e1) {
				return false;
			}
		try {
			Conference conf = confserv
					.getConferenceById((Long)req.getSession().getAttribute("confId"));
			confserv.getConferenceAudio(conf);
			if(conf.getTracks().isEmpty())conf.setSongStarted(0);
			conf.addAudioToConference(audio);
			confserv.updateConferenceAudios(conf);
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
			userserv.deleteAudio(audio.getId(), (Long)req.getSession().getAttribute("userId"));
		} catch (NumberFormatException e) {
		} catch (SQLException e) {
			return false;
		}
		System.out.println("Current session info:\nuserId: " + (Long) req.getSession().getAttribute("userId") + "\nconfId: "
				+ (Long)req.getSession().getAttribute("confId"));

		System.out.println("DELETING AUDIO FROM USER; AID=" + audio.getId());
		return true;
	}
}
