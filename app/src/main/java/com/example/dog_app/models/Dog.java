package com.example.dog_app.models;

import java.util.UUID;

public class Dog {

    
    private String ownerId;
    private String id;
    private String image;
    private String name;
    private String breed;
    private String gender;
    private int age;
    private double price;
    private boolean vaccinated;


    // New Dog constructor
    public Dog(String ownerId,String name, String image, String breed, String gender, int age, double price, boolean vaccinated) {
        this.name = name;
        this.image = image;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.price = price;
        this.vaccinated = vaccinated;

        this.ownerId = ownerId;
        this.id = UUID.randomUUID().toString();
    }

    public Dog(String ownerId,String id, String name, String image, String breed, String gender, int age, double price, boolean vaccinated) {
        this.name = name;
        this.image = image;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.price = price;
        this.vaccinated = vaccinated;
        this.id = id;
        this.ownerId = ownerId;
    }

    public Dog() {

    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Dog{" +
                "image='" + image + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", price=" + price +
                ", vaccinated=" + vaccinated +
                '}';
    }
}
