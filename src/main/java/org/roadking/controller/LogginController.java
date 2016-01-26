package org.roadking.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.roadking.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogginController {

	private Logger log = Logger.getLogger(LogginController.class);

	@Autowired
	private CustomUserDetailService userService;

	@RequestMapping(value = "/signin")
	public String loginPage(HttpServletRequest request, Model model) {

		if (request.getSession().getAttribute("SSO") == null) {

			log.info(SecurityContextHolder.getContext().getAuthentication().getName());
			UserDetails user = userService.loadUserByUsername("admin");
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			request.getSession().setAttribute("SSO", "ADMIN");
		}

		return "redirect:/";
	}
}
