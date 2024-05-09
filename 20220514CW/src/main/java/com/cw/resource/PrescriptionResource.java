/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.resource;

/**
 *
 * @author Tharanesh
 */

//This code implements a JAX-RS resource for managing prescriptions, allowing CRUD operations in JSON format
import com.cw.dao.PrescriptionDAO;
import com.cw.model.Prescription;
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

//This code defines a JAX-RS resource for handling prescriptions, specifying that it produces and consumes JSON data
@Path("/prescription")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PrescriptionResource {
    
    //This code initializes a logger for the PrescriptionResource class and creates an instance of PrescriptionDAO to interact with prescription data
    private static final Logger log = LoggerFactory.getLogger(PrescriptionResource.class);
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    
    //This code adds a prescription to the list, logs the addition, adds it using PrescriptionDAO, and returns a success response with the added prescription
    @POST
    public Response addPrescription(Prescription prescription) {
        log.info("The prescription was successfully added to the list : {}", prescription);
        prescriptionDAO.addPrescription(prescription);
        return Response.status(Response.Status.CREATED).entity(prescription).build();
    }
    
    //Retrieving all prescriptions and returning them as a response
    @GET
    public Response getAllPrescriptions() {
        log.info("Retrieving all prescriptions from the list");
        return Response.ok(prescriptionDAO.getAllPrescriptions()).build();
    }
    
    //Retrieving the prescription based on its prescription ID and returning the appropriate response
    @GET
    @Path("/{prescriptionIdentityNumber}")
    public Response getPrescriptionByIdentityNumber(@PathParam("prescriptionIdentityNumber") int prescriptionIdentityNumber) {
        log.info("Retrieving the prescription identified by its prescription ID : {}", prescriptionIdentityNumber);
        Prescription prescription = prescriptionDAO.getPrescriptionByIdentityNumber(prescriptionIdentityNumber);
        if (prescription != null) {
            return Response.ok(prescription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prescription With ID " + prescriptionIdentityNumber + " Was Not Found.")
                    .build();
        }
    }
    
    //This code updates a prescription in the array list with the provided information. If found, it gets updated; otherwise, a not found response is returned
    @PUT
    @Path("/{prescriptionIdentityNumber}")
    public Response updatePrescription(@PathParam("prescriptionIdentityNumber") int prescriptionIdentityNumber, Prescription updatedPrescription) {
        log.info("The prescription in the array list has been successfully updated with the provided information : {} : {}", prescriptionIdentityNumber, updatedPrescription);
        Prescription existingPrescription = prescriptionDAO.getPrescriptionByIdentityNumber(prescriptionIdentityNumber);
        if (existingPrescription != null) {
            prescriptionDAO.updatePrescription(updatedPrescription);
            return Response.ok(updatedPrescription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prescription With ID " + prescriptionIdentityNumber + " Was Not Found.")
                    .build();
        }
    }
    
    //This code deletes a prescription with the provided ID from the array list. If found, it gets deleted; otherwise, a not found response is returned
    @DELETE
    @Path("/{prescriptionIdentityNumber}")
    public Response deletePrescription(@PathParam("prescriptionIdentityNumber") int prescriptionIdentityNumber) {
        log.info("The Prescription With Prescription ID {} Has Been Deleted Successfully", prescriptionIdentityNumber);
        Prescription prescription = prescriptionDAO.getPrescriptionByIdentityNumber(prescriptionIdentityNumber);
        if (prescription != null) {
            prescriptionDAO.deletePrescription(prescriptionIdentityNumber);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prescription With ID " + prescriptionIdentityNumber + " Was Not Found.")
                    .build();
        }
    }
}