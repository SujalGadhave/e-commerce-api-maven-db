package org.sujal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sujal.entity.User;

@Repository
public interface UsersDao extends JpaRepository<User, Long>{
	
	List<User> findByLoginName(String loginName);
	
	int deleteByLoginName(String loginName);	
}