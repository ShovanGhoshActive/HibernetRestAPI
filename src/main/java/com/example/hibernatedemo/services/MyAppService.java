package com.example.hibernatedemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hibernatedemo.entity.User;

@Service
public interface MyAppService {
	
	public  List<User> getAllUser();
}
