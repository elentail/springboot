package org.roadking.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/errors")
	public String sendError(){
		
		for(GrantedAuthority at : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
			System.out.println(at.getAuthority());
		}
		return "error/404";
	}
	
	@RequestMapping(value="/admin")
	public String sendAdmin(){
		for(GrantedAuthority at : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
			System.out.println(at.getAuthority());
		}
		return "error/404";
	}
}
