package com.hospital.ERP.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "test_master")
public class TestMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_name")
    private String testName;
    private double price;

    public TestMaster() {
    }

    public TestMaster(Long id, String testName, double price) {
        this.id = id;
        this.testName = testName;
        this.price = price;
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
}
