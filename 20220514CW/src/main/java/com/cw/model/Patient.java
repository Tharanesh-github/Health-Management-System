/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.model;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for using Jackson annotations to deserialize JSON properties into Java objects, and for utilizing collections like ArrayList and List.
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    
    private String patientCurrentHealthStatus;
    private List<String> patientMedicalHistory;
    private int patientIdentityNumber;
    
    //This constructor initializes a Patient object with specific attributes including medical history and current status, while also invoking the superclass constructor to set basic person information
    public Patient(int patientIdentityNumber, String personName, int personAge, String personContactInformation, String personAddress, List<String> patientMedicalHistory, String patientCurrentHealthStatus) {
        super(patientIdentityNumber, personName, personAge, personContactInformation, personAddress);
        this.patientIdentityNumber = patientIdentityNumber;
        this.patientMedicalHistory = patientMedicalHistory != null ? new ArrayList<>(patientMedicalHistory) : new ArrayList<>();
        this.patientCurrentHealthStatus = patientCurrentHealthStatus;
    }
    
    //This constructor uses Jackson annotations to deserialize JSON properties into a Patient object, including patient-specific attributes like medical history and current status, while also setting basic person information through the superclass constructor.
    @JsonCreator
    public Patient(@JsonProperty("patientIdentityNumber") int patientIdentityNumber,
                   @JsonProperty("personIdentityNumber") int personIdentityNumber,
                   @JsonProperty("personName") String personName,
                   @JsonProperty("personAge") int personAge,
                   @JsonProperty("personContactInformation") String personContactInformation,
                   @JsonProperty("personAddress") String personAddress,
                   @JsonProperty("patientMedicalHistory") List<String> patientMedicalHistory,
                   @JsonProperty("patientCurrentStatus") String patientCurrentHealthStatus) {
        super(personIdentityNumber, personName, personAge, personContactInformation, personAddress);
        this.patientIdentityNumber = patientIdentityNumber;
        this.patientMedicalHistory = patientMedicalHistory != null ? new ArrayList<>(patientMedicalHistory) : new ArrayList<>();
        this.patientCurrentHealthStatus = patientCurrentHealthStatus;
    }
    
    //This method retrieves the patient's identification number, which is annotated to map a JSON property named "patientIdentificationNumber" to this Java method
    @JsonProperty("patientIdentityNumber")
    public int getPatientIdentityNumber() {
        return patientIdentityNumber;
    }
    
    //This method retrieves the patient's medical history, which is annotated to map a JSON property named "patientMedicalHistory" to this Java method
    @JsonProperty("patientMedicalHistory")
    public List<String> getPatientMedicalHistory() {
        return patientMedicalHistory;
    }
    
    //This method retrieves the patient's current status, which is annotated to map a JSON property named "patientCurrentStatus" to this Java method
    @JsonProperty("patientCurrentStatus")
    public String getPatientCurrentHealthStatus() {
        return patientCurrentHealthStatus;
    }
    
    //This method sets the patient's identification number
    public void setPatientIdentityNumber(int patientIdentityNumber) {
        this.patientIdentityNumber = patientIdentityNumber;
    }
    
    //This method sets the patient's medical history, ensuring that it's initialized with a new ArrayList if the provided list is null
    public void setPatientMedicalHistory(List<String> patientMedicalHistory) {
        this.patientMedicalHistory = patientMedicalHistory != null ? new ArrayList<>(patientMedicalHistory) : new ArrayList<>();
    }
    
    //This method sets the patient's current status
    public void setPatientCurrentHealthStatus(String patientCurrentHealthStatus) {
        this.patientCurrentHealthStatus = patientCurrentHealthStatus;
    }
    
    //This method generates a string representation of a Patient object, including its identification number, name, contact information, address, medical history, and current status
    @Override
    public String toString() {
        return "Patient : {" +
                "Patient Identification Number ='" + patientIdentityNumber + '\'' +
                ", Patient Name ='" + getPersonName() + '\'' +
                ", Patient Contact Number ='" + getPersonContactInformation() + '\'' +
                ", Patient Address Information ='" + getPersonAddress() + '\'' +
                ", Patient Medical History ='" + patientMedicalHistory + '\'' +
                ", Patient Current Status ='" + patientCurrentHealthStatus + '\'' +
                '}';
    }
}
