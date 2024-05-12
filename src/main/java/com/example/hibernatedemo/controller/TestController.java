package com.example.hibernatedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernatedemo.entity.User;
import com.example.hibernatedemo.services.MyAppService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	MyAppService myAppService;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> userAccess() {
		List<User> userList = myAppService.getAllUser();
	    return ResponseEntity.ok(userList);
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<?> adminAccess() {
		List<User> userList = myAppService.getAllUser();
	    return ResponseEntity.ok(userList);
	}
}
