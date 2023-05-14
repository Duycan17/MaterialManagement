/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author duy
 */
import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO() {
        this.connection = JDBCUtil.getConnection();
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String position = resultSet.getString("position");
                String phoneNumber = resultSet.getString("phoneNumber");
                int warehouseId = resultSet.getInt("warehouseId");
                Employee employee = new Employee(id, name, gender, position, phoneNumber, warehouseId);
                employees.add(employee);
            }
        }
        return employees;
    }

    public List<Employee> findEmployeesByName(String name) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String gender = rs.getString("gender");
                    String position = rs.getString("position");
                    String phoneNumber = rs.getString("phoneNumber");
                    int warehouseId = rs.getInt("warehouseId");
                    String namee = rs.getString("name");
                    Employee employee = new Employee(id, namee, gender, position, phoneNumber, warehouseId);
                    employees.add(employee);
                }
            }
        }
        return employees;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String query = "SELECT * FROM Employee WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String position = resultSet.getString("position");
                String phoneNumber = resultSet.getString("phoneNumber");
                int warehouseId = resultSet.getInt("warehouseId");
                return new Employee(id, name, gender, position, phoneNumber, warehouseId);
            } else {
                return null;
            }
        }
    }

    public void insertEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO Employee (id, name, gender, position, phoneNumber, warehouseId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getGender());
            statement.setString(4, employee.getPosition());
            statement.setString(5, employee.getPhoneNumber());
            statement.setInt(6, employee.getWarehouseId());
            statement.executeUpdate();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE Employee SET name = ?, gender = ?, position = ?, phoneNumber = ?, warehouseId = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getGender());
            statement.setString(3, employee.getPosition());
            statement.setString(4, employee.getPhoneNumber());
            statement.setInt(5, employee.getWarehouseId());
            statement.setInt(6, employee.getId());
            statement.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM Employee WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
