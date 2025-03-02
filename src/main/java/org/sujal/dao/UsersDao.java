package org.sujal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sujal.entity.User;

@Repository
public interface UsersDao extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);
	
	int deleteByUserName(String userName);	
}