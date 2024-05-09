/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.dao;

/**
 *
 * @author Tharanesh
 */

//
import com.cw.model.Billing;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillingDAO {
    
    //Defines a logger and a list to manage Billing objects within the DAO
    private static final Logger log = LoggerFactory.getLogger(BillingDAO.class);
    private static List<Billing> billingList = new ArrayList<>();
    
    //Initializes a sample Billing object and adds it to the billing list
    static {
        List<String> invoices = new ArrayList<>();
        invoices.add("Invoice 1");
        List<String> payments = new ArrayList<>();
        payments.add("Payment 1");

        Billing bill = new Billing(1, 1, "2024-06-06", invoices, payments);
        billingList.add(bill);
    }
    
    //This method adds a billing record to the list. If the provided billing object is invalid, it throws an exception. Then, it adds the billing object to the list and logs the action
    public void addBilling(Billing billing) {
        if (billing == null || billing.getBillingIdentityNumber() == 0) {
            throw new IllegalArgumentException("There's a mistake in adding the patient's appointment billing record to the array list");
        }
        billingList.add(billing);
        log.info("The patient's appointment billing record has been successfully added : {}", billing);
    }
    
    //Retrieve all billing records from the list and log the action
    public List<Billing> getAllTheBillings() {
        log.info("Retrieve all billing records from the array list");
        return billingList;
    }
    
    //This method retrieves a billing record by its identification number from the list. It logs success or failure accordingly
    public Billing getBillingByBillIdentityNumber(int billingIdentityNumber) {
        for (Billing billing : billingList) {
            if (billing.getBillingIdentityNumber() == billingIdentityNumber) {
                log.info("The Billing Record Was Found With The Billing Identification Number  {}: {}", billingIdentityNumber, billing);
                return billing;
            }
        }
        log.info("The billing record for the patient's appointment could not be found with the given identification number {}", billingIdentityNumber);
        return null;
    }
    
    //Code attempts to update a billing record in the list. If successful, it logs the update; otherwise, it indicates that the record couldn't be found
    public void updateBilling(Billing updatedBilling) {
        if (updatedBilling == null || updatedBilling.getBillingIdentityNumber() == 0) {
            throw new IllegalArgumentException("There's An Error In Updating The Billing Record Of The Patient's Appointment Within The Array List");
        }

        for (int i = 0; i < billingList.size(); i++) {
            Billing billing = billingList.get(i);
            if (billing.getBillingIdentityNumber() == updatedBilling.getBillingIdentityNumber()) {
                billingList.set(i, updatedBilling);
                log.info("The patient's appointment billing record has been successfully updated : {}", updatedBilling);
                return;
            }
        }
        log.error("The patient's appointment billing record could not be located");
    }
    
    //Code deletes a billing record based on its identity number. If successfully removed, it logs the deletion; otherwise, it logs an error message
    public void deleteBilling(int billingIdentityNumber) {
        boolean removed = billingList.removeIf(billing -> billing.getBillingIdentityNumber() == billingIdentityNumber);
        if (removed) {
            log.info("The Billing Record {} Of The Patient's Appointment Has Been Deleted Successfully", billingIdentityNumber);
        } else {
            log.error("The Billing Record {} Has Not Been Deleted From The Array List", billingIdentityNumber);
        }
    }
}