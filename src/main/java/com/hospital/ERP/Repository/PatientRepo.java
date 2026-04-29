package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.Patient;
import com.hospital.ERP.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
}