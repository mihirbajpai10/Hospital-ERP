package com.hospital.ERP.Services;

import com.hospital.ERP.Entity.Bill;
import com.hospital.ERP.Entity.BillItem;
import com.hospital.ERP.Repository.BillRepo;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class InvoiceService {

    @Autowired
    private BillRepo billRepo;

    public byte[] generateInvoice(Long billId) {

        Bill bill = billRepo.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("HOSPITAL INVOICE"));
            document.add(new Paragraph("----------------------------"));

            document.add(new Paragraph("Bill ID: " + bill.getId()));
            document.add(new Paragraph(
                    "Patient ID: " + bill.getPatient().getId()
            ));

            document.add(new Paragraph(
                    "Patient Name: " + bill.getPatient().getName()
            ));

            document.add(new Paragraph(
                    "Patient Email: " + bill.getPatient().getEmail()
            ));

            document.add(new Paragraph("Date: " + bill.getCreatedAt()));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Tests:"));

            for (BillItem item : bill.getItems()) {
                document.add(new Paragraph(
                        item.getTestName() + " - Rs. " + item.getPrice()
                ));
            }

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Consultation Fee: Rs. " + bill.getConsultationFee()));
            document.add(new Paragraph("Total Amount: Rs. " + bill.getTotalAmount()));

            document.add(new Paragraph("Payment Status: " + bill.getPaymentStatus()));
            document.add(new Paragraph("Payment Mode: " + bill.getPaymentMode()));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF");
        }

        return out.toByteArray();
    }
}