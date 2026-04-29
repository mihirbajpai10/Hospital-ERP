package com.hospital.ERP.Services;

import com.hospital.ERP.DTO.UserDto;
import com.hospital.ERP.Entity.Users;
import com.hospital.ERP.JWT.CustomUserDetails;
import com.hospital.ERP.JWT.JwtUtility;
import com.hospital.ERP.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtility jwtUtil;


    public String signup(UserDto dto) {

        Users user = new Users();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());


        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setPhoneNo(dto.getPhoneNo());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());

        user.setCreatedAT(LocalDateTime.now());
        user.setUpdatedAT(LocalDateTime.now());

        userRepo.save(user);

        return "User registered successfully";
    }


    public String login(String email, String password) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        return jwtUtil.generateToken(
                user.getUsername(),
                user.getUser().getRole().name()
        );
    }
}