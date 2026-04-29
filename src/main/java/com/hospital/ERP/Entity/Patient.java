package com.hospital.ERP.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Patient {

    @Id
    private int id;

    @Column(name = "Patient ID")
    private int user_id;

    private int age;
    private String gender;
    private String blood_group;
    private String address;

    public Patient() {
    }

    public Patient(int id, int user_id, int age, String gender, String blood_group, String address) {
        this.id = id;
        this.user_id = user_id;
        this.age = age;
        this.gender = gender;
        this.blood_group = blood_group;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
