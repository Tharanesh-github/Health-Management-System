/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.resource;

/**
 *
 * @author Tharanesh
 */

//
import com.cw.dao.DoctorDAO;
import com.cw.exception.UserNotFoundException;
import com.cw.model.Doctor;
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

//Specifies the endpoint for doctor-related operations, indicating that it produces and consumes JSON data
@Path("/doctor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class DoctorResource {
    
    //Defines a logger for the DoctorResource class and initializes a DoctorDAO instance
    private static final Logger log = LoggerFactory.getLogger(DoctorResource.class);

    private DoctorDAO doctorDAO;
    
    //Creates a new instance of DoctorResource and initializes its DoctorDAO field
    public DoctorResource() {
        this.doctorDAO = new DoctorDAO();
    }
    
    //Handles the addition of a new doctor to the array list, logs the action, and returns a response with the added doctor entity
    @POST
    public Response addDoctor(Doctor doctor) {
        log.info("The Doctor Has Been Added To The Array List: {}", doctor);
        doctorDAO.addDoctor(doctor);
        return Response.status(Response.Status.CREATED).entity(doctor).build();
    }
    
    //Fetching all doctors from the array list
    @GET
    public List<Doctor> getAllDoctors() {
        log.info("Retrieving all doctors from the array list");
        return doctorDAO.getAllDoctors();
    }
    
    //Retrieving the doctor by their identification number
    @GET
    @Path("/{doctorIdentityNumber}")
    public Response getDoctorByIdentityNumber(@PathParam("doctorIdentityNumber") int doctorIdentityNumber) {
        log.info("Retrieving The Doctor By The Identification Number: {}", doctorIdentityNumber);
        try {
            Doctor doctor = doctorDAO.getDoctorByIdentityNumber(doctorIdentityNumber);
            return Response.ok(doctor).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //Updating the doctor's information with the specified identification number
    @PUT
    @Path("/{doctorIdentityNumber}")
    public Response updateDoctor(@PathParam("doctorIdentityNumber") int doctorIdentityNumber, Doctor updatedDoctor) {
        log.info("The Doctor Has Been Updated Within The Array List: {}: {}", doctorIdentityNumber, updatedDoctor);
        try {
            Doctor currentDoctor = doctorDAO.getDoctorByIdentityNumber(doctorIdentityNumber);
            if (currentDoctor != null) {
                // Assuming you have appropriate setter methods in your Doctor class
                currentDoctor.setPersonName(updatedDoctor.getPersonName());
                currentDoctor.setPersonAge(updatedDoctor.getPersonAge());
                currentDoctor.setPersonContactInformation(updatedDoctor.getPersonContactInformation());
                currentDoctor.setPersonAddress(updatedDoctor.getPersonAddress());
                currentDoctor.setDoctorSpecialization(updatedDoctor.getDoctorSpecialization());

                doctorDAO.updateDoctor(currentDoctor);
                return Response.ok(currentDoctor).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Doctor With The Identification Number " + doctorIdentityNumber + " Was Not Found.")
                        .build();
            }
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //Deleting the doctor with the specified identification number from the system
    @DELETE
    @Path("/{doctorIdentityNumber}")
    public Response deleteDoctor(@PathParam("doctorIdentityNumber") int doctorIdentityNumber) {
        log.info("The Doctor With The Identification Number {} Has Been Deleted Successfully", doctorIdentityNumber);
        try {
            doctorDAO.deleteDoctor(doctorIdentityNumber);
            return Response.noContent().build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}