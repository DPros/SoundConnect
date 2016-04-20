package com.soundconnect.Controllers;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.UserService;
import com.soundconnect.Utils.MD5;

@Controller
@SessionAttributes({ "user" })
public class AuthController {
	@Autowired
	private UserService userv;
	
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String hello(Model model, HttpServletRequest request) {
			return "auth";	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String createSession(Model model, HttpServletRequest request) {
		System.out.println(((User)request.getSession().getAttribute("user")).getUsername());
		return "auth";
	}

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
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
	public ModelAndView vkLogin(@RequestBody String uname, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String name = "User #"+uname;
		System.out.println(name+'+'+uname);
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		User u = null;
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
			e.printStackTrace();
		}
		//session
		 request.getSession().setAttribute("user", u);
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return model;

	}

	@RequestMapping(value = "/login", method ={ RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		model.setViewName("login");
		return model;

	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;

	}
	
	@RequestMapping("/test")
	public String test(HttpServletRequest req, SessionStatus status) throws ServletException {
		return "VkTest";
	}

	@RequestMapping("/logout")
	public RedirectView logOut(HttpServletRequest req, SessionStatus status) throws ServletException {
		status.setComplete();
		return new RedirectView("redirect:/auth");
	}
}