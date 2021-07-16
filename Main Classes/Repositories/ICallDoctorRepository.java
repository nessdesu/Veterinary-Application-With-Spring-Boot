package com.example.nesdeneme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICallDoctorRepository extends JpaRepository<CallDoctor, Long> {
    @Query(value = "SELECT c FROM CallDoctor c WHERE c.id=?1")
    CallDoctor findByHour(String hour);
}
