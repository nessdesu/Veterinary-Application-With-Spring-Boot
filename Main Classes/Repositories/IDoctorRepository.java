package com.example.nesdeneme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDoctorRepository extends JpaRepository<Doctors, Long> {
    @Query(value = "SELECT d FROM Doctors d WHERE d.id=?1")
    Doctors findById(String id);

}
