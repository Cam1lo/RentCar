package cu.edu.cujae.carRent.visuals.reports.driver;

import cu.edu.cujae.carRent.utils.reportTables.CarStatusReport;
import cu.edu.cujae.carRent.utils.reportTables.DriverReport;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DriversVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<DriverReport> table;
    @FXML
    private TableColumn<DriverReport, String> idColumn;
    @FXML
    private TableColumn<DriverReport, String> nameColumn;
    @FXML
    private TableColumn<DriverReport, String> last_nameColumn;
    @FXML
    private TableColumn<DriverReport, String> addressColumn;
    @FXML
    private TableColumn<DriverReport, String> categoryColumn;
    @FXML
    private TableColumn<DriverReport, Integer> numberOfCarsDrivenColumn;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<DriverReport> items = DriverReport.getDriverReport();
        this.current_date.setText(LocalDate.now().toString());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        numberOfCarsDrivenColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfCarsDriven"));

        table.setItems(FXCollections.observableList(items));
    }
}
