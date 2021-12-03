package cu.edu.cujae.carRent.visuals.reports.contractForBrandAndModel;

import cu.edu.cujae.carRent.utils.reportTables.CarStatusReport;
import cu.edu.cujae.carRent.utils.reportTables.ContractForBrandAndModelReport;
import javafx.beans.property.SimpleIntegerProperty;
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

public class ContractForBrandAndModelVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<ContractForBrandAndModelReport> table;
    @FXML
    private TableColumn<ContractForBrandAndModelReport, String> brandColumn;
    @FXML
    private TableColumn<ContractForBrandAndModelReport, String> modelColumn;
    @FXML
    private TableColumn<ContractForBrandAndModelReport, Integer> totalContractColumn;
    @FXML
    private TableColumn<ContractForBrandAndModelReport, Integer> totalDaysRentedColumn;
    @FXML
    private TableColumn<ContractForBrandAndModelReport, Float> incomeForCreditCardColumn;
    @FXML
    private TableColumn<ContractForBrandAndModelReport, Float> incomeForCheckColumn;
    @FXML
    private TableColumn<ContractForBrandAndModelReport, Float> incomeForCashColumn;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<ContractForBrandAndModelReport> items = ContractForBrandAndModelReport.getContractForBrandAndModelReport();
        this.current_date.setText(LocalDate.now().toString());

        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        totalContractColumn.setCellValueFactory(new PropertyValueFactory<>("totalContract"));
        totalDaysRentedColumn.setCellValueFactory(new PropertyValueFactory<>("totalDaysRented"));
        incomeForCashColumn.setCellValueFactory(new PropertyValueFactory<>("incomeForCash"));
        incomeForCheckColumn.setCellValueFactory(new PropertyValueFactory<>("incomeForCheck"));
        incomeForCreditCardColumn.setCellValueFactory(new PropertyValueFactory<>("incomeForCreditCard"));

        table.setItems(FXCollections.observableList(items));
    }
}
