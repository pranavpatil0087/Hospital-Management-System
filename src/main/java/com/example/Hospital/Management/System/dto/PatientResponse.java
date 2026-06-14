package com.example.Hospital.Management.System.dto;

import com.example.Hospital.Management.System.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String email;
    private BloodGroupType bloodGroup;
}