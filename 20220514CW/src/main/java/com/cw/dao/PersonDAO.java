/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.dao;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for handling exceptions, defining data models, and logging within the application
import com.cw.exception.UserNotFoundException;
import com.cw.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PersonDAO {
    
    //This code defines a logger instance for logging purposes and a static list to store instances of the Person class within the PersonDAO class
    private static final Logger log = LoggerFactory.getLogger(PersonDAO.class);
    private static List<Person> personList = new ArrayList<>();

    //This static block initializes the list of people when the class is loaded
    static {
        initializePeopleList();
    }
    
    //This method initializes the list of people with some predefined Person objects
    private static void initializePeopleList() {
        personList.add(new Person(1, "TomCruise", 61, "0123456789", "39 Hampden Lane"));
        personList.add(new Person(2, "BradPitt", 60, "9876543210", "17 arethusalane"));
        personList.add(new Person(3, "FahadFasil", 41, "0135791113", "56 nelsonplace"));
        personList.add(new Person(4, "DulquerSalmaan", 40, "024681012", "333 Redwood Street"));
    }
    
    //This method adds a new person to the list, ensuring the person object is not null and has a non-empty name. It assigns a new identification number to the person and logs the addition
    public void addingNewPersonWithinTheArrayList(Person person) {
        if (person == null || person.getPersonName() == null || person.getPersonName().isEmpty()) {
            throw new IllegalArgumentException("An error occurred while attempting to add the person to the array list");
        }
        int newPersonId = getNextPersonIdentity();
        person.setPersonIdentityNumber(newPersonId);
        personList.add(person);
        log.info("The person has been successfully added to the array list : { }", person);
    }
    
    //This method retrieves all people from the array list, logs the action, and returns a new list containing the retrieved people
    public List<Person> getAllPeople() {
        log.info("Retrieving all individuals from the array list");
        return new ArrayList<>(personList);
    }
    
    //This method retrieves a person from the array list by their identification number. It logs the action, iterates through the list of people, and returns the person if found. If no person is found, it throws a UserNotFoundException
    public Person getPersonByIdentityNumber(int personIdentityNumber) {
        log.info("Retrieving the person with the given identification number : {}", personIdentityNumber);
        for (Person person : personList) {
            if (person.getPersonIdentityNumber() == personIdentityNumber) {
                log.info("The individual with the provided identification number {} has been located", personIdentityNumber);
                return person;
            }
        }
        throw new UserNotFoundException("No individual was found with the specified identification number " + personIdentityNumber);
    }
    
    //This method calculates and returns the next available identification number for a new person by iterating through the list of people and finding the maximum identification number
    private int getNextPersonIdentity() {
        int maximumIdentityNumber = Integer.MIN_VALUE;
        for (Person person : personList) {
            if (person.getPersonIdentityNumber() > maximumIdentityNumber) {
                maximumIdentityNumber = person.getPersonIdentityNumber();
            }
        }
        return maximumIdentityNumber + 1;
    }
    
    //This method updates a person within the array list with the provided updated person object. It iterates through the list to find the person to be updated, replaces it with the updated person, and logs the action. If the person is not found, it throws a UserNotFoundException
    public void updatePerson (Person updatedPerson){
        if (updatedPerson == null) {
            throw new IllegalArgumentException("An error occurred while attempting to update the person within the array list");
        }
        boolean found = false;
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getPersonIdentityNumber() == updatedPerson.getPersonIdentityNumber()) {
                personList.set(i, updatedPerson);
                found = true;
                log.info("The person has been successfully updated within the array list: {}", updatedPerson);
                break;
            }
        }
        if (!found) {
            throw new UserNotFoundException("The individual to be updated could not be found within the array list");
        }
    }
    
    //This method removes a person from the array list based on their identification number. It logs the action and throws an exception if the person is not found
    public void removePersonWithinArrayList(int personIdentityNumber) {
        boolean removed = personList.removeIf(person -> person.getPersonIdentityNumber() == personIdentityNumber);
        if (!removed) {
            throw new IllegalArgumentException("The individual could not be located for deletion within the array list");
        }
        log.info("The individual with the specified identification number {} has been successfully deleted", personIdentityNumber);
    }

}
