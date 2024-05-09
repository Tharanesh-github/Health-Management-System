/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.dao;

/**
 *
 * @author Tharanesh
 */

//This code imports classes and interfaces related to prescriptions and sets up logging functionality
import com.cw.model.Prescription;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrescriptionDAO {
    
    //This code declares a logger for logging prescription-related activities and initializes a list to store prescription objects
    private static final Logger log = LoggerFactory.getLogger(PrescriptionDAO.class);
    private static List<Prescription> prescriptionList = new ArrayList<>();
    
    //This code initializes a sample prescription with instructions and adds it to the list of prescriptions
    static {
        List<String> instructions = new ArrayList<>();
        instructions.add("Take one tablet daily with meals");
        instructions.add("Drink plenty of water");

        Prescription prescription = new Prescription(
                1,
                1,
                "Lavanya",
                "Brook",
                "Medicine",
                "106mg",
                "2023-04-10",
                instructions
        );

        prescriptionList.add(prescription);
    }
    
    //This method adds a prescription to the list after validating it and logs the addition
    public void addPrescription(Prescription prescription) {
        if (prescription == null || prescription.getPrescriptionIdentityNumber() == 0) {
            throw new IllegalArgumentException("There was a mistake while adding the prescription to the array list");
        }
        prescriptionList.add(prescription);
        log.info("The prescription has been successfully added : {}", prescription);
    }
    
    //Fetching all prescriptions from the list and logging the operation
    public List<Prescription> getAllPrescriptions() {
        log.info("Retrieving all prescriptions from the list");
        return prescriptionList;
    }
    
    //This method retrieves a prescription by its ID and logs the operation. If found, it returns the prescription; otherwise, it logs that it couldn't be located and returns null
    public Prescription getPrescriptionByIdentityNumber(int prescriptionIdentityNumber) {
        log.info("Retrieving the prescription identified by its prescription ID : {}", prescriptionIdentityNumber);
        for (Prescription prescription : prescriptionList) {
            if (prescription.getPrescriptionIdentityNumber() == prescriptionIdentityNumber) {
                log.info("The prescription corresponding to the prescription ID was located : {}", prescriptionIdentityNumber);
                return prescription;
            }
        }
        log.info("The prescription could not be located with the given prescription ID : {}", prescriptionIdentityNumber);
        return null;
    }
    
    //Update the prescription details in the array list
    public void updatePrescription(Prescription updatedPrescription) {
        if (updatedPrescription == null || updatedPrescription.getPrescriptionIdentityNumber() == 0) {
            throw new IllegalArgumentException("An issue occurred while attempting to modify the prescription in the array list");
        }
        int prescriptionIdentityNumber = updatedPrescription.getPrescriptionIdentityNumber();
        boolean found = false;
        for (int i = 0; i < prescriptionList.size(); i++) {
            Prescription prescription = prescriptionList.get(i);
            if (prescription.getPrescriptionIdentityNumber() == prescriptionIdentityNumber) {
                prescriptionList.set(i, updatedPrescription);
                found = true;
                log.info("The prescription has been successfully updated : {}", updatedPrescription);
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The prescription corresponding to the given prescription ID could not be located");
        }
    }
    
    //This code removes a prescription by its ID from the list. If successful, it logs the action; otherwise, it throws an exception
    public void deletePrescription(int prescriptionIdentityNumber) {
        boolean removed = prescriptionList.removeIf(prescription -> prescription.getPrescriptionIdentityNumber() == prescriptionIdentityNumber);
        if (!removed) {
            throw new IllegalArgumentException("The prescription could not be removed from the array list");
        }
        log.info("The prescription associated with prescription ID {} has been successfully deleted", prescriptionIdentityNumber);
    }
}