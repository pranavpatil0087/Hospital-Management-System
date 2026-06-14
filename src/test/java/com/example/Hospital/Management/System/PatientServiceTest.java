package com.example.Hospital.Management.System;

import com.example.Hospital.Management.System.dto.PatientResponse;
import com.example.Hospital.Management.System.entity.Patient;
import com.example.Hospital.Management.System.repository.PatientRepository;
import com.example.Hospital.Management.System.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void shouldGetPatientById() {

        Patient patient = Patient.builder()
                .id(1L)
                .name("Pranav")
                .email("pranav@gmail.com")
                .build();

        when(patientRepository.findById(1L))
                .thenReturn(Optional.of(patient));

        PatientResponse response =
                patientService.getPatientById(1L);

        assertEquals("Pranav", response.getName());
    }
    @Test
    void shouldThrowExceptionWhenPatientNotFound() {

        when(patientRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> patientService.getPatientById(99L));

        assertEquals(
                "Patient not found with id: 99",
                exception.getMessage()
        );
    }
}