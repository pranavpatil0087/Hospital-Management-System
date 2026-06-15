package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.dto.PatientRequest;
import com.example.Hospital.Management.System.dto.PatientResponse;
import com.example.Hospital.Management.System.entity.Patient;
import com.example.Hospital.Management.System.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
@SecurityRequirement(name = "Bearer Authentication") //Tells Swagger that this controller requires a JWT Bearer Token for accessing APIs
@RestController //Tells Swagger that this controller requires a JWT Bearer Token for accessing APIs
@RequestMapping("/api/patients") //Sets the base URL for all APIs in this controller. eg: http://localhost:8080/api/patients
public class PatientController {

    //PatientController acts as the API layer of our Hospital Management System.
    // It receives HTTP requests,
    // calls the Service layer for business logic, and returns responses to the client.

    @Autowired  // dependency Injection
    //Spring automatically injects the PatientService object so the controller can call business logic methods.
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
        // Calls service layer to fetch all patients
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping   // Create Patient
    public ResponseEntity<Patient> createPatient(
            @Valid @RequestBody PatientRequest request) {
//  → Reads JSON data from request body.
//→ @Valid checks validation rules in DTO
        Patient patient = Patient.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .email(request.getEmail())
                .bloodGroup(request.getBloodGroup())
                .build();

      //  Copies values from DTO to Entity.
                    // Example
        //{
        //  "name":"Sunny",
        //  "gender":"Male",
        //  "birthDate":"2004-05-10",
        //  "email":"sunny@gmail.com",
        //  "bloodGroup":"O+"
        //}

        return ResponseEntity.ok(patientService.createPatient(patient));
        // Saves patient into database
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {
        //Gets updated patient data from request body.

        return ResponseEntity.ok(patientService.updatePatient(id, patient));
        // Updates existing patient record.
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
}