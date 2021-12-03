package cu.edu.cujae.carRent.visuals.reports.carStatus;

import cu.edu.cujae.carRent.utils.reportTables.CarStatusReport;
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

public class CarStatusVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<CarStatusReport> table;
    @FXML
    private TableColumn<CarStatusReport, String> idColumn;
    @FXML
    private TableColumn<CarStatusReport, String> brandColumn;
    @FXML
    private TableColumn<CarStatusReport, String> statusColumn;
    @FXML
    private TableColumn<CarStatusReport, String> endOfContractColumn;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<CarStatusReport> items = CarStatusReport.getCarStatusReport();
        this.current_date.setText(LocalDate.now().toString());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("carId"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        endOfContractColumn.setCellValueFactory(data -> {
            LocalDate endOfContract = data.getValue().getEndOfContract();
            if (endOfContract != null)
                return new SimpleStringProperty(data.getValue().getEndOfContract().toString());
            else return new SimpleStringProperty("-");
        });

        table.setItems(FXCollections.observableList(items));
    }
}
