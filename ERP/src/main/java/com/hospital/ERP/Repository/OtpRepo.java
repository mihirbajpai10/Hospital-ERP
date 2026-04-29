package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends JpaRepository<Otp,Long> {

    Otp findByEmail(String email);
    void deleteByEmail(String email);
}
