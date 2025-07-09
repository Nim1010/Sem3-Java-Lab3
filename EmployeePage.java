import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EmployeePage {
    public static Scene getScene(Stage stage) {
        TableView<Employee> table = new TableView<>();
        TableColumn<Employee, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().addAll(idCol, nameCol);

        TextField nameField = new TextField();
        nameField.setPromptText("Employee Name");

        Button view = new Button("View");
        Button create = new Button("Create");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button back = new Button("Back");

        view.setOnAction(e -> table.setItems(EmployeeDAO.getAllEmployees()));
        create.setOnAction(e -> {
            EmployeeDAO.createEmployee(nameField.getText());
            table.setItems(EmployeeDAO.getAllEmployees());
        });
        update.setOnAction(e -> {
            Employee selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                EmployeeDAO.updateEmployee(selected.getId(), nameField.getText());
                table.setItems(EmployeeDAO.getAllEmployees());
            }
        });
        delete.setOnAction(e -> {
            Employee selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                EmployeeDAO.deleteEmployee(selected.getId());
                table.setItems(EmployeeDAO.getAllEmployees());
            }
        });
        back.setOnAction(e -> stage.setScene(DashboardPage.getScene(stage, "Employee")));

        HBox form = new HBox(10, nameField, create, update, delete);
        VBox layout = new VBox(10, table, form, view, back);
        layout.setStyle("-fx-padding: 20;");
        return new Scene(layout, 600, 400);
    }
}