package org.roadking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EvalController {

	@RequestMapping(value="/eval")
	public String list(){
		System.out.println("EVAL Clicked!");
		return "fragments/footer";
	}
}
