/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.model;

/**
 *
 * @author Tharanesh
 */

//This code includes annotations to help deserialize JSON properties into Java objects, and it declares classes for that purpose
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Prescription {
    
    private String prescribedPatient;
    private String prescribingDoctor;
    private String prescriptionName;
    private String prescribedDosage;
    private String prescribedDate;
    private List<String> prescribedInstructions;
    private int prescriptionIdentityNumber;
    private int prescriptionAppointmentNumber;
    
    //This code creates a Prescription object constructor from JSON properties, initializing its attributes like identity and appointment numbers, patient and doctor names, prescription details, dosage, date, and instructions
    @JsonCreator
    public Prescription(@JsonProperty("prescriptionIdentityNumber") int prescriptionIdentityNumber,
                        @JsonProperty("prescriptionAppointmentNumber") int prescriptionAppointmentNumber,
                        @JsonProperty("prescribedPatient") String prescribedPatient,
                        @JsonProperty("prescribingDoctor") String prescribingDoctor,
                        @JsonProperty("prescriptionName") String prescriptionName,
                        @JsonProperty("prescribedDosage") String prescribedDosage,
                        @JsonProperty("prescribedDate") String prescribedDate,
                        @JsonProperty("prescribedInstructions") List<String> prescribedInstructions) {
        this.prescriptionIdentityNumber = prescriptionIdentityNumber;
        this.prescriptionAppointmentNumber = prescriptionAppointmentNumber;
        this.prescribedPatient = prescribedPatient;
        this.prescribingDoctor = prescribingDoctor;
        this.prescriptionName = prescriptionName;
        this.prescribedDosage = prescribedDosage;
        this.prescribedDate = prescribedDate;
        this.prescribedInstructions = prescribedInstructions != null ? new ArrayList<>(prescribedInstructions) : new ArrayList<>();
    }
    
    //This code retrieves the prescription's identity number
    @JsonProperty("prescriptionIdentityNumber")
    public int getPrescriptionIdentityNumber() {
        return prescriptionIdentityNumber;
    }
    
    //This code retrieves the appointment number associated with the prescription
    @JsonProperty("prescriptionAppointmentNumber")
    public int getPrescriptionAppointmentNumber() {
        return prescriptionAppointmentNumber;
    }
    
    //This code retrieves the patient associated with the prescription
    @JsonProperty("prescribedPatient")
    public String getPrescribedPatient() {
        return prescribedPatient;
    }
    
    //This code retrieves the doctor associated with the prescriptions
    @JsonProperty("prescribingDoctor")
    public String getPrescribingDoctor() {
        return prescribingDoctor;
    }
    
    //This code retrieves the name of the prescription associated with the prescriptions
    @JsonProperty("prescriptionName")
    public String getPrescriptionName() {
        return prescriptionName;
    }
    
    //This code retrieves the prescribed dosage associated with the patient
    @JsonProperty("prescribedDosage")
    public String getPrescribedDosage() {
        return prescribedDosage;
    }
    
    //This code retrieves the dates of the consumption of dosage
    @JsonProperty("prescribedDate")
    public String getPrescribedDate() {
        return prescribedDate;
    }
    
    //This code retrieves the instructions on prescription note
    @JsonProperty("prescribedInstructions")
    public List<String> getPrescribedInstructions() {
        return prescribedInstructions;
    }
    
    //This code sets the identity number for the prescription
    public void setPrescriptionIdentityNumber(int prescriptionIdentityNumber) {
        this.prescriptionIdentityNumber = prescriptionIdentityNumber;
    }
    
    //This code sets the appointment number for the prescription
    public void setPrescriptionAppointmentNumber(int prescriptionAppointmentNumber) {
        this.prescriptionAppointmentNumber = prescriptionAppointmentNumber;
    }
    
    //This code sets the patient for whom the prescription is prescribed
    public void setPrescribedPatient(String prescribedPatient) {
        this.prescribedPatient = prescribedPatient;
    }
    
    //This code sets the doctor who prescribed the medication
    public void setPrescribingDoctor(String prescribingDoctor) {
        this.prescribingDoctor = prescribingDoctor;
    }
    
    //This code sets the name of the prescribed medication
    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }
    
    //This code sets the dosage of the prescribed medication
    public void setPrescribedDosage(String prescribedDosage) {
        this.prescribedDosage = prescribedDosage;
    }
    
    //This code updates the prescribed date of the medication
    public void setPrescribedDate(String prescribedDate) {
        this.prescribedDate = prescribedDate;
    }
    
    //This code updates the list of prescribed instructions for medication, ensuring it's not null
    public void setPrescribedInstructions(List<String> prescribedInstructions) {
        this.prescribedInstructions = prescribedInstructions != null ? new ArrayList<>(prescribedInstructions) : new ArrayList<>();
    }
    
    
}
