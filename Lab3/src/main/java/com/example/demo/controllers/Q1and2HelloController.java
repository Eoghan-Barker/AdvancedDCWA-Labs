package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.Q1and2Service;

@RestController
@RequestMapping("/hello")
public class Q1and2HelloController {
	
	@Autowired
	Q1and2Service ms;
	
	@GetMapping
	public String home() {
		return "Hello World" + ms.weekendCheck();
	}
	
	@PostMapping
	public String poster() {
		return "Not implemented yet";
	}
}
