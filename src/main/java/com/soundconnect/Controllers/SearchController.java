package com.soundconnect.Controllers;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Services.VKAudioService;
import com.soundconnect.Services.VKAudioServiceImpl;
import com.soundconnect.dao.UserDaoImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@RequestMapping(method=RequestMethod.POST)
	public String seekAudio(Model model, HttpServletRequest request){
		if(request.getParameter("query")!=null){ 	//if query is defined show response
		VKAudioService service = new VKAudioServiceImpl();
		try {
			StringBuilder audioresp = new StringBuilder();
			List<Audio> audios = service.searchForAudio(URLEncoder.encode(request.getParameter("query"), "UTF-8"));
			for(Audio au : audios){
				audioresp.append("<a href=\"");
				audioresp.append(au.getSource());
				audioresp.append("\">");
				audioresp.append(au.getArtist());
				audioresp.append(" - ");
				audioresp.append(au.getTitle());
				audioresp.append("</a><br>");
			}
			model.addAttribute("audioresp", audioresp.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return "search";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showView(Model model, HttpServletRequest request) throws SQLException{
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.createUser("DPro");
		return "search";
	}
}
