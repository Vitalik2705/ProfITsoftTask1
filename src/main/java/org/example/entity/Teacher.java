package org.example.entity;

public class Teacher {
    private String name;
    private String surname;
    private Subject subject;
    private String cities;

    public Teacher(String name, String surname, Subject subject, String cities) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }
}