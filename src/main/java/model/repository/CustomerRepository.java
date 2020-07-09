package model.repository;

import config.ConnectorDB;
import model.Contact;
import model.ContactType;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository implements Repository<Customer, Long> {
    @Override
    public Optional<Customer> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = ConnectorDB.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers")) {
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customers.add(new Customer(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        try(Connection conn = ConnectorDB.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT into customers(name, surname, age) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            try {
                ps.setInt(3, customer.getAge());
            } catch (NullPointerException e) {
                ps.setNull(3, Types.INTEGER);
            }
            ps.executeUpdate();
            try(ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    customer.setId((generatedKeys.getLong(1)));
                }
            }
        }
        catch (SQLException | ClassNotFoundException | NullPointerException e) {
            System.err.println("Nie udało się zapisać klienta.");
        }
    }

    public Iterable<Contact> findContacts(long id) {
        List<Contact> contacts = new ArrayList<>();
        try(Connection conn = ConnectorDB.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT type, contact FROM contacts where id_customer = ?")) {
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    contacts.add(new Contact(ContactType.get(rs.getInt(1)), rs.getString(2)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    }
