/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.model;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for using Jackson annotations to deserialize JSON properties into Java objects.
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Doctor extends Person {
    
    private int doctorIdentityNumber;
    private String doctorSpecialization;
    
    //This constructor initializes a Doctor object with specific attributes including specialization, while also invoking the superclass constructor to set basic person information
    public Doctor(int doctorIdentityNumber, String personName, int personAge, String personContactInformation, String personAddress, String doctorSpecialization) {
        super(doctorIdentityNumber, personName, personAge, personContactInformation, personAddress);
        this.doctorIdentityNumber = doctorIdentityNumber;
        this.doctorSpecialization = doctorSpecialization;
    }
    
    //This constructor uses Jackson annotations to deserialize JSON properties into a Doctor object, including doctor-specific attributes like specialization, while also setting basic person information through the superclass constructor
    @JsonCreator
    public Doctor(@JsonProperty("doctorIdentityNumber") int doctorIdentityNumber,
            @JsonProperty("personIdentityNumber") int personIdentityNumber,
            @JsonProperty("personName") String personName,
            @JsonProperty("personAge") int personAge,
            @JsonProperty("personContactInformation") String personContactInformation,
            @JsonProperty("personAddress") String personAddress,
            @JsonProperty("doctorSpecialization") String doctorSpecialization) {
        super(personIdentityNumber, personName, personAge, personContactInformation, personAddress);
        this.doctorIdentityNumber = doctorIdentityNumber;
        this.doctorSpecialization = doctorSpecialization;
    }
    
    //This method retrieves the doctor's identification number, which is annotated to map a JSON property named "doctorIdentificationNumber" to this Java method
    @JsonProperty("doctorIdentityNumber")
    public int getDoctorIdentityNumber() {
        return doctorIdentityNumber;
    }
    
    //This method retrieves the doctor's specialization, which is annotated to map a JSON property named "doctorSpecialization" to this Java method
    @JsonProperty("doctorSpecialization")
    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }
    
    //This method sets the doctor's identification number
    public void setDoctorIdentityNumber(int doctorIdentityNumber) {
        this.doctorIdentityNumber = doctorIdentityNumber;
    }
    
    //This method sets the doctor's specialization
    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }
    
}
