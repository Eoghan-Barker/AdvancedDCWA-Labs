package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.Q1and2Service;

@RestController
@RequestMapping("/bye")
public class Q1and2ByeController {
	
	@Autowired
	Q1and2Service ms;
	
	@GetMapping
	public String home() {
		return "Goodbye" + ms.pinGenerator();
	}
	
	@PostMapping
	public String poster() {
		return "Not implemented yet";
	}
}
