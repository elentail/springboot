package org.roadking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EvalController {

	@RequestMapping(value="/eval")
	public String list(){
		return "index";
	}
}
