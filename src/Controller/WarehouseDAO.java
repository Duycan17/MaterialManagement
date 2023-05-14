/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author duy
 */
import Model.Warehouse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {
  
  private Connection connection;

  public WarehouseDAO() {
    this.connection = JDBCUtil.getConnection();
  }

  public List<Warehouse> getAllWarehouses() throws SQLException {
    List<Warehouse> warehouses = new ArrayList<>();
    String query = "SELECT * FROM Warehouse";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("warehouseId");
        String name = resultSet.getString("warehouseName");
        Warehouse warehouse = new Warehouse(id, name);
        warehouses.add(warehouse);
      }
    }
    return warehouses;
  }

  public Warehouse getWarehouseById(int id) throws SQLException {
    String query = "SELECT * FROM Warehouse WHERE warehouseId = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String name = resultSet.getString("warehouseName");
        return new Warehouse(id, name);
      } else {
        return null;
      }
    }
  }

  public void insertWarehouse(Warehouse warehouse) throws SQLException {
    String query = "INSERT INTO Warehouse (warehouseId, warehouseName) VALUES (?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, warehouse.getWarehouseId());
      statement.setString(2, warehouse.getWarehouseName());
      statement.executeUpdate();
    }
  }

  public void updateWarehouse(Warehouse warehouse) throws SQLException {
    String query = "UPDATE Warehouse SET warehouseName = ? WHERE warehouseId = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, warehouse.getWarehouseName());
      statement.setInt(2, warehouse.getWarehouseId());
      statement.executeUpdate();
    }
  }

  public void deleteWarehouse(int id) throws SQLException {
    String query = "DELETE FROM Warehouse WHERE warehouseId = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }
}