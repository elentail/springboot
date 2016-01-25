package org.roadking.controller;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EvalController {

	@RequestMapping(value="/eval")
	public String list(){
		
		List<GrantedAuthority> authorities= (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(GrantedAuthority auth : authorities){
			System.out.println(auth.getAuthority());
		}
		
		
		return "index";
	}
}
