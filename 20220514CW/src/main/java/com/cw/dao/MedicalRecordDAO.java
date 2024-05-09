/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.dao;

/**
 *
 * @author Tharanesh
 */

//This code imports classes for patient management and initializes a logger and an empty list for patient data
import com.cw.exception.ResourceNotFoundException;
import com.cw.model.MedicalRecord;
import com.cw.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MedicalRecordDAO {
    
    //This code initializes a logger and an empty list to manage medical records
    private static final Logger log = LoggerFactory.getLogger(MedicalRecordDAO.class);
    private static List<MedicalRecord> medicalRecordList = new ArrayList<>();
    
    //This code initializes medical records for two patients with their respective diagnoses and treatments
    static {
        Patient patientOne = PatientDAO.patientList.get(0);
        Patient patientTwo = PatientDAO.patientList.get(1);

        List<String> diagnosesOne = new ArrayList<>();
        diagnosesOne.add("Common Cold and Virus");
        List<String> treatmentOne = new ArrayList<>();
        treatmentOne.add("Rest");
        treatmentOne.add("Fluids");

        medicalRecordList.add(new MedicalRecord(patientOne, diagnosesOne, treatmentOne));
        
        List<String> diagnosesTwo = new ArrayList<>();
        diagnosesTwo.add("Fever with vomite");
        List<String> treatmentTwo = new ArrayList<>();
        treatmentTwo.add("Rest");
        treatmentTwo.add("Medicine");
        medicalRecordList.add(new MedicalRecord(patientTwo, diagnosesTwo, treatmentTwo));
    }
    
    //Adds a medical record to the array list. If the record is null, it throws an exception; otherwise, it adds the record to the list and logs the action
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord == null) {
            throw new IllegalArgumentException("An error occurred while adding the patient's medical record to the array list");
        }
        medicalRecordList.add(medicalRecord);
        log.info("The Medical Record Of The Patient Has Been Added: {}", medicalRecord);
    }
    
    //Returns all medical records stored in the array list
    public List<MedicalRecord> getAllTheMedicalRecords() {
        log.info("Retrieves all medical records from the array list");
        return medicalRecordList;
    }
    
    //This code fetches the medical record linked to a patient using their ID. If found, it returns the record; otherwise, it throws an exception
    public MedicalRecord getMedicalRecordByPatientIdentityNumber(int patientIdentityNumber) {
        log.info("Retrieving the medical record associated with the patient identified by their identification number : {}", patientIdentityNumber);
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getPatientMedicalRecord().getPatientIdentityNumber() == patientIdentityNumber) {
                log.info("The medical record corresponding to the patient identified by their identification number was located : {}", patientIdentityNumber);
                return medicalRecord;
            }
        }
        throw new ResourceNotFoundException("The medical record could not be located for the patient identified by the provided identification number");
    }
    
    //This code updates a medical record in the list with the provided one. If null, it throws an exception. It finds the record associated with the patient, updates it, or throws a "not found" exception if not present
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        if (updatedMedicalRecord == null) {
            throw new IllegalArgumentException("An issue occurred while attempting to modify the medical record in the array list");
        }
        int patientId = updatedMedicalRecord.getPatientMedicalRecord().getPatientIdentityNumber();
        boolean found = false;
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord medicalRecord = medicalRecordList.get(i);
            if (medicalRecord.getPatientMedicalRecord().getPatientIdentityNumber() == patientId) {
                medicalRecordList.set(i, updatedMedicalRecord);
                found = true;
                log.info("The Medical Record Has Been Updated With The Patient Identification Number {}", patientId);
                break;
            }
        }
        if (!found) {
            throw new ResourceNotFoundException("The patient's medical record could not be located");
        }
    }
    
    //Deletes a medical record associated with a patient ID from the list. If not found, throws an exception. Log indicates successful deletion
    public void deleteMedicalRecord(int patientId) {
        boolean removed = medicalRecordList.removeIf(medicalRecord -> medicalRecord.getPatientMedicalRecord().getPatientIdentityNumber() == patientId);
        if (!removed) {
            throw new ResourceNotFoundException("The medical record was not removed from the array list");
        }
        log.info("The Medical Record Of The Patient with ID {} Has Been Deleted Successfully", patientId);
    }
}