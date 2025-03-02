package org.sujal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sujal.entity.Login;

@Repository
public interface UserRepo extends JpaRepository<Login, Long> {
    Optional<Login> findByLoginNameAndPassword(String loginName, String password);
}
