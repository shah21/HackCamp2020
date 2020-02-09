package com.curiocodes.rentcar.Models;

public class CarsModel {

    public CarsModel(){}

    private String name;
    private String gear;
    private String seats;
    private String owner;
    private String image;
    private double price;

    public CarsModel(String name, String gear, String seats, String owner, String image, double price) {
        this.name = name;
        this.gear = gear;
        this.seats = seats;
        this.owner = owner;
        this.image = image;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getGear() {
        return gear;
    }

    public String getSeats() {
        return seats;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }
}
