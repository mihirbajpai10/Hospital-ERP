package com.hospital.ERP.Services;

import com.hospital.ERP.Entity.Users;
import com.hospital.ERP.JWT.CustomUserDetails;
import com.hospital.ERP.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;   
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new CustomUserDetails(user);
    }
}
