package com.example.nesdeneme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.email=?1")
    User findByEmail(String email);

}
