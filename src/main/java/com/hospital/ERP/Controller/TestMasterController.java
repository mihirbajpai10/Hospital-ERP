package com.hospital.ERP.Controller;

import com.hospital.ERP.Entity.TestMaster;
import com.hospital.ERP.Services.TestMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestMasterController {

    @Autowired
    TestMasterService masterService;

    /*@PreAuthorize("hasRole('SUPERADMIN')")*/
    @PostMapping("/add")
    public TestMaster addTest(@RequestBody TestMaster master) {
        return masterService.add(master);
    }
}
