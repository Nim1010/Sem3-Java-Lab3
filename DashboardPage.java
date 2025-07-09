import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class DashboardPage {
    public static Scene getScene(Stage stage, String username) {
        Label welcome = new Label("Welcome, " + username);
        Label date = new Label("Date: " + LocalDate.now());

        Button adminBtn = new Button("Admin");
        Button empBtn = new Button("Employee");
        Button logoutBtn = new Button("Logout");
        Button exitBtn = new Button("Exit");

        adminBtn.setOnAction(e -> stage.setScene(AdminPage.getScene(stage)));
        empBtn.setOnAction(e -> stage.setScene(EmployeePage.getScene(stage)));
        logoutBtn.setOnAction(e -> stage.setScene(LoginPage.getScene(stage)));
        exitBtn.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, welcome, date, adminBtn, empBtn, logoutBtn, exitBtn);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 400, 300);
    }
}