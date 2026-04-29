package com.hospital.ERP.Controller;

import com.hospital.ERP.DTO.BillRequestDto;
import com.hospital.ERP.Entity.Bill;
import com.hospital.ERP.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @PostMapping("/create")
    public Bill createBills(@RequestBody BillRequestDto dto) {
        return billService.createBill(dto);
    }

}
