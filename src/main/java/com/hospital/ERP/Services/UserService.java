package com.hospital.ERP.Services;


import com.hospital.ERP.DTO.UserDto;
import com.hospital.ERP.Entity.Users;
import com.hospital.ERP.Repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Users created(UserDto dto) {

        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Users user = new Users();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhoneNo(dto.getPhoneNo());
        user.setUpdatedAT(LocalDateTime.now());
        user.setCreatedAT(LocalDateTime.now());

        user.setRole(dto.getRole() != null ? dto.getRole() : Users.Role.PATIENT);
        user.setStatus(Users.Status.ACTIVE);

        return userRepo.save(user);
    }




}
