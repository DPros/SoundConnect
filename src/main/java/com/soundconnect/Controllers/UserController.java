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
		userserv.unfollowUser((Long) request.getSession().getAttribute("userId"), (Long) id);
		return true;
	}
	
	@RequestMapping(value="/changename", method = RequestMethod.POST)
	public String viewName(@RequestBody String query, Model model, HttpServletRequest request){
		try {
			String name = query.substring(query.indexOf('=')+1, query.length());
			name=name.replace('+', ' ');
			userserv.updateUserName(name, (Long) request.getSession().getAttribute("userId"));
			model.addAttribute("usernamelabel", name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main";
	}
	
	@RequestMapping(value="/fetchconnect", method = RequestMethod.POST)
	public @ResponseBody Boolean fetchConnect(@RequestBody Long id, Model model, HttpServletRequest request){
		try {
			User u = userserv.getUserById(id);
			userserv.updateUserConference(u.getConference(), (Long) request.getSession().getAttribute("userId"));
			request.getSession().setAttribute("confId", u.getConference());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
