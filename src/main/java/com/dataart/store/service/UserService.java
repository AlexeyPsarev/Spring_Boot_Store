package com.dataart.store.service;

import com.dataart.store.dao.UserRepository;
import com.dataart.store.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService
{
	@Autowired
	private UserRepository dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public boolean canCreate(User user)
	{
		return (dao.findByUsername(user.getUsername()) == null);
	}
	
	@Transactional
	public void save(User user)
	{
		user = dao.save(user);
	}
	
	@Transactional(readOnly = true)
	public boolean authenticate(String username, String rawPassword)
	{
		User correctUser = dao.findByUsername(username);
		return (correctUser != null && encoder.matches(rawPassword, correctUser.getPassword()));
	}
	
	@Transactional(readOnly = true)
	public User find(String username)
	{
		return dao.findByUsername(username);
	}
	
	@Transactional(readOnly = true)
	public User find(Integer id)
	{
		return dao.findOne(id);
	}
}
