package com.hospital.ERP.Services;

import com.hospital.ERP.DTO.LoginRequest;
import com.hospital.ERP.Entity.Otp;
import com.hospital.ERP.Entity.Users;
import com.hospital.ERP.Repository.OtpRepo;
import com.hospital.ERP.Repository.UserRepo;
import com.hospital.ERP.Security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpRepo otpRepo;


    public String login(LoginRequest req) {

        Users user = (Users) userRepo.findByEmail(req.getEmail());


        if (!user.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    public String sendOtp(String email) {

        Users user = userRepo.findByEmail(email);
        if (user == null) return "User not found";

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        Otp token = new Otp();
        token.setEmail(email);
        token.setOtp(otp);
        token.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepo.save(token);
        emailService.sendOtp(email, otp);

        return "OTP sent to email";
    }

    public String verifyOtp(String email, String otp) {

        Otp token = otpRepo.findByEmail(email);

        if (token == null) return "OTP not found";

        if (!token.getOtp().equals(otp)) return "Invalid OTP";

        if (token.getExpiryTime().isBefore(LocalDateTime.now()))
            return "OTP expired";

        return "OTP verified";
    }

    @Transactional
    public String resetPassword(String email, String password) {

        Users user = userRepo.findByEmail(email);

        if (user == null) {
            return "User not found";
        }
        user.setPassword(password);
        userRepo.save(user);
        otpRepo.deleteByEmail(email);
        return "Password updated";
    }

}
