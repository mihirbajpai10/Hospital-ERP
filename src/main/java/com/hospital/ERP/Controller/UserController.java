package com.hospital.ERP.Controller;

import com.hospital.ERP.DTO.UserDto;
import com.hospital.ERP.Entity.Users;
import com.hospital.ERP.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Users create(@RequestBody UserDto dto) {
         return userService.created(dto);
    }

    @GetMapping("/admin/test")
    public String test() {
        return "Super Admin Access";
    }

}

