/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.dao;

/**
 *
 * @author Tharanesh
 */

//This code imports necessary classes for handling Doctor objects and initializes a logger
import com.cw.model.Doctor;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoctorDAO {
    
    //This code initializes a logger and a list to store Doctor objects.
    private static final Logger log = LoggerFactory.getLogger(DoctorDAO.class);
    private static List<Doctor> doctorList = new ArrayList<>();
    
    //This code initializes two Doctor objects and adds them to the doctorList
    static {
        Doctor eliasDoctor = new Doctor(1, 3, "Brook", 27, "0123456789", "222 Mait Place", "Neurologist");
        doctorList.add(eliasDoctor);

        Doctor jenniferDoctor = new Doctor(2, 4, "Chander", 27, "9876543210", "333 Red Street", "Cardiologist");
        doctorList.add(jenniferDoctor);
    }
    
    //This method adds a doctor to the list of doctors. It checks if the provided doctor object is not null and has a non-empty name before adding it to the list. Finally, it logs a message indicating the successful addition of the doctor
    public void addDoctor(Doctor doctor) {
        if (doctor == null || doctor.getPersonName() == null || doctor.getPersonName().isEmpty()) {
            throw new IllegalArgumentException("An error occurred while adding the doctor to the array list");
        }
        doctorList.add(doctor);
        log.info("The doctor has been successfully added: {}", doctor);
    }
    
    //This method retrieves all doctors from the list and logs the action. Then, it returns the list of doctors
    public List<Doctor> getAllDoctors() {
        log.info("Retrieving All The Doctors From The Array List");
        return doctorList;
    }
    
    //This method retrieves a doctor from the list based on their identification number. It logs the process of retrieval and whether the doctor was found or not
    public Doctor getDoctorByIdentityNumber(int identityNumber) {
        log.info("Retrieving The Doctor By The Identification Number {}", identityNumber);
        for (Doctor doctor : doctorList) {
            if (doctor.getDoctorIdentityNumber()== identityNumber) {
                log.info("The Doctor With The Identification Number {} Has Been Located", identityNumber);
                return doctor;
            }
        }
        log.info("The Doctor With The Identification Number {} Could Not Be Found", identityNumber);
        return null;
    }
    
    //This code updates a doctor's information in the list of doctors. If the doctor with the given identification number is found, their details are updated; otherwise, an exception is thrown indicating that the doctor was not found
    public void updateDoctor(Doctor updatedDoctor) {
        if (updatedDoctor == null) {
            throw new IllegalArgumentException("There's an issue encountered while updating the doctor within the array list");
        }
        int id = updatedDoctor.getDoctorIdentityNumber();
        boolean found = false;
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);
            if (doctor.getDoctorIdentityNumber() == id) {
                doctorList.set(i, updatedDoctor);
                found = true;
                log.info("The doctor has been successfully updated: {}", updatedDoctor);
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The Doctor With The Identification Number Has Not Been Found");
        }
    }
    
    //Deletes a doctor from the array list based on their identification number. If the deletion is successful, it logs the event; otherwise, it throws an exception indicating that the doctor has not been deleted successfully
    public void deleteDoctor(int identityNumber) {
        boolean removed = doctorList.removeIf(doctor -> doctor.getDoctorIdentityNumber() == identityNumber);
        if (!removed) {
            throw new IllegalArgumentException("The Doctor Has Not Been Deleted Successfully");
        }
        log.info("The Doctor With The Identification Number {} Has Been Deleted Successfully", identityNumber);
    }
    
    
}
