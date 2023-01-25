package com.abhishek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/other")
	public String home() {
		System.out.println("You are inside home handler...");
		return "home";
	}
}
