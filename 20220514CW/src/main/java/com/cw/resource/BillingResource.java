/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.resource;

/**
 *
 * @author Tharanesh
 */

//This code sets up a RESTful API endpoint to handle billing records, providing functionalities like adding, retrieving, updating, and deleting billing records
import com.cw.dao.BillingDAO;
import com.cw.model.Billing;
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

//This code defines a RESTful API endpoint for billing operations, specifying that it consumes and produces JSON data
@Path("/billing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class BillingResource {
    
    //This code initializes a logger and a data access object (DAO) for billing operations
    private static final Logger log = LoggerFactory.getLogger(BillingResource.class);
    private BillingDAO billingDAO = new BillingDAO();
    
    //This code adds a billing record. If successful, it returns the added record with status 201; otherwise, it returns an error message with status 400
    @POST
    public Response addBilling(Billing billing) {
        try {
            billingDAO.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity(billing).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    //Retrieve all billing records from the array list
    @GET
    public List<Billing> getAllTheBillings() {
        log.info("Fetching All The Billing Records From The Array List");
        return billingDAO.getAllTheBillings();
    }
    
    //This code retrieves a billing record based on the billing identification number. If found, it returns the billing record; otherwise, it returns a "not found" response
    @GET
    @Path("/{billingIdentityNumber}")
    public Response getBillingByBillIdentityNumber(@PathParam("billingIdentityNumber") int billingIdentityNumber) {
        log.info("The Billing Record Was Found With The Billing Identification Number {}", billingIdentityNumber);
        Billing billing = billingDAO.getBillingByBillIdentityNumber(billingIdentityNumber);
        if (billing != null) {
            return Response.ok(billing).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("The billing record could not be located with the provided billing identification number : " + billingIdentityNumber).build();
        }
    }
    
    //This code updates a billing record with the provided information and returns the updated record if successful, or a "not found" response if the record does not exist
    @PUT
    @Path("/{billingIdentityNumber}")
    public Response updateBilling(@PathParam("billingIdentityNumber") int billingIdentityNumber, Billing updatedBilling) {
        updatedBilling.setBillingIdentityNumber(billingIdentityNumber);
        try {
            billingDAO.updateBilling(updatedBilling);
            return Response.ok(updatedBilling).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //This code deletes a billing record by its identification number and returns the appropriate response based on whether the deletion was successful or not
    @DELETE
    @Path("/{billingIdentityNumber}")
    public Response deleteBilling(@PathParam("billingIdentityNumber") int billingIdentityNumber) {
        try {
            billingDAO.deleteBilling(billingIdentityNumber);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
