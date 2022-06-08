package com.taller1SM.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1SM.model.user.UserApp;
import com.taller1SM.model.user.UserType;

@Repository
public interface  UserRepository extends CrudRepository<UserApp,Long> {

	
List<UserApp> findByName(String name);
	
	List<UserApp> findByType(UserType patient);
	
	List<UserApp> findByUsername(String username);
}
