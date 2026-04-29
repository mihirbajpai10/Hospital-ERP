package com.hospital.ERP.Repository;

import com.hospital.ERP.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    /*List<Users> email(String email);*/
}
