package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.dto.PatientResponse;
import com.example.Hospital.Management.System.entity.Patient;
import com.example.Hospital.Management.System.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//  Marks this class as a Service Layer.
//  Contains business logic between Controller and Repository
@RequiredArgsConstructor
//Lombok automatically creates a constructor for final fields.
public class PatientService {
    //PatientService handles all patient-related operations like adding, viewing, updating, and deleting (CRUD) patients.
    // It receives requests from the Controller, performs the required logic, interacts with the Repository
    // to access the database, and returns the result back to the Controller.

    public final PatientRepository patientRepository;
    //Repository object is injected automatically through constructor injection.

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
    //Makes the whole update operation a single database transaction.
    //If anything fails, all changes are rolled back.
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