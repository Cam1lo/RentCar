package cu.edu.cujae.carRent.visuals.reports.touristReport;

import cu.edu.cujae.carRent.utils.reportTables.TouristFailContractReport;
import cu.edu.cujae.carRent.utils.reportTables.TouristReport;
import javafx.beans.property.SimpleFloatProperty;
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

public class TouristVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<TouristReport> table;
    @FXML
    private TableColumn<TouristReport, String> countryColumn;
    @FXML
    private TableColumn<TouristReport, String> nameColumn;
    @FXML
    private TableColumn<TouristReport, String> last_nameColumn;
    @FXML
    private TableColumn<TouristReport, String> passportColumn;
    @FXML
    private TableColumn<TouristReport, Integer> amountContractColumn;
    @FXML
    private TableColumn<TouristReport, Float> rentValueColumn;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<TouristReport> items = TouristReport.getTouristReport();
        this.current_date.setText(LocalDate.now().toString());

        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        passportColumn.setCellValueFactory(new PropertyValueFactory<>("passport"));
        amountContractColumn.setCellValueFactory(new PropertyValueFactory<>("amountContract"));
        rentValueColumn.setCellValueFactory(new PropertyValueFactory<>("rentValue"));

        table.setItems(FXCollections.observableList(items));
    }
}
