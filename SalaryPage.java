import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class SalaryPage {

    private TableView<Salary> tableView;
    private ObservableList<Salary> salaryList;

    private final String DB_URL = "jdbc:mysql://localhost:3306/hrdb";
    private final String USER = "root";
    private final String PASSWORD = "password";

    public SalaryPage(Stage stage) {
        Label header = new Label("Salary Management");

        // TableView setup
        tableView = new TableView<>();
        TableColumn<Salary, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Salary, Double> baseCol = new TableColumn<>("Base Salary");
        baseCol.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));

        TableColumn<Salary, Double> bonusCol = new TableColumn<>("Bonus");
        bonusCol.setCellValueFactory(new PropertyValueFactory<>("bonus"));

        TableColumn<Salary, Double> deductionCol = new TableColumn<>("Deductions");
        deductionCol.setCellValueFactory(new PropertyValueFactory<>("deductions"));

        tableView.getColumns().addAll(idCol, baseCol, bonusCol, deductionCol);
        salaryList = FXCollections.observableArrayList();
        tableView.setItems(salaryList);

        // Input fields
        TextField idField = new TextField();
        idField.setPromptText("ID");

        TextField baseField = new TextField();
        baseField.setPromptText("Base");

        TextField bonusField = new TextField();
        bonusField.setPromptText("Bonus");

        TextField deductionField = new TextField();
        deductionField.setPromptText("Deductions");

        HBox inputBox = new HBox(10, idField, baseField, bonusField, deductionField);

        // Buttons
        Button viewBtn = new Button("View");
        Button insertBtn = new Button("Insert");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button backBtn = new Button("Back");

        viewBtn.setOnAction(e -> loadSalaryData());
        insertBtn.setOnAction(e -> insertSalary(idField, baseField, bonusField, deductionField));
        updateBtn.setOnAction(e -> updateSalary(idField, baseField, bonusField, deductionField));
        deleteBtn.setOnAction(e -> deleteSalary(idField));
        backBtn.setOnAction(e -> new Dashboard(stage));

        HBox buttonBox = new HBox(10, viewBtn, insertBtn, updateBtn, deleteBtn, backBtn);

        VBox root = new VBox(15, header, tableView, inputBox, buttonBox);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-alignment: center;");

        stage.setScene(new Scene(root, 750, 450));
        stage.setTitle("Salary - Nimesh Neupane");
        stage.show();
    }

    private Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadSalaryData() {
        salaryList.clear();
        String query = "SELECT * FROM salary";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                salaryList.add(new Salary(
                        rs.getInt("id"),
                        rs.getDouble("baseSalary"),
                        rs.getDouble("bonus"),
                        rs.getDouble("deductions")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertSalary(TextField id, TextField base, TextField bonus, TextField deduct) {
        String query = "INSERT INTO salary (id, baseSalary, bonus, deductions) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(id.getText()));
            pstmt.setDouble(2, Double.parseDouble(base.getText()));
            pstmt.setDouble(3, Double.parseDouble(bonus.getText()));
            pstmt.setDouble(4, Double.parseDouble(deduct.getText()));
            pstmt.executeUpdate();
            loadSalaryData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateSalary(TextField id, TextField base, TextField bonus, TextField deduct) {
        String query = "UPDATE salary SET baseSalary=?, bonus=?, deductions=? WHERE id=?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, Double.parseDouble(base.getText()));
            pstmt.setDouble(2, Double.parseDouble(bonus.getText()));
            pstmt.setDouble(3, Double.parseDouble(deduct.getText()));
            pstmt.setInt(4, Integer.parseInt(id.getText()));
            pstmt.executeUpdate();
            loadSalaryData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSalary(TextField id) {
        String query = "DELETE FROM salary WHERE id=?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(id.getText()));
            pstmt.executeUpdate();
            loadSalaryData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
