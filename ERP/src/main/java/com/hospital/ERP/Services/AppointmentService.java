package com.hospital.ERP.Services;

import com.hospital.ERP.DTO.AppointmentDTO;
import com.hospital.ERP.Entity.Appointment;
import com.hospital.ERP.Entity.Doctor;
import com.hospital.ERP.Entity.Patient;
import com.hospital.ERP.Repository.AppointmentRepo;
import com.hospital.ERP.Repository.DoctorRepo;
import com.hospital.ERP.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;


    public AppointmentDTO bookAppointment(AppointmentDTO dto) {

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appt = new Appointment();
        appt.setDoctor(doctor);
        appt.setPatient(patient);
        appt.setAppointmentDate(dto.getAppointmentDate());
        appt.setTimeSlot(dto.getTimeSlot());
        appt.setReason(dto.getReason());
        appt.setStatus(Appointment.Status.BOOKED);

        Appointment saved = appointmentRepo.save(appt);

        return mapToDTO(saved);
    }


    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appt = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        return mapToDTO(appt);
    }

    public List<AppointmentDTO> getAppointmentsByPatient(Long patientId) {
        return appointmentRepo.findByPatient_Id(patientId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepo.findByDoctor_Id(doctorId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }


    public AppointmentDTO cancelAppointment(Long id) {
        Appointment appt = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        appt.setStatus(Appointment.Status.CANCELLED);
        return mapToDTO(appointmentRepo.save(appt));
    }


    private AppointmentDTO mapToDTO(Appointment appt) {

        AppointmentDTO dto = new AppointmentDTO();

        dto.setId(appt.getId());
        dto.setAppointmentDate(appt.getAppointmentDate());
        dto.setTimeSlot(appt.getTimeSlot());
        dto.setTokenNo(appt.getTokenNo());
        dto.setStatus(appt.getStatus().name());
        dto.setReason(appt.getReason());

        dto.setDoctorId(appt.getDoctor().getId());
        dto.setPatientId(appt.getPatient().getId());

        return dto;
    }
}