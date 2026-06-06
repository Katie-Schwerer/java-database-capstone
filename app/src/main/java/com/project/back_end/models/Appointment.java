package com.project.back_end.models;

import java.beans.Transient;
import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Doctor doctor;

    @ManyToOne
    @NotNull
    private Patient patient;

    @Future(message = "Appointment time must be in the future")
    private LocalDateTime appointmentTime;

    @NotNull(message = "Status must be 0 or 1")
    private int status;

    @Transient
    public LocalDateTime getEndTime() {
        return appointmentTime.plusHours(1);
    }

    @Transient
    private LocalDate getAppointmentDate() {
        return appointmentTime.toLocalDate();
    }

    @Transient
    private LocalTime getAppointmenTime() {
        return appointmentTime.toLocalTime();
    }

    public Appointment() {
        id = 0;
        doctor = new Doctor();
        patient = new Patient();
        appointmentTime = LocalDateTime.now();
        status = 0;
    }

    public Appointment(Long id, Doctor doctor, Patient patient, LocalDateTime appointmentTime, int status) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public int getStatus() {
        return status;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public void setDoctor(Doctor newDoctor) {
        this.doctor = newDoctor;
    }

    public void setPatient(Patient newPatient) {
        this.patient = newPatient;
    }

    public void setAppointmentTime( LocalDateTime newAppointmentTime) {
        this.appointmentTime = newAppointmentTime;
    }

    public void setStatus(int newStatus) {
        this.status = newStatus;
    }
}
