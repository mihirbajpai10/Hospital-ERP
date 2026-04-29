package com.hospital.ERP.Controller;

import com.hospital.ERP.DTO.*;
import com.hospital.ERP.Entity.EmergencyCase;
import com.hospital.ERP.Entity.MedicalHistory;
import com.hospital.ERP.Entity.Patient;
import com.hospital.ERP.Repository.MedicalHistoryRepo;
import com.hospital.ERP.Repository.PatientRepo;
import com.hospital.ERP.Repository.UserRepo;
import com.hospital.ERP.Services.AppointmentService;
import com.hospital.ERP.Services.PatientService;
import com.hospital.ERP.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class    PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private MedicalHistoryRepo medicalHistoryRepo;

    @Autowired
    private AppointmentService appointmentService;



    @GetMapping("/role/{id}")
    public List<PatientDTO> getPatientsByRole() {
        List<Patient> patients = patientRepo.findAllWithUser();
        return patients.stream()
                .map(p -> patientService.showByRole(p))
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public Patient create(@RequestBody PatientDTO patientDTO){
        return patientService.createPatient(patientDTO);
    }

    @PutMapping("/update/{id}")
    public Patient updatePatient(@PathVariable Long id,
                                 @RequestBody PatientDTO dto) {
        return patientService.updatePatient(id, dto);
    }

    @PostMapping("/medicalhistory/add")
    public MedicalHistory addHistory(@RequestBody MedicalHistoryDTO dto) {
        return patientService.addHistory(dto);
    }

    @GetMapping("/medicalhistory/{id}")
    public List<MedicalHistory> getHistory(@PathVariable Long id) {
        return medicalHistoryRepo.findAll()
                .stream()
                .filter(h -> h.getPatient().getId().equals(id))
                .toList();
    }

    //Patient Appointment Booking

    @PostMapping("/book")
    public AppointmentDTO bookAppointment(@RequestBody AppointmentDTO dto) {
        return appointmentService.bookAppointment(dto);
    }

    @GetMapping("/all")
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDTO getById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/patients/{patientId}")
    public List<AppointmentDTO> getByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDTO> getByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    @PutMapping("/cancel/{id}")
    public AppointmentDTO cancelAppointment(@PathVariable Long id) {
        return appointmentService.cancelAppointment(id);
    }

    //Emergency Registration for patients

    @PostMapping("/emergency/register")
    public EmergencyCase registerEmergency(@RequestBody EmergencyDTO dto) {
        return patientService.createEmergencyPatient(dto);
    }

    @PutMapping("/emergency/update")
    public ResponseEntity<EmergencyCase> updateEmergency(@RequestBody EmergencyDTO dto) {
        return ResponseEntity.ok(patientService.updateEmergencyPatient(dto));
    }

    @GetMapping("/emergency/all")
    public ResponseEntity<List<EmergencyResponseDTO>> getAllEmergencyPatients() {
        return ResponseEntity.ok(patientService.getAllEmergencyPatients());
    }


}
