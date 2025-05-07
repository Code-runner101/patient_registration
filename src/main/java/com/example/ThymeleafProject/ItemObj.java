package com.example.ThymeleafProject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemObj {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String surname;
    private String firstName;
    private String patronymic;
    private int work_num;
    private int rating;

    public ItemObj() {}

    public ItemObj(String surname, String firstName, String patronymic, int work_num, int rating) {
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.work_num = work_num;
        this.rating = rating;
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

    public int getWork_num() {
        return work_num;
    }

    public void setWork_num(int work_num) {
        this.work_num = work_num;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

