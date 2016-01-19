package org.roadking.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogginController {

	private Logger log = Logger.getLogger(LogginController.class);

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginPage(HttpServletRequest request,Model model){
		String referrer = request.getHeader("Referer");
		log.info("Referer page : "+referrer);
		
	    request.getSession().setAttribute("url_prior_login", referrer);
	    // some other stuff
		return "login";
	}
}
