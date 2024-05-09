/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.model;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for using Jackson annotations to deserialize JSON properties into Java objects
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Appointment {
    
    private int appointmentIdentityNumber;
    private String appointmentDate;
    private String doctorName;
    private String patientName;
    private String appointmentReason;
    
    //This constructor initializes an Appointment object with specific attributes such as identification number, date, doctor name, patient name, and visiting reason, using Jackson annotations to deserialize JSON properties into Java object fields
    @JsonCreator
    public Appointment(@JsonProperty("appointmentIdentityumber") int appointmentIdentityNumber,
                       @JsonProperty("appointmentDate") String appointmentDate,
                       @JsonProperty("doctorName") String doctorName,
                       @JsonProperty("patientName") String patientName,
                       @JsonProperty("appointmentReason") String appointmentReason) {
        this.appointmentIdentityNumber = appointmentIdentityNumber;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
        this.patientName = patientName; 
        this.appointmentReason = appointmentReason;
    }
    
    //This method retrieves the appointment's identity number
    @JsonProperty("appointmentIdentityNumber")
    public int getAppointmentIdentityNumber() {
        return appointmentIdentityNumber;
    }
    
    //This method retrieves the appointment date
    @JsonProperty("appointmentDate")
    public String getAppointmentDate() {
        return appointmentDate;
    }
    
    //This method retrieves the appointment doctor's name
    @JsonProperty("doctorName")
    public String getDoctorName() {
        return doctorName;
    }
    
    //This method retrieves the patient name
    @JsonProperty("patientName")
    public String getPatientName() {
        return patientName;
    }
    
    //This method retrieves the reason for the appointment
    @JsonProperty("appointmentReason")
    public String getAppointmentReason() {
        return appointmentReason;
    }
    
    //This method sets the appointment's identity number
    public void setAppointmentIdentityumber(int appointmentIdentityNumber) {
        this.appointmentIdentityNumber = appointmentIdentityNumber;
    }
    
    //This method sets the appointment date
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    //This method sets the doctors name for the appointment
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
    //This method sets the patient's name for the appointment
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
    //This method sets the reason for the appointment
    public void setAppointmentReason(String appointmentReason) {
        this.appointmentReason = appointmentReason;
    }
    
    //This method generates a string representation of an appointment object containing its identification number, date, patient name, doctor name, and reason for the appointment
    @Override
    public String toString() {

        return "Appointment{" +
                " Appointment Identification Number = " + appointmentIdentityNumber +
                ", Appointment Date = " + appointmentDate +
                ", Appointment Patient = " + patientName + 
                ", Appointment Doctor = " + doctorName +   
                ", Appointment Reason='" + appointmentReason + '\'' +
                '}';
    }
}