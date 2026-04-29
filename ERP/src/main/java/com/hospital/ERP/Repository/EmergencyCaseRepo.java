package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.EmergencyCase;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface EmergencyCaseRepo extends JpaRepository<EmergencyCase,Long> {

    Optional<EmergencyCase> findByEmergencyCode(String code);
}
