package com.hospital.ERP.Services;

import com.hospital.ERP.DTO.*;
import com.hospital.ERP.Entity.*;
import com.hospital.ERP.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private MedicalHistoryRepo medicalHistoryRepo;

    @Autowired
    private BedRepo bedRepo;

    @Autowired
    private EmergencyCaseRepo emergencyCaseRepo;

    public Patient createPatient(PatientDTO dto) {

        Users user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals(Users.Role.PATIENT)) {
            throw new RuntimeException("Invalid role: Not a patient");
        }

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setAddress(dto.getAddress());
        patient.setEmergencyContact(dto.getEmergencyContact());
        patient.setType("NORMAL");

        return patientRepo.save(patient);
    }


    public PatientDTO showByRole(Patient patient) {

        PatientDTO dto = new PatientDTO();

        dto.setUserId(
                patient.getUser() != null ? patient.getUser().getId() : null
        );
        dto.setGender(patient.getGender());
        dto.setAge(patient.getAge());
        dto.setAddress(patient.getAddress());
        dto.setBloodGroup(patient.getBloodGroup());
        dto.setEmergencyContact(patient.getEmergencyContact());


        if (patient.getUser() != null) {
            UserDto userDTO = new UserDto();
            userDTO.setName(patient.getUser().getName());
            userDTO.setEmail(patient.getUser().getEmail());
            userDTO.setPhoneNo(patient.getUser().getPhoneNo());
            userDTO.setRole(patient.getUser().getRole());
            userDTO.setStatus(patient.getUser().getStatus());
            userDTO.setCreatedAT(patient.getUser().getCreatedAT());
            userDTO.setUpdatedAT(patient.getUser().getUpdatedAT());
            dto.setUser(userDTO);
        }
        return dto;
    }


    public Patient updatePatient(Long id, PatientDTO dto) {

        Patient patient = patientRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Users user = new Users();

        user.setName(patient.getUser().getName());
        user.setEmail(patient.getUser().getEmail());
        user.setPhoneNo(patient.getUser().getPhoneNo());

        userRepo.save(user);

        patient.setAge(dto.getAge());
        patient.setAddress(dto.getAddress());
        return patientRepo.save(patient);
    }

    public MedicalHistory addHistory(MedicalHistoryDTO dto) {

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        MedicalHistory history = new MedicalHistory();

        history.setDisease(dto.getDisease());
        history.setDiagnosis(dto.getDiagnosis());
        history.setPrescription(dto.getPrescription());
        history.setNotes(dto.getNotes());
        history.setVisitDate(LocalDate.now());
        history.setPatient(patient);

        return medicalHistoryRepo.save(history);
    }

    //EmergencyCase Patient Registrations and Assign Bed

//    public EmergencyCase registerEmergency(EmergencyRequestDTO dto) {
//
//        Patient patient;
//
//        if (dto.getUserId() != null) {
//
//            Users user = userRepo.findById(dto.getUserId())
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            patient = patientRepo.findByUser(user);
//
//            if (patient == null) {
//                patient = new Patient();
//                patient.setUser(user);
//                patient.setType("NORMAL");
//                patient = patientRepo.save(patient);
//            }
//
//        } else {
//
//            patient = new Patient();
//            patient.setAge(dto.getAge());
//            patient.setGender(dto.getGender());
//            patient.setType("EMERGENCY");
//
//            patient = patientRepo.save(patient);
//        }
//
//        EmergencyCase ec = new EmergencyCase();
//
//        ec.setPatient(patient);
//        ec.setEmergencyCode(generateCode(patient.getId()));
//        ec.setStatus(EmergencyCase.EmergencyStatus.ADMITTED);
//        ec.setArrivalTime(LocalTime.now());
//
//        Bed bed = bedRepo.findFirstByStatus(Bed.BedStatus.AVAILABLE);
//
//        if (bed == null) {
//            throw new RuntimeException("No beds available");
//        }
//
//        bed.setStatus(Bed.BedStatus.OCCUPIED);
//        bedRepo.save(bed);
//
//        ec.setBed(bed);
//
//        return emergencyCaseRepo.save(ec);
//    }

    public EmergencyCase createEmergencyPatient(EmergencyDTO dto) {

        EmergencyCase ec = emergencyCaseRepo.findByEmergencyCode(dto.getEmergencyCode())
                .orElseThrow(() -> new RuntimeException("Emergency case not found"));

        Patient patient = ec.getPatient();

        Users user = patient.getUser();

        if (user == null) {
            user = new Users();
        }

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPhoneNo() != null) user.setPhoneNo(dto.getPhoneNo());

        user.setRole(Users.Role.PATIENT);
        user = userRepo.save(user);

        patient.setUser(user);
        if (dto.getAge() != null) patient.setAge(dto.getAge());
        if (dto.getGender() != null) patient.setGender(dto.getGender());
        if (dto.getAddress() != null) patient.setAddress(dto.getAddress());
        if (dto.getBloodGroup() != null) patient.setBloodGroup(dto.getBloodGroup());

        patientRepo.save(patient);

        ec.setUnknownPatient(false);

        return emergencyCaseRepo.save(ec);
    }

    private String generateCode(Long id) {
        return String.format("EMR%04d", id);
    }

    public EmergencyCase updateEmergencyPatient(EmergencyDTO emergencyDTO) {

        EmergencyCase ec = emergencyCaseRepo.findById(emergencyDTO.getEmergencyCode)
                .orElseThrow(() -> new RuntimeException("Emergency case not found"));

        Patient patient = ec.getPatient();
        Users user = patient.getUser();

        if (user == null) {
            user = new Users();
        }
        user.setName(emergencyDTO.getName());
        user.setEmail(emergencyDTO.getEmail());
        user.setPhoneNo(emergencyDTO.getPhoneNo());
        user.setCreatedAT(LocalDateTime.now());
        user.setUpdatedAT(LocalDateTime.now());
        user.setRole(Users.Role.PATIENT);

        user = userRepo.save(user);
        patient.setUser(user);

        patient.setAge(emergencyDTO.getAge());
        patient.setAddress(emergencyDTO.getAddress());
        patient.setBloodGroup(emergencyDTO.getBloodGroup());

        patientRepo.save(patient);
        ec.setUnknownPatient(false);

        return emergencyCaseRepo.save(ec);
    }

    public List<EmergencyResponseDTO> getAllEmergencyPatients() {

        List<EmergencyCase> list = emergencyCaseRepo.findAll();

        List<EmergencyResponseDTO> response = new ArrayList<>();

        for (EmergencyCase ec : list) {

            EmergencyResponseDTO dto = new EmergencyResponseDTO();

            dto.setEmergencyId(ec.getId());
            dto.setEmergencyCode(ec.getEmergencyCode());
            dto.setStatus(ec.getStatus().name());

            dto.setBedNumber(ec.getBed().getBedNumber());

            Patient p = ec.getPatient();

            if (p != null) {
                dto.setPatientId(p.getId());
                dto.setAge(p.getAge());
                dto.setGender(p.getGender());

                if (p.getUser() != null) {
                    dto.setName(p.getUser().getName());
                    dto.setEmail(p.getUser().getEmail());
                    dto.setPhoneNo(p.getUser().getPhoneNo());
                }
            }
            response.add(dto);
        }
        return response;
    }


}