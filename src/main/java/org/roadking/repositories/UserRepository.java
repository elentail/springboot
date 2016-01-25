package org.roadking.repositories;

import org.roadking.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer>{
	public Users findByUsername(String name);
}
