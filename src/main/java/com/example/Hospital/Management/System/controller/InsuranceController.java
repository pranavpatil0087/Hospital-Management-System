package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.entity.Insurance;
import com.example.Hospital.Management.System.entity.Patient;
import com.example.Hospital.Management.System.repository.InsuranceRepository;
import com.example.Hospital.Management.System.service.InsuranceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/insurances")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final InsuranceRepository insuranceRepository;

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        return ResponseEntity.ok(insuranceRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Insurance not found with id: " + id));

        return ResponseEntity.ok(insurance);
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<Patient> assignInsuranceToPatient(
            @RequestBody Insurance insurance,
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                insuranceService.assignInsuranceToPatient(
                        insurance, patientId));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Patient> removeInsuranceFromPatient(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                insuranceService.disassociateInsuranceFromPatient(
                        patientId));
    }
}