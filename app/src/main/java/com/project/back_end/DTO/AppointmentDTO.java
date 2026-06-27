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
    private LocalDate appointmentDate;
    private LocalTime appointmentTimeOnly;
    private LocalDateTime endTime;

    public AppointmentDTO(Long id, Long doctorId, String doctorName, Long patientId, String patientName,
            String patientEmail, String patientPhone, String patientAddress, LocalDateTime appointmentTime, int status,
            LocalDate appointmentDate, LocalTime appointmentTimeOnly, LocalDateTime endTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.patientPhone = patientPhone;
        this.patientAddress = patientAddress;
        this.appointmentTime = appointmentTime;
        if (appointmentTime != null) {
            this.appointmentDate = appointmentTime.toLocalDate();
            this.appointmentTimeOnly = appointmentTime.toLocalTime();
            this.endTime = appointmentTime.plusHours(1);
        }
    }

    public Long getId() {
        return id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getAppointmenTime() {
        return appointmentTime;
    }

    public LocalDate getAppointmentDate() {
        return appointmenDate;
    }

    public LocalTime getAppointmentTimeOnly() {
        return appointmentTimeOnly;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public void setDoctorId(Long newDoctorId) {
        this.doctorId = newDoctorId;
    }

    public void setDoctorName(String newDoctorName) {
        this.doctorName = newDoctorName;
    }

    public void setPatientId(Long newPatientId) {
        this.patientId = newPatientId;
    }

    public void setPatientName(String newPatientName) {
        this.patientName = newPatientName;
    }

    public void setPatientEmail(String newPatientEmail) {
        this.patientEmail = newPatientEmail;
    }

    public void setPatientPhone(String newPatientPhone) {
        this.patientPhone = newPatientPhone;
    }

    public void setPatientAddress(String newPatientAddress) {
        this.patientAddress = newPatientAddress;
    }

    public void setStatus(int newStatus) {
        this.status = newStatus;
    }

    public void setAppointmenTime(LocalDateTime newAppointmentTime) {
        this.appointmentTime = newAppointmentTime;
    }

    public void setAppointmentDate(LocalDate newAppointmentDate) {
        this.appointmenDate = newAppointmentDate;
    }

    public void setAppointmentTimeOnly(LocalTime newAppointmentTimeOnly) {
        this.appointmentTimeOnly = newAppointmentTimeOnly;
    }

    public void getEndTime(LocalDateTime newEndTime) {
        this.endTime = newEndTime;
    }

}
