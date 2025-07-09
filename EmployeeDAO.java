import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class EmployeeDAO {
    public static ObservableList<Employee> getAllEmployees() {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee_detail");
            while (rs.next()) {
                list.add(new Employee(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void createEmployee(String name) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO employee_detail (name) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployee(int id, String name) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE employee_detail SET name = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM employee_detail WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}