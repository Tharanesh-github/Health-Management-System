/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.resource;

/**
 *
 * @author Tharanesh
 */

//This code defines a JAX-RS resource for managing patient data. It includes methods for handling HTTP GET, POST, PUT, and DELETE requests for patients, with appropriate annotations for specifying endpoints and data formats. Logging is also implemented using SLF4J
import com.cw.dao.PatientDAO;
import com.cw.exception.UserNotFoundException;
import com.cw.model.Patient;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//This code specifies the base path for the patient resource along with the data format for both producing and consuming JSON. It sets up the endpoint for patient-related operations and defines how data should be serialized and deserialized
@Path("/patient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PatientResource {
    
    //This code initializes a logger for the PatientResource class and creates an instance of PatientDAO to interact with patient data. It enables logging within the resource class and connects it to the data access layer
    private static final Logger log = LoggerFactory.getLogger(PatientResource.class);
    private PatientDAO patientDAO;
    
    //This code is the constructor for the PatientResource class, initializing a new instance of PatientDAO to enable interaction with patient data
    public PatientResource() {
        this.patientDAO = new PatientDAO();
    }
    
    //This code handles the addition of a new patient. It logs the successful addition of the patient and returns a response with the created status along with the added patient entity
    @POST
    public Response addPatient(Patient patient) {
        log.info("The patient has been successfully added: {}", patient);
        patientDAO.addPatient(patient);
        return Response.status(Response.Status.CREATED).entity(patient).build();
    }
    
    //Fetching all patient details from the array list
    @GET
    public List<Patient> getAllPatientsDetails() {
        log.info("Retrieving all patients from the array list");
        return patientDAO.getAllPatientsDetails();
    }
    
    //This method retrieves a patient by their identification number and returns a response with the patient details if found, or a "not found" response if the patient is not found
    @GET
    @Path("/{patientIdentityNumber}")
    public Response getPatientByIdentityNumber(@PathParam("patientIdentityNumber") int patientIdentityNumber) {
        log.info("Fetching the patient by the identification number: {}", patientIdentityNumber);
        try {
            Patient patient = patientDAO.getPatientByIdentityNumber(patientIdentityNumber);
            return Response.ok(patient).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //This method updates a patient's information in the array list, given their identification number. If the patient is found, their details are updated with the provided information, and a response with the updated patient details is returned. If the patient is not found, a response indicating that the patient was not found is returned
    @PUT
    @Path("/{patientIdentityNumber}")
    public Response updatePatient(@PathParam("patientIdentityNumber") int patientIdentityNumber, Patient updatedPatient) {
        log.info("The Patient Has Been Updated Within The Array List: {}: {}", patientIdentityNumber, updatedPatient);
        try {
            Patient existingPatient = patientDAO.getPatientByIdentityNumber(patientIdentityNumber);
            if (existingPatient != null) {
                // Assuming you have appropriate setter methods in your Patient class
                existingPatient.setPersonName(updatedPatient.getPersonName());
                existingPatient.setPersonAge(updatedPatient.getPersonAge());
                existingPatient.setPersonContactInformation(updatedPatient.getPersonContactInformation());
                existingPatient.setPersonAddress(updatedPatient.getPersonAddress());
                existingPatient.setPatientMedicalHistory(updatedPatient.getPatientMedicalHistory());
                existingPatient.setPatientCurrentHealthStatus(updatedPatient.getPatientCurrentHealthStatus());

                patientDAO.updatePatient(existingPatient);
                return Response.ok(existingPatient).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The patient with the identification number " + patientIdentityNumber + " was not found.")
                        .build();
            }
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //
    @DELETE
    @Path("/{patientIdentityNumber}")
    public Response deletePatient(@PathParam("patientIdentityNumber") int patientIdentityNumber) {
        log.info("The patient with the identification number {} has been successfully removed from the records", patientIdentityNumber);
        try {
            patientDAO.deletePatient(patientIdentityNumber);
            return Response.noContent().build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
