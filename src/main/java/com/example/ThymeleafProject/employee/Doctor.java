package com.example.ThymeleafProject.employee;

import com.example.ThymeleafProject.Appointment.Appointment;
import com.example.ThymeleafProject.schedule.Schedule;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Doctor extends Employee{

    String position;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private List<Schedule> schedules;

    public Doctor(){}

    public Doctor(String surname, String firstName, String patronymic, String passportSer, String passportNum, String snils, String inn, String phoneNum, String email, String position) {
        super(surname, firstName, patronymic, passportSer, passportNum, snils, inn, phoneNum, email);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
