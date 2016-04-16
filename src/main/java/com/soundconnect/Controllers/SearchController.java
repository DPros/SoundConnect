package com.soundconnect.Controllers;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Beans.Conference;
import com.soundconnect.Beans.User;
import com.soundconnect.Services.AudioService;
import com.soundconnect.Services.AudioServiceImpl;
import com.soundconnect.Services.ConferenceService;
import com.soundconnect.Services.ConferenceServiceImpl;
import com.soundconnect.Services.UserService;
import com.soundconnect.Services.UserServiceImpl;
import com.soundconnect.Services.VKAudioService;
import com.soundconnect.Services.VKAudioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@Controller
public class SearchController {

	@Autowired
	AudioService audioserv;
	
	@RequestMapping("/search")
//	@RequestMapping(method=RequestMethod.POST)
	public String seekAudio(@RequestBody String searchText, Model model, HttpServletRequest request){
		if(searchText!=null&&searchText.length()>0){ 	//if query is defined show response
		VKAudioService service = new VKAudioServiceImpl();
		try {
			List<Audio> audios = service.searchForAudio(URLEncoder.encode(searchText, "UTF-8"));
			model.addAttribute("audios", audios);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		return "includes/findmusic";
	}
	
	@RequestMapping("/add-to-user")
	public @ResponseBody Boolean addAudioToUser(@RequestBody Audio audio, Model model, HttpServletRequest req){
		if(audio==null){
			System.out.println("null pointer audio request");
			return false;
		}
		//check whether we already have this audio in our DB
		//update if needed
		try{
			audioserv.getAudioById(audio.getId());
		}catch(EmptyResultDataAccessException e){
			try {
				//analyze song before creating!!
				audioserv.createAudio(audio);
			} catch (SQLException e1) {
				return false;
			}
		}
		UserService userserv = new UserServiceImpl();
		try {
			User user = userserv.getUserById(Long.valueOf((String) req.getSession().getAttribute("userId")));
			//add audio to user here!!!
			userserv.addAudio(audio.getId(), user.getId());
		} catch (NumberFormatException e) {
		} catch (SQLException e) {
			return false;
		}
		System.out.println("ADDING AUDIO TO USER; AID="+audio.getId());
		return true;
	}
	
	@RequestMapping("/add-to-conference")
	public @ResponseBody Boolean addAudioToConference(@RequestBody Audio audio, Model model, HttpServletRequest req){
		if(audio==null){
			System.out.println("null pointer audio request");
			return false;
		}
		//check whether we already have this audio in our DB
		//update if needed
		try{
			audioserv.getAudioById(audio.getId());
		}catch(NullPointerException e){
			try {
				//analyze song before creating!!
				audioserv.createAudio(audio);
			} catch (SQLException e1) {
				return false;
			}
		}
		ConferenceService confserv = new ConferenceServiceImpl();
		try {
			Conference conf = confserv.getConferenceById(Long.valueOf((String) req.getSession().getAttribute("userId")));
			//add audio to conference here!!!
			confserv.updateConferenceAudios(conf);
		} catch (NumberFormatException e) {
		} catch (SQLException e) {
			return false;
		}
		System.out.println("ADDING AUDIO TO CONFERENCE; AID="+audio.getId());
		return true;
	}
}
