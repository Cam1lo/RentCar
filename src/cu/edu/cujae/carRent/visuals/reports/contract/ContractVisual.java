package cu.edu.cujae.carRent.visuals.reports.contract;

import cu.edu.cujae.carRent.utils.reportTables.CarStatusReport;
import cu.edu.cujae.carRent.utils.reportTables.ContractReport;
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

public class ContractVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<ContractReport> table;
    @FXML
    private TableColumn<ContractReport, String> touristNameColumn;
    @FXML
    private TableColumn<ContractReport, String> touristLastNameColumn;
    @FXML
    private TableColumn<ContractReport, String> carIdColumn;
    @FXML
    private TableColumn<ContractReport, String> carBrandColumn;
    @FXML
    private TableColumn<ContractReport, String> carModelColumn;
    @FXML
    private TableColumn<ContractReport, String> paymentColumn;
    @FXML
    private TableColumn<ContractReport, String> startingDateColumn;
    @FXML
    private TableColumn<ContractReport, String> finalDateColumn;
    @FXML
    private TableColumn<ContractReport, Integer> extensionColumn;
    @FXML
    private TableColumn<ContractReport, String> haveDriverColumn;
    @FXML
    private TableColumn<ContractReport, Float> totalAmountColumn;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<ContractReport> items = ContractReport.getContractReport();
        this.current_date.setText(LocalDate.now().toString());

        touristNameColumn.setCellValueFactory(new PropertyValueFactory<>("touristName"));
        touristLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("touristLastName"));
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carId"));
        carBrandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        carModelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        haveDriverColumn.setCellValueFactory(new PropertyValueFactory<>("haveDriver"));
        startingDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStartingDate().toString()));
        finalDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFinalDate().toString()));
        extensionColumn.setCellValueFactory(new PropertyValueFactory<>("extension"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        table.setItems(FXCollections.observableList(items));
    }
}
