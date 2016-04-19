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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.UserService;

@Controller
@SessionAttributes({ "userId", "confId" })
public class AuthController {
	@Autowired
	private UserService userv;

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String hello(SecurityContextHolderAwareRequestWrapper req, Model model) {
		model.addAttribute("message", "This is data from Controller");
		model.addAttribute("session", "Current session info:<br>userId: " + req.getSession().getAttribute("userId")
				+ "<br>confId: " + req.getSession().getAttribute("confId"));
		return "auth";
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String createSession(SecurityContextHolderAwareRequestWrapper req, Model model) {
		req.getSession().setAttribute("userId", req.getParameter("userId"));
		req.getSession().setAttribute("confId", req.getParameter("confId"));
		model.addAttribute("session", "Current session info:<br>userId: " + req.getSession().getAttribute("userId")
				+ "<br>confId: " + req.getSession().getAttribute("confId"));
		return "auth";
	}

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage(SecurityContextHolderAwareRequestWrapper req, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		User u = null;
		try {
			u = userv.getUserByUName(req.getUserPrincipal().getName());
		} catch (SQLException e) {
		}
		if (u != null) {
			request.getSession().setAttribute("userId", u.getId());
			request.getSession().setAttribute("confId", u.getConference());
		}
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
			SecurityContextHolderAwareRequestWrapper req, HttpServletRequest request) {

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

	@RequestMapping("/logout")
	public RedirectView logOut(HttpServletRequest req, SessionStatus status) throws ServletException {
		status.setComplete();
		return new RedirectView("redirect:/auth");
	}
}