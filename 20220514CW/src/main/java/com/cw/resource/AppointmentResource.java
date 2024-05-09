/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.resource;

/**
 *
 * @author Tharanesh
 */

//This code defines a JAX-RS resource class for handling appointments. It specifies HTTP methods for creating, reading, updating, and deleting appointments. It uses the AppointmentDAO to interact with the data layer.
import com.cw.dao.AppointmentDAO;
import com.cw.exception.ResourceNotFoundException;
import com.cw.model.Appointment;
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

//This code specifies the base path for the appointment resource, along with the media types it produces and consumes. It ensures that the resource communicates using JSON format
@Path("/appointment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AppointmentResource {
    
    //This code initializes a log to handle logging for the AppointmentResource class and creates an instance of AppointmentDAO to interact with appointment data
    private static final Logger log = LoggerFactory.getLogger(AppointmentResource.class);
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    
    //This code defines a POST endpoint to add a new appointment. It logs the addition of the appointment and delegates the addition to the appointment DAO. Finally, it returns a response indicating success along with the added appointment
    @POST
    public Response addAppointment(Appointment appointment) {
        log.info("The Appointment Has Been Added To The Array List: {}", appointment);
        appointmentDAO.addAppointment(appointment);
        return Response.status(Response.Status.CREATED).entity(appointment).build();
    }
    
    //This code defines a GET endpoint to retrieve all appointments. It logs the retrieval operation and delegates it to the appointment DAO, returning the list of appointments retrieved from the DAO
    @GET
    public List<Appointment> getAllAppointments() {
        log.info("Retrieving All The Appointments From The Array List");
        return appointmentDAO.getAllAppointments();
    }
    
    //This code retrieves an appointment by its identity number, logs the operation, and returns the appointment if found; otherwise, it returns a NOT FOUND response
    @GET
    @Path("/{appointmentIdentityNumber}")
    public Response getAppointmentByIdentityNumber(@PathParam("appointmentIdentityNumber") int appointmentIdentityNumber) {
        log.info("Retrieving The Appointment By The Identity Number: {}", appointmentIdentityNumber);
        try {
            Appointment appointment = appointmentDAO.getAppointmentByIdentityNumber(appointmentIdentityNumber);
            return Response.ok(appointment).build();
        } catch (ResourceNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //This code updates an appointment in the array list using its identity number, logs the operation, and returns the updated appointment if found; otherwise, it returns a NOT FOUND response.
    @PUT
    @Path("/{appointmentIdentityNumber}")
    public Response updateAppointment(@PathParam("appointmentIdentityNumber") int appointmentIdentityNumber, Appointment updatedAppointment) {
        log.info("The Appointment Has Been Updated Within The Array List: {}: {}", appointmentIdentityNumber, updatedAppointment);
        try {
            Appointment currentAppointment = appointmentDAO.getAppointmentByIdentityNumber(appointmentIdentityNumber);
            if (currentAppointment != null) {
                currentAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
                currentAppointment.setDoctorName(updatedAppointment.getDoctorName());
                currentAppointment.setPatientName(updatedAppointment.getPatientName());
                currentAppointment.setAppointmentReason(updatedAppointment.getAppointmentReason());

                appointmentDAO.updateAppointment(currentAppointment);
                return Response.ok(currentAppointment).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Appointment With The Identity Number " + appointmentIdentityNumber + " Was Not Found.")
                        .build();
            }
        } catch (ResourceNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //This code deletes an appointment by its identity number, logs the operation, and returns the appropriate response
    @DELETE
    @Path("/{appointmentIdentityNumber}")
    public Response deleteAppointment(@PathParam("appointmentIdentityNumber") int appointmentIdentityNumber) {
        log.info("The Appointment With The Identity Number {} Has Been Deleted Successfully", appointmentIdentityNumber);
        try {
            appointmentDAO.deleteAppointment(appointmentIdentityNumber);
            return Response.noContent().build();
        } catch (ResourceNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


    
}
