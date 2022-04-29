package com.hartmanmark.formula1.service;

public class Racer {

    private String name;
    private String car;
    private String abbreviation;

    public Racer(String name, String car, String abbreviation) {
        this.name = name;
        this.car = car;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
