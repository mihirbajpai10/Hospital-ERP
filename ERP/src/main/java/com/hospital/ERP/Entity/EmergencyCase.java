package com.hospital.ERP.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;


@Entity
public class EmergencyCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emergencyCode;



    private Long doctorId;


    public enum EmergencyStatus {

        ADMITTED,
        ASSIGNED_DOCTOR,
        UNDER_TREATMENT,
        STABILIZED,
        DISCHARGED
    }

    @Enumerated(EnumType.STRING)
    private EmergencyStatus status;

    private LocalTime arrivalTime;

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    private boolean unknownPatient;

    @ManyToOne
    @JoinColumn(name = "bed_id")
    private Bed bed;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmergencyCode() {
        return emergencyCode;
    }

    public void setEmergencyCode(String emergencyCode) {
        this.emergencyCode = emergencyCode;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }


    public EmergencyStatus getStatus() {
        return status;
    }

    public void setStatus(EmergencyStatus status) {
        this.status = status;
    }

    public boolean isUnknownPatient() {
        return unknownPatient;
    }

    public void setUnknownPatient(boolean unknownPatient) {
        this.unknownPatient = unknownPatient;
    }
}



