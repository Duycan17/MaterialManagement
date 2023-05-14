/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author duy
 */
import Model.Material;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    Connection connection;

    public MaterialDAO() {
        connection = JDBCUtil.getConnection();

    }

    public void create(Material material) throws SQLException {
        String query = "INSERT INTO Material (materialId, statusId, materialName, materialNumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, material.getMaterialId());
            statement.setInt(2, material.getStatusId());
            statement.setString(3, material.getMaterialName());
            statement.setInt(4, material.getMaterialNumber());
            statement.executeUpdate();
        }
    }

//    public Material read(int materialId) throws SQLException {
//        String query = "SELECT  FROM Material WHERE materialId = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, materialId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    Material material = new Material();
//                    material.setMaterialId(resultSet.getInt("materialId"));
//                    material.setStatusId(resultSet.getInt("statusId"));
//                    material.setMaterialName(resultSet.getString("materialName"));
//                    material.setMaterialNumber(resultSet.getInt("materialNumber"));
//                    return material;
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
    public void update(Material material) throws SQLException {
        String query = "UPDATE Material SET statusId = ?, materialName = ?, materialNumber = ? WHERE materialId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, material.getStatusId());
            statement.setString(2, material.getMaterialName());
            statement.setInt(3, material.getMaterialNumber());
            statement.setInt(4, material.getMaterialId());
            statement.executeUpdate();
        }
    }

    public void delete(int materialId) throws SQLException {
        String query = "DELETE FROM Material WHERE materialId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, materialId);
            statement.executeUpdate();
        }
    }

    public List<Material> getAll() throws SQLException {
        String query = "SELECT Material.materialId, Material.materialName, Material.materialNumber, Maintain.statusName FROM Material INNER JOIN Maintain ON Material.statusId = Maintain.statusId";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            List<Material> materials = new ArrayList<>();
            while (resultSet.next()) {
                Material material = new Material();
                material.setMaterialId(resultSet.getInt("materialId"));
                material.setMaterialName(resultSet.getString("materialName"));
                material.setMaterialNumber(resultSet.getInt("materialNumber"));
                material.setStatusName(resultSet.getString("statusName"));
                materials.add(material);
            }
            return materials;
        }
    }

    public int getIdStatus(String s) throws SQLException {
        String query = "select * from Maintain where statusName like '" + s + "'";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
        
        }
        return 0;

    }
}
