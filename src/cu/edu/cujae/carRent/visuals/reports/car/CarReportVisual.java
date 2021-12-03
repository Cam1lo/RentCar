package cu.edu.cujae.carRent.visuals.reports.car;

import cu.edu.cujae.carRent.utils.reportTables.CarReport;
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

public class CarReportVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<CarReport> table;
    @FXML
    private TableColumn<CarReport, String> idColumn;
    @FXML
    private TableColumn<CarReport, String> brandColumn;
    @FXML
    private TableColumn<CarReport, String> modelColumn;
    @FXML
    private TableColumn<CarReport, String> colorColumn;
    @FXML
    private TableColumn<CarReport, String> mileageColumn;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<CarReport> items = CarReport.getCarReport();
        this.current_date.setText(LocalDate.now().toString());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("car_Id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        mileageColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getMileage())));
        table.setItems(FXCollections.observableList(items));
    }
}
