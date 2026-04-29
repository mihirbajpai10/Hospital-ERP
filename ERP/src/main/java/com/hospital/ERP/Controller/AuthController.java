package com.hospital.ERP.Controller;

import com.hospital.ERP.DTO.LoginRequest;
import com.hospital.ERP.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
    @PostMapping("/forgot-password")
    public String forgot(@RequestBody Map<String, String> req) {
        return authService.sendOtp(req.get("email"));
    }

    @PostMapping("/verify-otp")
    public String verify(@RequestBody Map<String, String> req) {
        return authService.verifyOtp(req.get("email"), req.get("otp"));
    }

    @PostMapping("/reset-password")
    public String reset(@RequestBody Map<String, String> req) {
        //System.out.println(authService.resetPassword(req.get("email"), req.get("password")));
        return authService.resetPassword(req.get("email"), req.get("password"));
    }
}
