package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalHistoryRepo extends JpaRepository<MedicalHistory,Long> {

}
