package com.example.nesdeneme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IQuestionRepository extends JpaRepository<Questions, Long> {
    @Query(value = "SELECT z FROM Questions z WHERE z.id=?1")
    Questions findByid(String id);
}
