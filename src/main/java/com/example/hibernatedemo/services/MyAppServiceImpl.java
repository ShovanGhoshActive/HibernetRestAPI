package com.example.hibernatedemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hibernatedemo.entity.User;
import com.example.hibernatedemo.repository.UserRepository;

@Service
public class MyAppServiceImpl implements MyAppService {
	@Autowired
	UserRepository userRepository;
	
	
	public  List<User> getAllUser() {
		return userRepository.findAll() ;
	}

}
