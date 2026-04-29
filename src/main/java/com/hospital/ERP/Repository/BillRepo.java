package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Integer> {
    
}
