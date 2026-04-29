package com.hospital.ERP.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Doctor {

    @Id
    private int id;

    @Column(name = "DoctorID")
    private int user_id;

    private String specialization;
    private String experience;
    private String qualification;

    private double consultationFee;
    private String availabilityStatus;

    public Doctor() {
    }

    public Doctor(int id, int user_id, String specialization, String experience, String qualification, double consultationFee, String availabilityStatus) {
        this.id = id;
        this.user_id = user_id;
        this.specialization = specialization;
        this.experience = experience;
        this.qualification = qualification;
        this.consultationFee = consultationFee;
        this.availabilityStatus = availabilityStatus;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
