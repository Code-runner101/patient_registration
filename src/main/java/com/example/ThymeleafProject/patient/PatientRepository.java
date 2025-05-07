package com.example.ThymeleafProject.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByRegNum(String regNum);
    List<Patient> findByRegNumOrPhoneNum(String regNum, String phoneNum);
}
