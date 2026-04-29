package com.hospital.ERP.Services;

import com.hospital.ERP.Entity.TestMaster;
import com.hospital.ERP.Repository.TestMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestMasterService {

    @Autowired
    TestMasterRepo masterRepo;

    public TestMaster add(TestMaster master) {
        return masterRepo.save(master);
    }
}
