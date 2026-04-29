package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedRepo extends JpaRepository<Bed,Long> {
    Bed findFirstByStatus(Bed.BedStatus bedStatus);
}
