package com.hospital.ERP.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {

    @GetMapping("/test")
    public String test() {
        return "Super Admin Access";
    }
}
