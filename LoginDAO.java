import java.sql.*;

public class LoginDAO {
    public static boolean authenticate(String email, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM login WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
