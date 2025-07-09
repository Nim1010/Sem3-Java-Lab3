import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class AdminDAO {
    public static ObservableList<Admin> getAllAdmins() {
        ObservableList<Admin> list = FXCollections.observableArrayList();
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin");
            while (rs.next()) {
                list.add(new Admin(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void createAdmin(String name) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO admin (name) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAdmin(int id, String name) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE admin SET name = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAdmin(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM admin WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
