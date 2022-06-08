package com.taller1SM.service;

import java.util.Optional;

import com.taller1SM.model.user.UserApp;
import com.taller1SM.model.user.UserType;


public interface UserService {
	
	
	public void save(UserApp user);

	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdministrators();

	public Iterable<UserApp> findAllOperators();



	public UserType[] getTypes();

}
