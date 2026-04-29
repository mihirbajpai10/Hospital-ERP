package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
