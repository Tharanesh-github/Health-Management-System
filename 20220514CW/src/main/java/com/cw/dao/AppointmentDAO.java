/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.dao;

/**
 *
 * @author Tharanesh
 */

//This code imports necessary classes and libraries for handling appointments and logging
import com.cw.model.Appointment;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppointmentDAO {
    
    //This code initializes a logger for logging appointment-related activities and creates an empty list to store appointments
    private static final Logger log = LoggerFactory.getLogger(AppointmentDAO.class);
    private static List<Appointment> appointmentList = new ArrayList<>();
    
    //This code block adds an appointment to the list of appointments
    static {
        Appointment appointmentOne = new Appointment(1, "20th Monday 2022", "Michigan", "JamesGunn", "Routine Checkup");
        appointmentList.add(appointmentOne);
    }
    
    //This method adds an appointment to the array list and logs the addition. If the appointment is null, it throws an exception indicating an issue with the addition
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("An issue occurred while adding the appointment to the array list");
        }
        appointmentList.add(appointment);
        log.info("The Appointment Has Been Added: {}", appointment);
    }
    
    //This method retrieves all appointments from the array list and logs the action
    public List<Appointment> getAllAppointments() {
        log.info("Fetching All The Appointments From the Array List");
        return appointmentList;
    }
    
    //This method retrieves an appointment from the array list based on its identification number and logs the action. If the appointment is found, it returns it; otherwise, it logs a message indicating that the appointment was not found and returns null
    public Appointment getAppointmentByIdentityNumber(int identityNumber) {
        log.info("Fetching The Appointment By The Identification Number {}", identityNumber);
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentIdentityNumber() == identityNumber) {
                log.info("The Appointment With The Identification Number {} Has Been Found", identityNumber);
                return appointment;
            }
        }
        log.info("The Appointment With The Identification Number {} Has Not Been Found", identityNumber);
        return null;
    }
    
    //This code updates an appointment within an array list. If the updated appointment is null, it throws an exception. It iterates through the list to find the appointment with the specified identity number, updates it if found, and throws an exception if not found
    public void updateAppointment(Appointment updatedAppointment) {
        if (updatedAppointment == null) {
            throw new IllegalArgumentException("An issue occurred while updating the appointment within the array list");
        }
        int identityNumber = updatedAppointment.getAppointmentIdentityNumber();
        boolean found = false;
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);
            if (appointment.getAppointmentIdentityNumber() == identityNumber) {
                appointmentList.set(i, updatedAppointment);
                found = true;
                log.info("The Appointment Has Been Updated: {}", updatedAppointment);
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The Appointment With The Specific Identity Number Has Not Been Found");
        }
    }
    
    //This code deletes an appointment from an array list based on its identity number. If the appointment is not found, it throws an exception. It logs the successful deletion of the appointment
    public void deleteAppointment(int identityNumber) {
        boolean removed = appointmentList.removeIf(appointment -> appointment.getAppointmentIdentityNumber() == identityNumber);
        if (!removed) {
            throw new IllegalArgumentException("The Appointment Has Not Been Deleted From The Array List");
        }
        log.info("The Appointment With The Identification Number {} Has Been Deleted Successfully", identityNumber);
    }
}