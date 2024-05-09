/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.resource;

/**
 *
 * @author Tharanesh
 */

//Defines a JAX-RS resource class for handling medical records. It specifies HTTP methods for creating, reading, updating, and deleting medical records
import com.cw.dao.MedicalRecordDAO;
import com.cw.exception.ResourceNotFoundException;
import com.cw.model.MedicalRecord;
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

//Specifies the base path and media types for handling medical records in JSON format
@Path("/medicalrecord")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MedicalRecordResource {
    
    //Creates a logger instance to handle logging for the MedicalRecordResource class and initializes a MedicalRecordDAO object for interacting with medical record data
    private static final Logger log = LoggerFactory.getLogger(MedicalRecordResource.class);
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    
    //Handles medical record addition. If successful, returns status CREATED with the added record; otherwise, logs errors and returns status INTERNAL_SERVER_ERROR
    @POST
    public Response addMedicalRecord(MedicalRecord record) {
        try {
            medicalRecordDAO.addMedicalRecord(record);
            return Response.status(Response.Status.CREATED).entity(record).build();
        } catch (Exception e) {
            log.error("Error adding medical record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    
    //Retrieve all medical records
    @GET
    public List<MedicalRecord> getAllMedicalRecords() {
        try {
            return medicalRecordDAO.getAllTheMedicalRecords();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve medical records", e);
        }
    }
    
    //Retrieve the medical record for a specific patient by their identity number
    @GET
    @Path("/{patientIdentityNumber}")
    public Response getMedicalRecordByPatientIdentityNumber(@PathParam("patientIdentityNumber") int patientIdentityNumber) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordByPatientIdentityNumber(patientIdentityNumber);
            return Response.ok(medicalRecord).build();
        } catch (ResourceNotFoundException e) {
            log.error("Medical record for patient ID {} not found", patientIdentityNumber);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Error retrieving medical record for patient ID {}: {}", patientIdentityNumber, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    
    //
    @PUT
    @Path("/{patientIdentityNumber}")
    public Response updateMedicalRecord(MedicalRecord updatedRecord, @PathParam("patientIdentificationNumber") int patientIdentityNumber) {
        try {
            medicalRecordDAO.updateMedicalRecord(updatedRecord);
            return Response.ok().build();
        } catch (ResourceNotFoundException e) {
            log.error("No medical record found for the patient with ID {}", patientIdentityNumber);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            log.error("Failed to update the medical record for patient ID {}: {}", patientIdentityNumber, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    
    //
    @DELETE
    @Path("/{patientIdentityNumber}")
    public Response deleteMedicalRecord(@PathParam("patientIdentityNumber") int patientIdentityNumber) {
        try {
            medicalRecordDAO.deleteMedicalRecord(patientIdentityNumber);
            return Response.noContent().build();
        } catch (ResourceNotFoundException e) {
            log.error("The medical record corresponding to patient ID {} could not be located", patientIdentityNumber);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            log.error("There was an issue while deleting the medical record for patient ID {}: {}", patientIdentityNumber, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    
}
