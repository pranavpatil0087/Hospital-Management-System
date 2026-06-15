package com.example.Hospital.Management.System.repository;

import com.example.Hospital.Management.System.dto.BloodGroupCountResponseEntity;
import com.example.Hospital.Management.System.entity.Patient;
import com.example.Hospital.Management.System.entity.type.BloodGroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    //Repository is the layer that directly talks to the database.
    //public interface PatientRepository extends JpaRepository<Patient, Long>
    //→ JpaRepository already gives many methods like:
    //save()
    //findById()
    //findAll()
    //deleteById()
    //existsById()
    //So we don't need to write them ourselves.

    Patient findByName(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);
    //Gets all patients having a particular blood group.
    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);
        //Finds patients born after a specific date.
    @Query("SELECT new com.example.Hospital.Management.System.dto.BloodGroupCountResponseEntity(p.bloodGroup, COUNT(p)) FROM Patient p GROUP BY p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();
//Counts patients in each blood group.
    @Query(value = "SELECT * FROM patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);
    //Uses actual SQL query.
    //SELECT * FROM patient
}