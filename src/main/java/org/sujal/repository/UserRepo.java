package org.sujal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sujal.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByLoginNameAndPassword (String loginName, String password);
}