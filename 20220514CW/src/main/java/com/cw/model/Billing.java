/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.model;

/**
 *
 * @author Tharanesh
 */

//This code contains annotations used for JSON serialization/deserialization
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Billing {
    
    private String billingAppointmentDate;
    private List<String> billingInvoiceRecord;
    private List<String> billingPaymentRecord;
    private int billingIdentityNumber;
    private int billingAppointmentIdentityNumber;
    
    //This code defines a constructor for the Billing class, using annotations for JSON deserialization
    @JsonCreator
    public Billing(@JsonProperty("billingIdentityNumber") int billingIdentityNumber,
                   @JsonProperty("billingAppointmentIdentityNumber") int billingAppointmentIdentityNumber,
                   @JsonProperty("billingAppointmentDate") String billingAppointmentDate,
                   @JsonProperty("billingInvoiceRecord") List<String> billingInvoiceRecord,
                   @JsonProperty("billingPaymentRecord") List<String> billingPaymentRecord) {
        this.billingIdentityNumber = billingIdentityNumber;
        this.billingAppointmentIdentityNumber = billingAppointmentIdentityNumber;
        this.billingAppointmentDate = billingAppointmentDate;
        this.billingInvoiceRecord = billingInvoiceRecord != null ? new ArrayList<>(billingInvoiceRecord) : new ArrayList<>();
        this.billingPaymentRecord = billingPaymentRecord != null ? new ArrayList<>(billingPaymentRecord) : new ArrayList<>();
    }
    
    //This method retrieves the billing identity number
    public int getBillingIdentityNumber() {
        return billingIdentityNumber;
    }
    
    //This method retrieves the appointment identity number
    public int getBillingAppointmentIdentityNumber() {
        return billingAppointmentIdentityNumber;
    }
    
    //This method retrieves the appointment date
    public String getBillingAppointmentDate() {
        return billingAppointmentDate;
    }
    
    //This method retrieves the billing invoice record
    public List<String> getBillingInvoiceRecord() {
        return billingInvoiceRecord;
    }
    
    //This method retrieves the billing payment record
    public List<String> getBillingPaymentRecord() {
        return billingPaymentRecord;
    }
    
    //This method sets the billing identity number for a billing instance. It takes an integer parameter representing the billing identity number and assigns it to the corresponding field in the billing object
    public void setBillingIdentityNumber(int billingIdentityNumber) {
        this.billingIdentityNumber = billingIdentityNumber;
    }
    
    //Sets the billing appointment identity number to the specified value
    public void setBillingAppointmentIdentityNumber(int billingAppointmentIdentityNumber) {
        this.billingAppointmentIdentityNumber = billingAppointmentIdentityNumber;
    }
    
    //Sets the date of the billing appointment to the provided date value
    public void setBillingAppointmentDate(String billingAppointmentDate) {
        this.billingAppointmentDate = billingAppointmentDate;
    }
    
    //Updates the list of billing invoice records with the provided list, ensuring it's initialized even if null
    public void setBillingInvoiceRecord(List<String> billingInvoiceRecord) {
        this.billingInvoiceRecord = billingInvoiceRecord != null ? new ArrayList<>(billingInvoiceRecord) : new ArrayList<>();
    }
    
    //Initializes the list of billing payment records with the provided list, ensuring it's initialized even if null
    public void setBillingPaymentRecord(List<String> billingPaymentRecord) {
        this.billingPaymentRecord = billingPaymentRecord != null ? new ArrayList<>(billingPaymentRecord) : new ArrayList<>();
    }
    
    //Overrides the toString method to provide a string representation of the Billing object's attributes.
    @Override
    public String toString() {
        return "Patient : {" +
                "Billing Identity Number ='" + billingIdentityNumber + '\'' +
                ", Billing Appointment Identity Number ='" + billingAppointmentIdentityNumber + '\'' +
                ", Billing Appointment Date ='" + billingAppointmentDate + '\'' +
                ", Billing Invoice Record ='" + billingInvoiceRecord + '\'' +
                ", Billing Payment Record ='" + billingPaymentRecord + '\'' +
                '}';
    }
}