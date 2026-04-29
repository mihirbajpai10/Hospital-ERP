package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.Patient;
import com.hospital.ERP.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}