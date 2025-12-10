package com.example.mysqlsample;

import com.example.mysqlsample.dao.CustomerDao;
import com.example.mysqlsample.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        CustomerDao dao = new CustomerDao();
        try {
            System.out.println("Existing customers:");
            List<Customer> customers = dao.findAll();
            customers.forEach(System.out::println);

            System.out.println("\nInserting new customer...");
            Customer newC = new Customer();
            newC.setName("Charlie Dev");
            newC.setEmail("charlie@example.com");
            Customer inserted = dao.insert(newC);
            System.out.println("Inserted: " + inserted);

            System.out.println("\nCustomers after insert:");
            dao.findAll().forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
