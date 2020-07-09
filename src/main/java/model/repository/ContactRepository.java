package model.repository;

import config.ConnectorDB;
import model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ContactRepository implements Repository<Contact, Long> {
    @Override
    public Optional<Contact> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Contact> findAll() {
        return null;
    }

    @Override
    public void save(Contact contact) {
        try(Connection conn = ConnectorDB.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT into contacts(id_customer, type, contact) values(?, ?, ?)")) {
            ps.setLong(1, contact.getCustomerId());
            ps.setInt(2, contact.getType().getValue());
            ps.setString(3, contact.getValue());
            ps.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException | NullPointerException e) {
            System.err.println("Nie udało się zapisać kontaktu.");
        }
    }
}
