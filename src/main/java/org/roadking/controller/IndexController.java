package org.roadking.controller;

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
		return "error/404";
	}
}
