package com.hospital.ERP.Repository;

import com.hospital.ERP.DTO.EmergencyRequestDTO;
import com.hospital.ERP.Entity.Patient;
import com.hospital.ERP.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> {

    @Query("SELECT p FROM Patient p WHERE p.user.role = :role")
    List<Patient> findByRole(@Param("role") Users.Role role);

    List<Patient> findByUser_Role(Users.Role role);

    @Query("SELECT p FROM Patient p JOIN FETCH p.user")
    List<Patient> findAllWithUser();

    Patient findByUser(Users user);

    List<Patient> findByType(String emergency);
}
