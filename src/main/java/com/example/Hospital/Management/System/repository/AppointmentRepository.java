package com.example.Hospital.Management.System.repository;

import com.example.Hospital.Management.System.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentTime = :appointmentTime")
    boolean existsByDoctorIdAndAppointmentTime(@Param("doctorId") Long doctorId,
                                               @Param("appointmentTime") LocalDateTime appointmentTime);
}