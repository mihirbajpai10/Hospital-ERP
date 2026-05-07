package com.hospital.ERP.Controller;

import com.hospital.ERP.DTO.BillRequestDto;
import com.hospital.ERP.DTO.PaymentDto;
import com.hospital.ERP.Entity.Bill;
import com.hospital.ERP.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @PostMapping("/create")
    public Bill createBills(@RequestBody BillRequestDto dto) {
        return billService.createBill(dto);
    }

    @PostMapping("/pay/{billId}")
    public Bill payBill(@PathVariable Long billId, @RequestBody PaymentDto request) {
        return billService.payBill(billId, request.getPaymentMode());
    }

}
