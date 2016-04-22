package com.soundconnect.Controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.soundconnect.Beans.Conference;
import com.soundconnect.Beans.User;
import com.soundconnect.Services.ConferenceService;
import com.soundconnect.Services.UserService;
import com.soundconnect.Utils.MD5;

@Controller
@SessionAttributes({ "user"})
public class MainController {
	
	@Autowired 
	UserService userserv;
	@Autowired
	ConferenceService confserv;
	
	@RequestMapping(value={"/home", "/"})
	public ModelAndView viewMain(Model model, HttpServletRequest request){
		if(request.getSession().getAttribute("userId")==null||request.getSession().getAttribute("confId")==null)
			return new ModelAndView("redirect:/auth");
		try {
			User u = userserv.getUserById((Long) request.getSession().getAttribute("userId"));
			model.addAttribute("usernamelabel", u.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("main");
	}
	
	@RequestMapping(value="createconference")
	public String createConf(@RequestBody String query, Model model, HttpServletRequest request){
		String name = query.substring(query.indexOf('=')+1, query.length());
		name=name.replace('+', ' ');
		try {
			if(confserv.searchConference(name).isEmpty())
				confserv.createConference(new Conference(0, name, MD5.getHash("12345"), null, null, 0));
			Conference c = confserv.searchConference(name).get(0);
			userserv.updateUserConference(c.getId(), (Long) request.getSession().getAttribute("userId"));
			request.getSession().setAttribute("confId", c.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main";
	}
	
}
