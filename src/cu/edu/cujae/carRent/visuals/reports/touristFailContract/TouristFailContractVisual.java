package cu.edu.cujae.carRent.visuals.reports.touristFailContract;

import cu.edu.cujae.carRent.utils.reportTables.IncomeAnnualReport;
import cu.edu.cujae.carRent.utils.reportTables.TouristFailContractReport;
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

public class TouristFailContractVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<TouristFailContractReport> table;
    @FXML
    private TableColumn<TouristFailContractReport, String> nameColumn;
    @FXML
    private TableColumn<TouristFailContractReport, String> lastNameColumn;
    @FXML
    private TableColumn<TouristFailContractReport, String> finalDateColumn;
    @FXML
    private TableColumn<TouristFailContractReport, String> deliveryDateColumn;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<TouristFailContractReport> items = TouristFailContractReport.getTouristFailContractReport();
        this.current_date.setText(LocalDate.now().toString());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        finalDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFinalDate().toString()));
        deliveryDateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDeliveryDate().toString()));

        table.setItems(FXCollections.observableList(items));
    }
}
