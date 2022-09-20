package com.example.database;

public class Car {
    private int id;
    private String model;
    private String color;
    private double dpl;

    public Car(int id, String model, String color, double dpl) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.dpl = dpl;
    }

    public Car(String model, String color, double dpl) {
        this.model = model;
        this.color = color;
        this.dpl = dpl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getDpl() {
        return dpl;
    }

    public void setDpl(double dpl) {
        this.dpl = dpl;
    }
}
