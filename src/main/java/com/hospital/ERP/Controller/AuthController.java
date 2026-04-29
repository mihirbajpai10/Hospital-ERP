package com.hospital.ERP.Controller;

import com.hospital.ERP.DTO.UserDto;
import com.hospital.ERP.JWT.CustomUserDetails;
import com.hospital.ERP.JWT.JwtUtility;
import com.hospital.ERP.JWT.LoginRequest;
import com.hospital.ERP.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtility jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserDto dto) {

        userService.created(dto);

        return "User registered successfully";
    }

    /*@GetMapping("/auth/test")
    public String test() {
        return "Working";
    }*/

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        return jwtUtil.generateToken(
                user.getUsername(),
                user.getUser().getRole().name()
        );
    }
}
