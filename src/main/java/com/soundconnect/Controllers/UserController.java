package com.soundconnect.Controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.soundconnect.Beans.Audio;
import com.soundconnect.Beans.User;
import com.soundconnect.Services.UserService;

@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	UserService userserv;
	
	@RequestMapping(value="/followings", method=RequestMethod.POST)
	public String listFollowedByMe(Model model, HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("user");
		model.addAttribute("followedusers", userserv.getFollowings(u.getId()));
		return "includes/following";
	}
}
