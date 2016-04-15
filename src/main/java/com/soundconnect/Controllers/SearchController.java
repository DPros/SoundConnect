package com.soundconnect.Controllers;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Services.VKAudioService;
import com.soundconnect.Services.VKAudioServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@Controller
public class SearchController {
	
	@RequestMapping("/search")
//	@RequestMapping(method=RequestMethod.POST)
	public String seekAudio(@RequestBody String searchText, Model model, HttpServletRequest request){
		if(searchText!=null&&searchText.length()>0){ 	//if query is defined show response
		VKAudioService service = new VKAudioServiceImpl();
		try {
			List<Audio> audios = service.searchForAudio(URLEncoder.encode(searchText, "UTF-8"));
			model.addAttribute("audios", audios);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return "includes/findmusic";
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public String showView(Model model, HttpServletRequest request){
//		return "main";
//	}
}
