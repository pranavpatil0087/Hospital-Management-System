    package com.example.Hospital.Management.System.service;

    import com.example.Hospital.Management.System.entity.Appointment;
    import com.example.Hospital.Management.System.entity.Doctor;
    import com.example.Hospital.Management.System.entity.Patient;
    import com.example.Hospital.Management.System.repository.AppointmentRepository;
    import com.example.Hospital.Management.System.repository.DoctorRepository;
    import com.example.Hospital.Management.System.repository.PatientRepository;
    import jakarta.transaction.Transactional;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class AppointmentService {

        private final AppointmentRepository appointmentRepository;
        private final DoctorRepository doctorRepository;
        private final PatientRepository patientRepository;

        public List<Appointment> getAllAppointments() {
            return appointmentRepository.findAll();
        }

        public Appointment getAppointmentById(Long id) {
            return appointmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        }

        @Transactional
        public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

            if (appointment.getId() != null && appointment.getId() > 0)
                throw new IllegalArgumentException("Appointment should not have an id");

            // Check if doctor is already booked at this time
            boolean conflict = appointmentRepository.existsByDoctorIdAndAppointmentTime(
                    doctorId, appointment.getAppointmentTime());
            if (conflict)
                throw new IllegalArgumentException("Doctor is already booked at this time slot");

            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            patient.getAppointments().add(appointment);

            return appointmentRepository.save(appointment);
        }

        public void deleteAppointment(Long id) {
            if (!appointmentRepository.existsById(id))
                throw new RuntimeException("Appointment not found with id: " + id);
            appointmentRepository.deleteById(id);
        }
    }