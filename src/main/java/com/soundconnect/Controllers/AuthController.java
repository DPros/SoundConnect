package com.soundconnect.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.ConferenceService;
import com.soundconnect.Services.UserService;
import com.soundconnect.Utils.MD5;

@Controller
@SessionAttributes({ "user" })
public class AuthController {
	@Autowired
	private UserService userv;
	
	@Autowired
	private ConferenceService comferenceService;
	
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String hello(Model model, HttpServletRequest request) {
			return "auth";	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String createSession(Model model, HttpServletRequest request) {
		System.out.println(((User)request.getSession().getAttribute("user")).getUsername());
		return "auth";
	}

	@RequestMapping(value =  "/welcome**", method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		User u = null;
		try {
			u = userv.getUserByUName(Long.toString((Long)request.getSession().getAttribute("userId")));
		} catch (SQLException e) {
		}
		if (u != null) {
			request.getSession().setAttribute("userId", u.getId());
			request.getSession().setAttribute("confId", u.getConference());
			
		}
		return model;

	}
	
	@RequestMapping(value = {"/vklogin" }, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Boolean vkLogin(@RequestBody String uname, HttpServletRequest request, HttpServletResponse resp, Model model) {
		User u = null;
		String name = "User #"+uname;
		try {
			u = userv.getUserByUName(uname);
		if (u == null) {
			u = new User(Long.valueOf(uname), name, (long) 0, null, null,
					uname, (short) 1);
			u.setPassword(MD5.getHash("password"));
			userv.createUser(u);
		}
			u = userv.getUserByUName(uname);
		} catch (SQLException e) {
			return false;
		}
		//session
		 request.getSession().setAttribute("userId", u.getId());
		 request.getSession().setAttribute("confId", u.getConference());
		 comferenceService.getConferenceById(u.getConference()).getUsers().add(u);
		return true;

	}

	@RequestMapping("/logout")
	public String logOut(HttpServletRequest req, SessionStatus status) throws ServletException {
		status.setComplete();
		return "index";
	}
}