/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.model;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for using Jackson annotations to deserialize JSON properties into Java objects, and for utilizing collections like ArrayList and List
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    
    private Patient patientMedicalRecord;
    private List<String> patientDiagnosesRecord;
    private List<String> patientTreatmentRecord;
    
    //This constructor initializes a MedicalRecord object with a patient's medical records, diagnoses, and treatments, using Jackson annotations to deserialize JSON properties into Java object fields
    @JsonCreator
    public MedicalRecord(@JsonProperty("patientMedicalRecord") Patient patientMedicalRecord,
                         @JsonProperty("patientDiagnosesRecord") List<String> patientDiagnosesRecord,
                         @JsonProperty("patientTreatmentRecord") List<String> patientTreatmentRecord) {
        this.patientMedicalRecord = patientMedicalRecord;
        this.patientDiagnosesRecord = new ArrayList<>(patientDiagnosesRecord);
        this.patientTreatmentRecord = new ArrayList<>(patientTreatmentRecord);
    }
    
    //This method retrieves the medical records of a patient
    public Patient getPatientMedicalRecord() {
        return patientMedicalRecord;
    }
    
    //This method retrieves the list of diagnoses recorded for a patient
    public List<String> getPatientDiagnosesRecord() {
        return patientDiagnosesRecord;
    }
    
    //This method retrieves the list of treatments recorded for a patient
    public List<String> getPatientTreatmentRecord() {
        return patientTreatmentRecord;
    }
    
    //This method sets the medical records of a patient
    public void setPatientMedicalRecord(Patient patientMedicalRecord) {
        this.patientMedicalRecord = patientMedicalRecord;
    }
    
    //This method sets the list of diagnoses recorded for a patient, ensuring that it's initialized with a new ArrayList if the provided list is not null
    public void setPatientDiagnosesRecord(List<String> patientDiagnosesRecord) {
        this.patientDiagnosesRecord = new ArrayList<>(patientDiagnosesRecord);
    }
    
    //This method sets the list of treatments recorded for a patient, ensuring that it's initialized with a new ArrayList if the provided list is not null
    public void setPatientTreatmentRecord(List<String> patientTreatmentsRecord) {
        this.patientTreatmentRecord = new ArrayList<>(patientTreatmentRecord);
    }
    
    //This method generates a string representation of a MedicalRecord object, including the patient's identification number, medical record, and treatments record
    @Override
    public String toString() {
        return "MedicalRecord{" +
                "Patient Identity Number = " + patientMedicalRecord.getPatientIdentityNumber() +
                ", Patient Medical Record = " + patientMedicalRecord +
                ", Patient Treatment Record = " + patientTreatmentRecord +
                '}';
    }
    
}
