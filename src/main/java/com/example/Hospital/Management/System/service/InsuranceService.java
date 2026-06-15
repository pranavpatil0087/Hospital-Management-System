package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.entity.Insurance;
import com.example.Hospital.Management.System.entity.Patient;
import com.example.Hospital.Management.System.repository.InsuranceRepository;
import com.example.Hospital.Management.System.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Patient not found with id: " + patientId));

        // Save insurance first
        Insurance savedInsurance = insuranceRepository.save(insurance);

        // Create relationship
        patient.setInsurance(savedInsurance);
        savedInsurance.setPatient(patient);

        // Save patient
        patientRepository.save(patient);

        return patient;
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Patient not found with id: " + patientId));

        patient.setInsurance(null);

        patientRepository.save(patient);

        return patient;
    }
}