package com.elec5620.intelligentfinancial.repository;

import com.elec5620.intelligentfinancial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findByUsername(String username);
}