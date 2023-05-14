/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Maintain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duy
 */
public class MaintainDAO {

    Connection connection = JDBCUtil.getConnection();

    public List<Maintain> getAllMaintains() throws Exception {
        List<Maintain> maintains = new ArrayList<>();
        String query = "SELECT * FROM Maintain";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("statusId");
                String name = resultSet.getString("statusName");
                Maintain maintain = new Maintain(id, name);
                maintains.add(maintain);
            }
        }
        return maintains;
    }

    public Maintain getMaintainById(int id) throws Exception {
        String query = "SELECT * FROM Maintain WHERE statusId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("statusName");
                return new Maintain(id, name);
            } else {
                return null;
            }
        }
    }

    public void insertMaintain(Maintain maintain) throws Exception {
        String query = "INSERT INTO Maintain (statusId, statusName) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, maintain.getStatusId());
            statement.setString(2, maintain.getStatusName());
            statement.executeUpdate();
        }
    }

    public void updateMaintain(Maintain maintain) throws Exception {
        String query = "UPDATE Maintain SET statusName = ?  WHERE statusId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(2, maintain.getStatusId());
            statement.setString(1, maintain.getStatusName());
            statement.executeUpdate();
        }
    }

    public void deleteMaintain(int id) throws Exception {
        String query = "DELETE FROM Maintain WHERE statusId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
