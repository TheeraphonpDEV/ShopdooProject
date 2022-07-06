package com.shopdoo.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopdoo.common.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> listAll() {
		return (List<User>) repo.findAll();
	}
}
