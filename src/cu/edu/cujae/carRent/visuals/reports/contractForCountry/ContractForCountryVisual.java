package cu.edu.cujae.carRent.visuals.reports.contractForCountry;

import cu.edu.cujae.carRent.utils.reportTables.ContractForBrandAndModelReport;
import cu.edu.cujae.carRent.utils.reportTables.ContractForCountryReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContractForCountryVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<ContractForCountryReport> table;
    @FXML
    private TableColumn<ContractForCountryReport, String> countryColumn;
    @FXML
    private TableColumn<ContractForCountryReport, String> carBrandColumn;
    @FXML
    private TableColumn<ContractForCountryReport, String> carModelColumn;
    @FXML
    private TableColumn<ContractForCountryReport, Integer> totalExtensionDaysColumn;
    @FXML
    private TableColumn<ContractForCountryReport, Float> incomeForCashColumn;
    @FXML
    private TableColumn<ContractForCountryReport, Float> totalIncome;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<ContractForCountryReport> items = ContractForCountryReport.getContractForCountryReport();
        this.current_date.setText(LocalDate.now().toString());

        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        carBrandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        carModelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        totalExtensionDaysColumn.setCellValueFactory(new PropertyValueFactory<>("totalExtensionDays"));
        incomeForCashColumn.setCellValueFactory(new PropertyValueFactory<>("incomeForCash"));
        totalIncome.setCellValueFactory(new PropertyValueFactory<>("totalIncome"));

        table.setItems(FXCollections.observableList(items));
    }
}
