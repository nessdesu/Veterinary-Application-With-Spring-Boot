package com.example.nesdeneme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT r FROM Role r WHERE r.id=?1")
    Role findByName(String name);
}
