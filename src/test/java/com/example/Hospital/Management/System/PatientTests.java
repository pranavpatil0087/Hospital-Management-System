package com.example.Hospital.Management.System;

import com.example.Hospital.Management.System.dto.BloodGroupCountResponseEntity;
import com.example.Hospital.Management.System.entity.Patient;
import com.example.Hospital.Management.System.entity.type.BloodGroupType;
import com.example.Hospital.Management.System.repository.PatientRepository;
import com.example.Hospital.Management.System.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
@SpringBootTest
public class PatientTests {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;
    @Test
    public void testPatientRepository(){
   List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
}
@Test
    public void testTransactionalMethods(){
//        Patient patient = patientService.getPatientById(1L);
//    System.out.println(patient);

//    Patient patient = patientRepository.findByName("Diya Patel");
//    System.out.println(patient);
   // List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of(1988,3,15),"Diya"+"dishant.verma@example.com");

//    List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);
//
//    for(Patient patient: patientList) {
//        System.out.println(patient);

//    List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1993,3,15));
//
//    for(Patient patient: patientList) {
//        System.out.println(patient);
//}

    // PROJECTION Type of Query
    List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
    for(BloodGroupCountResponseEntity bloodGroupCountResponse: bloodGroupList) {
        System.out.println(bloodGroupCountResponse);
    }
    // PAGINATION Type of Query
    // giving only 2 rows (jitna chahiye utna jyada nahi)                            idhar batao kitna
    Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(0,2));

    for(Patient patient: patientList) {
        System.out.println(patient);
    }
}

}
