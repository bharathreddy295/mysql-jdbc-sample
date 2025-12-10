package com.example.mysqlsample.dao;

import com.example.mysqlsample.config.DatabaseConfig;
import com.example.mysqlsample.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public List<Customer> findAll() throws SQLException {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT id, name, email FROM customer";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                list.add(c);
            }
        }
        return list;
    }

    public Customer insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer(name,email) VALUES (?,?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            int rows = ps.executeUpdate();
            if (rows == 0) throw new SQLException("Insert failed");
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) customer.setId(keys.getInt(1));
            }
        }
        return customer;
    }
}
