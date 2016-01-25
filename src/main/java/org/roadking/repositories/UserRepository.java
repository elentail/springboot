package org.roadking.repositories;

import org.roadking.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
	public User findByUsername(String name);
}
