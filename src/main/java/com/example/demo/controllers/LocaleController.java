package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class LocaleController {
	@GetMapping("/locale")
	public String locale(HttpServletRequest request) {
		String ultimoUrl = request.getHeader("referer");
		return "redirect:".concat(ultimoUrl);
	}	
}
