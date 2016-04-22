package com.soundconnect.Controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.UserService;

@Controller
@SessionAttributes({"user"})
public class UserController {

	@Autowired
	UserService userserv;
	
	@RequestMapping(value="/followings", method=RequestMethod.POST)
	public String listFollowedByMe(Model model, HttpServletRequest request){
		model.addAttribute("followedusers", userserv.getFollowings((Long) request.getSession().getAttribute("userId")));
		return "includes/following";
	}
	
	@RequestMapping(value="/unfollow", method = RequestMethod.POST)
	public @ResponseBody Boolean unfollow(@RequestBody Long id, Model model, HttpServletRequest request){
		userserv.unfollowUser(((User)request.getSession().getAttribute("user")).getId(), (Long) id);
		return true;
	}
	
	@RequestMapping(value="/fetchconnect", method = RequestMethod.POST)
	public @ResponseBody Boolean fetchConnect(@RequestBody Long id, Model model, HttpServletRequest request){
		User u = ((User) request.getSession().getAttribute("user"));
		try {
			userserv.updateUserConference(userserv.getUserById(id).getConference(), u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
