package com.example.nesdeneme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value = "SELECT a FROM Appointment a WHERE a.id=?1")
    User findByEmail(String email);
}
