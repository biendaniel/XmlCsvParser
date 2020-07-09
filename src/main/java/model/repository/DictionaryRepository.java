package model.repository;

import config.ConnectorDB;
import exception.NotDictionaryElementExist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DictionaryRepository {

    public void update(String value, String name) {
        try (Connection conn = ConnectorDB.getConnection();
             PreparedStatement ps = conn.prepareStatement("update dictionary set value = ? where name = ?;")) {
            ps.setString(1, value);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Nie udało się zmienić rekordu w słowniku.");
        }
    }

    public String findByName(String name) throws NotDictionaryElementExist {
        try (Connection conn = ConnectorDB.getConnection();
             PreparedStatement ps = conn.prepareStatement("select value from dictionary where name = ?;")) {
            ps.setString(1, name);
            ps.executeQuery();
            try(ResultSet resultSet = ps.executeQuery();) {
                if(resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new NotDictionaryElementExist();
        }
        return "";
    }
}
