package com.hospital.ERP.Services;

import com.hospital.ERP.DTO.BillRequestDto;
import com.hospital.ERP.Entity.*;
import com.hospital.ERP.Repository.BillRepo;
import com.hospital.ERP.Repository.TestMasterRepo;
import com.hospital.ERP.Repository.UserRepo;
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

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    EmailService emailService;

    @Autowired
    private UserRepo userRepo;


    public Bill createBill(BillRequestDto dto) {

        double ConsultationFee = 300;

        /*Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        double consultationFee = doctor.getConsultationFee();*/

        Bill bill = new Bill();
        /*bill.setPatientId(dto.getPatientId());*/

        Users patient = userRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        bill.setPatient(patient);

        bill.setConsultationFee(ConsultationFee);
        bill.setCreatedAt(LocalDateTime.now());
        bill.setPaymentStatus(Bill.PaymentStatus.PENDING);


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

    public Bill payBill(Long billId, String mode) {

        Bill bill = billRepo.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        if (bill.getPaymentStatus() == Bill.PaymentStatus.PAID) {
            throw new RuntimeException("Bill already paid");
        }

        bill.setPaymentStatus(Bill.PaymentStatus.PAID);
        bill.setPaymentMode(Bill.PaymentMode.valueOf(mode));
        bill.setPaymentTime(LocalDateTime.now());

        Bill savedBill = billRepo.save(bill);


        Users patient = bill.getPatient();

        String email = patient.getEmail();

        byte[] pdf = invoiceService.generateInvoice(billId);

        emailService.sendInvoiceEmail(email, pdf, billId);

        return savedBill;
    }
    
}
