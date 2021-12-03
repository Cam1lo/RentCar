package cu.edu.cujae.carRent.visuals.reports.incomeAnnual;

import cu.edu.cujae.carRent.utils.reportTables.DriverReport;
import cu.edu.cujae.carRent.utils.reportTables.IncomeAnnualReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class IncomeAnnualVisual {
    @FXML
    private Label current_date;
    @FXML
    private TableView<IncomeAnnualReport> table;
    @FXML
    private TableColumn<IncomeAnnualReport, String> monthColumn;
    @FXML
    private TableColumn<IncomeAnnualReport, Float> incomeMonthlyColumn;

    public void onInit() throws SQLException, ClassNotFoundException {
        ArrayList<IncomeAnnualReport> items = IncomeAnnualReport.getIncomeAnnualReport();
        this.current_date.setText(LocalDate.now().toString());

        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        incomeMonthlyColumn.setCellValueFactory(new PropertyValueFactory<>("incomeMonthly"));

        table.setItems(FXCollections.observableList(items));
    }
}
