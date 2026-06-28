package com.project.back_end.services;

import java.util.Optional;

import com.project.back_end.models.Appointment;
import com.project.back_end.repo.AppointmentRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;
import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private TokenService tokenService;

    public AppointmentService(AppointmentRepository app, PatientRepository pat, DoctorRepository doctor,
            TokenService token) {
        appointmentRepository = app;
        patientRepository = pat;
        doctorRepository = doctor;
        tokenService = token;
    }

    @Transactional
    public int bookAppointment(Appointment appointment) {
        try {
            appointmentRepository.save(appointment);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Transactional
    public ResponseEntity<Map<String, String>> updateAppointment(Appointment appointment) {
        Map<String, String> response = new HashMap<>();

        // 1) Check appointment exists
        Optional<Appointment> existingOpt = appointmentRepository.findById(appointment.getId());
        if (existingOpt.isEmpty()) {
            response.put("message", "Appointment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // 2) Validate the update
        // (Assuming validateAppointment returns boolean; adapt if your method returns
        // something else)
        boolean valid = service.validateAppointment(appointment);
        if (!valid) {
            response.put("message", "Invalid appointment update");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // 3) Save/update
        appointmentRepository.save(appointment);

        response.put("message", "Appointment updated successfully");
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<Map<String, String>> cancelAppointment(long id, String token) {
        Map<String, String> response = new HashMap<>();

        Optional<Appointment> opt = appointmentRepository.findById(appointmentId);
        if (opt.isEmpty()) {
            response.put("message", "Appointment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Appointment appointment = opt.get();

        // IMPORTANT: check patient ID match BEFORE deleting
        Long appointmentPatientId = appointment.getPatient().getId(); // adjust if needed
        if (!appointmentPatientId.equals(patientId)) {
            response.put("message", "You can only cancel your own appointment");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        appointmentRepository.delete(appointment);
        response.put("message", "Appointment canceled successfully");
        return ResponseEntity.ok(response);
    }

    @Transactional
    public Map<String, Object> getAppointment(String pname, LocalDate date, String token) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        boolean hasPatientName = pname != null && !pname.isEmpty();

        if (hasPatientName) {
            return appointmentRepository.findByDoctorIdAndPatient_NameContainingIgnoreCaseAndAppointmentTimeBetween(
                    doctorId, pname, start, end);
        }

        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                doctorId, start, end);
    }

    @Transactional
    public void changeAppointmentStatus(Long appointmentId, int status) {
        appointmentRepository.updateStatus(status, appointmentId);
    }

    // 3. **Add @Transactional Annotation for Methods that Modify Database**:
    // - The methods that modify or update the database should be annotated with
    // `@Transactional` to ensure atomicity and consistency of the operations.
    // - Instruction: Add the `@Transactional` annotation above methods that
    // interact with the database, especially those modifying data.

    // 5. **Update Appointment Method**:
    // - This method is used to update an existing appointment based on its ID.
    // - It validates whether the patient ID matches, checks if the appointment is
    // available for updating, and ensures that the doctor is available at the
    // specified time.
    // - If the update is successful, it saves the appointment; otherwise, it
    // returns an appropriate error message.
    // - Instruction: Ensure proper validation and error handling is included for
    // appointment updates.

    // 6. **Cancel Appointment Method**:
    // - This method cancels an appointment by deleting it from the database.
    // - It ensures the patient who owns the appointment is trying to cancel it and
    // handles possible errors.
    // - Instruction: Make sure that the method checks for the patient ID match
    // before deleting the appointment.

    // 7. **Get Appointments Method**:
    // - This method retrieves a list of appointments for a specific doctor on a
    // particular day, optionally filtered by the patient's name.
    // - It uses `@Transactional` to ensure that database operations are consistent
    // and handled in a single transaction.
    // - Instruction: Ensure the correct use of transaction boundaries, especially
    // when querying the database for appointments.

    // 8. **Change Status Method**:
    // - This method updates the status of an appointment by changing its value in
    // the database.
    // - It should be annotated with `@Transactional` to ensure the operation is
    // executed in a single transaction.
    // - Instruction: Add `@Transactional` before this method to ensure atomicity
    // when updating appointment status.

}
