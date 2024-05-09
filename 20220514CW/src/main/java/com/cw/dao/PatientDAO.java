/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.dao;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for handling exceptions, defining data models, and logging within the application
import com.cw.exception.UserNotFoundException;
import com.cw.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientDAO {
    
    //This code initializes a logger instance and a static list to store instances of the Patient class within the PatientDAO class
    private static final Logger log = LoggerFactory.getLogger(PatientDAO.class);
    static List<Patient> patientList = new ArrayList<>();
    
    //This static block initializes two instances of the Patient class with sample data and adds them to the patientList
    static {
        List<String> medicalRecord1 = new ArrayList<>();
        medicalRecord1.add("Annual Checkup - 2023");
        medicalRecord1.add("Fever - 2022");
        patientList.add(new Patient(1, "Ajith", 55, "0123456789", "000 Flower Avenue", medicalRecord1, "UnHealthy"));

        List<String> medicalRecord2 = new ArrayList<>();
        medicalRecord2.add("Annual Checkup - 2021");
        medicalRecord2.add("Allergy - 2020");
        patientList.add(new Patient(2, "AmithabBachan", 70, "024681012", "111 Opaque Street", medicalRecord2, "UnHealthy"));
    }
    
    //This method adds a patient to the patient list after validating the patient object. It throws an exception if the patient object or its name is null or empty. Finally, it logs the successful addition of the patient
    public void addPatient(Patient patient) {
        if (patient == null || patient.getPersonName() == null || patient.getPersonName().isEmpty()) {
            throw new IllegalArgumentException("There was an issue encountered while adding the patient to the array list");
        }
        patientList.add(patient);
        log.info("The patient has been successfully added: {}", patient);
    }
    
    //This method retrieves all patient details from the patient list and logs the action
    public List<Patient> getAllPatientsDetails() {
        log.info("Retrieving all patients from the array list");
        return patientList;
    }
    
    //This method retrieves a patient from the list by their identification number. It logs the action and throws an exception if the patient is not found
    public Patient getPatientByIdentityNumber(int patientIdentityNumber) {
        log.info("Retrieving the patient with the identification number {}", patientIdentityNumber);
        for (Patient patient : patientList) {
            if (patient.getPatientIdentityNumber() == patientIdentityNumber) {
                log.info("The patient with the identity number {} has been located", patientIdentityNumber);
                return patient;
            }
        }
        throw new UserNotFoundException("The Patient With The Identification Number " + patientIdentityNumber + " Has Not Been Found");
    }
    
    //This method updates a patient within the array list. It logs the action and throws an exception if the patient is not found
    public void updatePatient(Patient updatedPatient) {
        if (updatedPatient == null) {
            throw new IllegalArgumentException("An error occurred while updating the patient within the array list");
        }

        int updatedPatientIdentityNumber = updatedPatient.getPatientIdentityNumber();
        boolean found = false;

        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = patientList.get(i);
            if (patient.getPatientIdentityNumber() == updatedPatientIdentityNumber) {
                patientList.set(i, updatedPatient);
                found = true;
                log.info("The patient has been successfully updated: {}", updatedPatient);
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The patient with the identification number {}" + updatedPatient.getPatientIdentityNumber() + "Has not been located");
        }
    }
    
    //This method deletes a patient from the list based on their identification number. If the patient is not found, it throws an exception. Finally, it logs the deletion of the patient with the provided identification number
    public void deletePatient(int identityNumber) {
        boolean removed = patientList.removeIf(patient -> patient.getPatientIdentityNumber() == identityNumber);

        if (!removed) {
            throw new IllegalArgumentException("The Patient With The Identification Number " + identityNumber + " Has Not Been Found");
        }
        log.info("The patient with the identification number {} has been successfully deleted", identityNumber);
    }
}