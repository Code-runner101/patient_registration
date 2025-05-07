package com.example.ThymeleafProject.employee;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String surname;
    private String firstName;
    private String patronymic;
    private String passportSer;
    private String passportNum;
    private String snils;
    private String inn;
    private String phoneNum;
    private String email;

    public Employee(){}

    public Employee(String surname, String firstName, String patronymic, String passportSer, String passportNum, String snils, String inn, String phoneNum, String email) {
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.passportSer = passportSer;
        this.passportNum = passportNum;
        this.snils = snils;
        this.inn = inn;
        this.phoneNum = phoneNum;
        this.email = email;
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

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
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
}
