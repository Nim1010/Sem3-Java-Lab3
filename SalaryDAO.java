import java.sql.*;
import javafx.collections.*;

public class SalaryDAO {
    public static ObservableList<Salary> getAllSalaries() {
        ObservableList<Salary> list = FXCollections.observableArrayList();
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM salary");
            while (rs.next()) {
                list.add(new Salary(rs.getInt("id"), rs.getInt("employee_id"), rs.getDouble("monthly_salary")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static void createSalary(int employeeId, double monthlySalary) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO salary (employee_id, monthly_salary) VALUES (?, ?)");
            stmt.setInt(1, employeeId);
            stmt.setDouble(2, monthlySalary);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void updateSalary(int id, double monthlySalary) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE salary SET monthly_salary=? WHERE id=?");
            stmt.setDouble(1, monthlySalary);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void deleteSalary(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM salary WHERE id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
