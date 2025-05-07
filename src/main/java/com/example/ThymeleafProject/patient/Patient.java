package com.example.ThymeleafProject.patient;

import com.example.ThymeleafProject.Appointment.Appointment;
import com.example.ThymeleafProject.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String surname;
    private String patronymic;
    private String passportSer;
    private String passportNum;
    private String phoneNum;
    private String email;
    private String regNum;
    private String citizenship;
    private String gender;
    private String passportType;

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Column(columnDefinition = "DATE")
    private LocalDate birthdate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    public Patient() {}

    public Patient(Long id, String firstName, String surname, String patronymic, String phoneNum, String email, String regNum) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNum = phoneNum;
        this.email = email;
        this.regNum = regNum;
    }

    public Patient(Long id, String firstName, String surname, String patronymic, String passportSer, String passportNum,
                   String phoneNum, String email, String regNum, String citizenship, String gender, String passportType, LocalDate birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passportSer = passportSer;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.email = email;
        this.regNum = regNum;
        this.citizenship = citizenship;
        this.gender = gender;
        this.passportType = passportType;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassportSer() {
        return passportSer;
    }

    public void setPassportSer(String passportSer) {
        this.passportSer = passportSer;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegNum() {
        return regNum;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "regNum='" + regNum + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

