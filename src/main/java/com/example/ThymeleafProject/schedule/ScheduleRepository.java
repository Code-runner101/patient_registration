package com.example.ThymeleafProject.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s WHERE s.doctor.id = :doctorId AND s.status = 'available'")
    List<Schedule> findAvailableByDoctorId(@Param("doctorId") Long doctorId);
}
