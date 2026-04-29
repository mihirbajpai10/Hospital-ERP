package com.hospital.ERP.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int patientId;
    private double ConsultationFee;
    private double totalAmount;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillItem> items;

    public Bill() {
    }

    public Bill(int id, int patientId, double consultationFee, double totalAmount, LocalDateTime createdAt, List<BillItem> items) {
        this.id = id;
        this.patientId = patientId;
        ConsultationFee = consultationFee;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getConsultationFee() {
        return ConsultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        ConsultationFee = consultationFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }
}
