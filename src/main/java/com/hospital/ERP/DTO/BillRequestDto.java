package com.hospital.ERP.DTO;

import java.util.List;

public class BillRequestDto {

    private int patientId;
    private List<Long> testId;

    public BillRequestDto() {
    }

    public BillRequestDto(int patientId, List<Long> testId) {
        this.patientId = patientId;
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public List<Long> getTestId() {
        return testId;
    }

    public void setTestId(List<Long> testId) {
        this.testId = testId;
    }
}
