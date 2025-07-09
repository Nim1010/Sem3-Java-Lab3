import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginPage {
    public static Scene getScene(Stage stage) {
        TextField email = new TextField();
        PasswordField password = new PasswordField();
        Button loginBtn = new Button("Login");
        Label message = new Label();

        loginBtn.setOnAction(e -> {
            if (LoginDAO.authenticate(email.getText(), password.getText())) {
                stage.setScene(DashboardPage.getScene(stage, email.getText()));
            } else {
                message.setText("Invalid credentials.");
            }
        });

        VBox layout = new VBox(10, new Label("Email"), email, new Label("Password"), password, loginBtn, message);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 400, 300);
    }
}