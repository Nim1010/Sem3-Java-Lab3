import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminPage {
    public static Scene getScene(Stage stage) {
        TableView<Admin> table = new TableView<>();
        TableColumn<Admin, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Admin, String> nameCol = new TableColumn<>("Name");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().addAll(idCol, nameCol);

        TextField nameField = new TextField();
        nameField.setPromptText("Admin Name");

        Button view = new Button("View");
        Button create = new Button("Create");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button back = new Button("Back");

        view.setOnAction(e -> table.setItems(AdminDAO.getAllAdmins()));
        create.setOnAction(e -> {
            AdminDAO.createAdmin(nameField.getText());
            table.setItems(AdminDAO.getAllAdmins());
        });
        update.setOnAction(e -> {
            Admin selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                AdminDAO.updateAdmin(selected.getId(), nameField.getText());
                table.setItems(AdminDAO.getAllAdmins());
            }
        });
        delete.setOnAction(e -> {
            Admin selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                AdminDAO.deleteAdmin(selected.getId());
                table.setItems(AdminDAO.getAllAdmins());
            }
        });
        back.setOnAction(e -> stage.setScene(DashboardPage.getScene(stage, "Admin")));

        HBox form = new HBox(10, nameField, create, update, delete);
        VBox layout = new VBox(10, table, form, view, back);
        layout.setStyle("-fx-padding: 20;");
        return new Scene(layout, 600, 400);
    }
}