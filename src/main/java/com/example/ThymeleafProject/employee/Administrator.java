package com.example.ThymeleafProject.employee;

public class Administrator extends Employee{

    private String informNumber;

    public Administrator(String surname, String firstName, String patronymic, String passportSer, String passportNum, String snils, String inn, String phoneNum, String email, String informNumber) {
         super(surname, firstName, patronymic, passportSer, passportNum, snils, inn, phoneNum, email);
         this.informNumber = informNumber;
    }

    public String getInformNumber() {
        return informNumber;
    }

    public void setInformNumber(String informNumber) {
        this.informNumber = informNumber;
    }
}
