package com.hospital.ERP.Services;

import com.hospital.ERP.DTO.BillRequestDto;
import com.hospital.ERP.Entity.Bill;
import com.hospital.ERP.Entity.BillItem;
import com.hospital.ERP.Entity.Doctor;
import com.hospital.ERP.Entity.TestMaster;
import com.hospital.ERP.Repository.BillRepo;
import com.hospital.ERP.Repository.TestMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    BillRepo billRepo;

    @Autowired
    TestMasterRepo masterRepo;

    public Bill createBill(BillRequestDto dto) {

        double ConsultationFee = 300;

        /*Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        double consultationFee = doctor.getConsultationFee();*/

        Bill bill = new Bill();
        bill.setPatientId(dto.getPatientId());
        bill.setConsultationFee(ConsultationFee);
        bill.setCreatedAt(LocalDateTime.now());

        List<BillItem> items = new ArrayList<>();

        double total = ConsultationFee;

        for (Long testId : dto.getTestId()) {

            TestMaster test = masterRepo.findById(testId)
                    .orElseThrow(() -> new RuntimeException("Test not found"));

            BillItem item = new BillItem();
            item.setBill(bill);
            item.setTestName(test.getTestName());
            item.setPrice(test.getPrice());

            total += test.getPrice();

            items.add(item);
        }

        bill.setItems(items);
        bill.setTotalAmount(total);

        return billRepo.save(bill);

    }
}
