package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.dto.PatientResponse;
import com.example.Hospital.Management.System.entity.Patient;
import com.example.Hospital.Management.System.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    public final PatientRepository patientRepository;

    // Get all patients
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(p -> PatientResponse.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .gender(p.getGender())
                        .birthDate(p.getBirthDate())
                        .email(p.getEmail())
                        .bloodGroup(p.getBloodGroup())
                        .build())
                .toList();
    }

    // Get single patient by id
    public PatientResponse getPatientById(Long id) {
        Patient p = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return PatientResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .gender(p.getGender())
                .birthDate(p.getBirthDate())
                .email(p.getEmail())
                .bloodGroup(p.getBloodGroup())
                .build();
    }

    // Create new patient
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Update existing patient
    @Transactional
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        existing.setName(updatedPatient.getName());
        existing.setEmail(updatedPatient.getEmail());
        existing.setGender(updatedPatient.getGender());
        existing.setBirthDate(updatedPatient.getBirthDate());
        existing.setBloodGroup(updatedPatient.getBloodGroup());

        return patientRepository.save(existing);
    }

    // Delete patient
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}