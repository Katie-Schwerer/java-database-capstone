package com.project.back_end.models;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Document(collection = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    private String patientName;

    @NotNull
    private Long appointmentId;

    @NotNull
    @Size(min = 3, max = 100)
    private String medication;

    @NotNull
    @Size(min = 3, max = 20)
    private String dosage;

    @Size(max = 200)
    private String doctorNotes;

    public Prescription() {
        id = "0";
        patientName = "";
        appointmentId = 0L;
        medication = "";
        dosage = "";
        doctorNotes = "";
    }

    public Prescription(String id, String patientName, Long appointmentId, String mediation, String dosage, String doctorNotes) {
        this.id = id;
        this.patientName = patientName;
        this.appointmentId = appointmentId;
        this.medication = mediation;
        this.dosage = dosage;
        this.doctorNotes = doctorNotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String newPatientName) {
        this.patientName = newPatientName;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long newAppointmentId) {
        this.appointmentId = newAppointmentId;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String newMedication) {
        this.medication = newMedication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String newDosage) {
        this.dosage = newDosage;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String newDoctorNotes) {
        this.doctorNotes = newDoctorNotes;
    }

}
