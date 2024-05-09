/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.resource;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for defining a RESTful web service endpoint for managing person data
import com.cw.dao.PersonDAO;
import com.cw.exception.UserNotFoundException;
import com.cw.model.Person;
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

//These annotations define the base path for the RESTful web service endpoint ("/person") and specify that it produces and consumes JSON data
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PersonResource {
    
    //This code initializes a logger instance and a PersonDAO instance for managing person data
    private static final Logger log = LoggerFactory.getLogger(PersonDAO.class);
    private PersonDAO personDAO;

    
    //This constructor initializes a new instance of PersonDAO within the PersonResource class
    public PersonResource() {

        this.personDAO = new PersonDAO();
    }
    
    //This method defines a POST endpoint to add a person to the array list, logging the action and delegating the task to the PersonDAO class. It returns a response with the created person entity and a status of 201 (Created)
    @POST
    public Response addingNewPersonWithinTheArrayList(Person person) {
        log.info("The individual has been successfully added to the array list: {}", person);
        personDAO.addingNewPersonWithinTheArrayList(person);
        return Response.status(Response.Status.CREATED).entity(person).build();
    }
    
    //This method defines a GET endpoint to retrieve all people from the array list, logging the action and delegating the task to the PersonDAO class
    @GET
    public List<Person> getAllPeople(){
        log.info("Retrieving all individuals from the array list");
        return personDAO.getAllPeople();
    }
    
    //This method defines a GET endpoint to retrieve a person from the array list by their identification number. It logs the action, delegates the task to the PersonDAO class, and returns a response with the retrieved person entity. If the person is not found, it returns a response with a status of 404 (Not Found) and an error message
    @GET
    @Path("/{personIdentityNumber}")
    public Response getPersonByIdentityNumber(@PathParam("personIdentityNumber") int personIdentityNumber) {
        log.info("Fetching The Person By The Identification Number: {}", personIdentityNumber);
        try {
            Person person = personDAO.getPersonByIdentityNumber(personIdentityNumber);
            return Response.ok(person).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //This method defines a PUT endpoint to update a person in the array list by their identification number. It logs the action, retrieves the existing person from the PersonDAO class, updates its attributes with those from the provided updated person object, and returns a response with the updated person entity. If the person is not found, it returns a response with a status of 404 (Not Found) and an appropriate error message
    @PUT
    @Path("/{personIdentityNumber}")
    public Response updatePerson(@PathParam("personIdentityNumber") int personIdentityNumber, Person updatedPerson) {
        log.info("The individual with the identification number {} has been successfully updated within the array list: {}", personIdentityNumber, updatedPerson);
        try {
            Person currentIndividual = personDAO.getPersonByIdentityNumber(personIdentityNumber);
            if (currentIndividual != null) {
                // Assuming you have appropriate setter methods in your Person class
                currentIndividual.setPersonName(updatedPerson.getPersonName());
                currentIndividual.setPersonAge(updatedPerson.getPersonAge());
                currentIndividual.setPersonContactInformation(updatedPerson.getPersonContactInformation());
                currentIndividual.setPersonAddress(updatedPerson.getPersonAddress());

                personDAO.updatePerson(currentIndividual);
                return Response.ok(currentIndividual).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Individual with the identification number " + personIdentityNumber + " Was Not Found.")
                        .build();
            }
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    //This method defines a DELETE endpoint to remove a person from the array list by their identification number. It logs the action, delegates the task to the PersonDAO class, and returns a response with a status of 204 (No Content) if the deletion is successful. If the person is not found, it returns a response with a status of 404 (Not Found) and an appropriate error message
    @DELETE
    @Path("/{personIdentityNumber}")
    public Response removePersonWithinArrayList(@PathParam("personIdentityNumber") int personIdentityNumber) {
        log.info("The individual with the identification number {} has been successfully deleted", personIdentityNumber);
        try {
            personDAO.removePersonWithinArrayList(personIdentityNumber);
            return Response.noContent().build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
