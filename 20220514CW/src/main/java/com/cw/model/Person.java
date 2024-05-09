/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.model;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for using Jackson annotations to deserialize JSON properties into Java objects
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    
    private String personName;
    private String personContactInformation;
    private String personAddress;
    private int personAge;
    private int personIdentityNumber;
    
    // Default constructor
    public Person() {
    }
    
    // Constructor to deserialize JSON properties into a Person object
    @JsonCreator
    public Person(@JsonProperty("personIdentityNumber") int personIdentityNumber,
            @JsonProperty("personName") String personName,
            @JsonProperty("personAge") int personAge,
            @JsonProperty("personContactInformation") String personContactInformation,
            @JsonProperty("personAddressInformation") String personAddress) {
        this.personIdentityNumber = personIdentityNumber;
        this.personName = personName;
        this.personAge = personAge;
        this.personContactInformation = personContactInformation;
        this.personAddress = personAddress;
    }
    
    //method retrieves the person's name
    public String getPersonName() {
        return personName;
    }
    
    //method retrieves the person's age
    public int getPersonAge() {
        return personAge;
    }
    
    //method retrieves the person's identification number
    public int getPersonIdentityNumber() {
        return personIdentityNumber;
    }
    
    //method retrieves the person's contact information
    public String getPersonContactInformation() {
        return personContactInformation;
    }
    
    //method retrieves the person's address information
    public String getPersonAddress() {
        return personAddress;
    }
    
    //This method sets the person's name
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    
    //This method sets the person's age
    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }
    
    //This method sets the person's identification number
    public void setPersonIdentityNumber(int personIdentityNumber) {
        this.personIdentityNumber = personIdentityNumber;
    }
    
    //This method sets the person's contact information
    public void setPersonContactInformation(String personContactInformation) {
        this.personContactInformation = personContactInformation;
    }
    
    //This method sets the person's address information
    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }
    
    //This method generates a string representation of a Person object with its attributes
    @Override
    public String toString() {
        return "Person:{"
                + "Person Identification Number ='" + personIdentityNumber + '\''
                + ", Person Name ='" + personName + '\''
                + ", Person Contact Number =" + personContactInformation
                + ", Person Address Information ='" + personAddress + '\''
                + '}';
    }
    
}
