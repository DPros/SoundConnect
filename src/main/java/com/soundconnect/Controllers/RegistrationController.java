package com.soundconnect.Controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.UserService;
import com.soundconnect.Utils.MD5;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	@Autowired
	private UserService userv;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doRegister(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		System.out.println("POST handler!");
		try {
			if (userv.getUserByUName(request.getParameter("username")) != null) {
				model.addObject("error", "Username must be unique. Try another one");
				model.setViewName("register");
				return model;
			}
			if (request.getParameter("password").length()<6) {
				model.addObject("error", "Too short password! Password should be 6 symbols at least");
				model.setViewName("register");
				return model;
			}
			if (!request.getParameter("password").equals(request.getParameter("passconf"))) {
				model.addObject("error", "Password confirmation failed!");
				model.setViewName("register");
				return model;
			}
			User u = new User((long) 0, request.getParameter("name"), (long) 0, null, null,
					request.getParameter("username"), (short) 1);
			u.setPassword(MD5.getHash(request.getParameter("password")));
			userv.createUser(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addObject("msg", "You've been registered successfully.");
		model.setViewName("login");
		return model;
	}

}
