package com.hospital.ERP.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testName;
    private double price;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private Bill bill;

    public BillItem() {
    }

    public BillItem(Long id, String testName, double price, Bill bill) {
        this.id = id;
        this.testName = testName;
        this.price = price;
        this.bill = bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
