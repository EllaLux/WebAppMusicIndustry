package org.larina.application.repo;

import org.larina.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
