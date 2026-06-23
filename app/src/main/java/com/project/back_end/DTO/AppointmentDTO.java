package com.project.back_end.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentDTO {
    private Long id;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientPhone;
    private String patientAddress;
    private LocalDateTime appointmentTime;
    private int status;
    private LocalDate appointmenDate;
    private LocalTime appointmentTimeOnly;
    private LocalDateTime endTime;

    public AppointmentDTO(Long id, Long doctorId, String doctorName) {
        this.id = id;
        this.doctorId = doctorId;
    }

    public Long getId() {
        return id;
    }

// 14. Constructor:
//    - The constructor accepts all the relevant fields for the AppointmentDTO, including simplified fields for the doctor and patient (ID, name, etc.).
//    - It also calculates custom fields: 'appointmentDate', 'appointmentTimeOnly', and 'endTime' based on the 'appointmentTime' field.

// 15. Getters:
//    - Standard getter methods are provided for all fields: id, doctorId, doctorName, patientId, patientName, patientEmail, patientPhone, patientAddress, appointmentTime, status, appointmentDate, appointmentTimeOnly, and endTime.
//    - These methods allow access to the values of the fields in the AppointmentDTO object.

}
