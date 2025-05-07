package com.example.ThymeleafProject.Appointment;

import com.example.ThymeleafProject.employee.Doctor;
import com.example.ThymeleafProject.patient.Patient;
import com.example.ThymeleafProject.schedule.Schedule;
import jakarta.persistence.*;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Appointment() {}

    public Appointment(Patient patient, Doctor doctor, Schedule schedule) {
        this.patient = patient;
        this.doctor = doctor;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

