package cu.edu.cujae.carRent.visuals.pages.home;

import cu.edu.cujae.carRent.utils.reportTables.CarStatusReport;
import cu.edu.cujae.carRent.utils.reportTables.ContractForCountryReport;
import cu.edu.cujae.carRent.utils.visual.ScenesManager;
import cu.edu.cujae.carRent.visuals.reports.car.CarReportVisual;
import cu.edu.cujae.carRent.visuals.reports.carStatus.CarStatusVisual;
import cu.edu.cujae.carRent.visuals.reports.contract.ContractVisual;
import cu.edu.cujae.carRent.visuals.reports.contractForBrandAndModel.ContractForBrandAndModelVisual;
import cu.edu.cujae.carRent.visuals.reports.contractForCountry.ContractForCountryVisual;
import cu.edu.cujae.carRent.visuals.reports.driver.DriversVisual;
import cu.edu.cujae.carRent.visuals.reports.incomeAnnual.IncomeAnnualVisual;
import cu.edu.cujae.carRent.visuals.reports.touristFailContract.TouristFailContractVisual;
import cu.edu.cujae.carRent.visuals.reports.touristReport.TouristVisual;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class Home {
    @FXML
    private AnchorPane ap_content;
    @FXML
    private Label touristR;
    @FXML
    private Label touristFailContractR;
    @FXML
    private Label contractR;
    @FXML
    private Label contractCarAndModelsR;
    @FXML
    private Label contractCountriesR;
    @FXML
    private Label driversR;
    @FXML
    private Label incomeAnualR;
    @FXML
    private Label carR;
    @FXML
    private Label carStatusR;

    public void loadContract() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException {
        ContractVisual reportVisual = (ContractVisual) ScenesManager.changeApContentTo(this.ap_content, "contract");
        reportVisual.onInit();
    }

    public void loadContractForBrandAndModels() throws SQLException, ClassNotFoundException, IOException, NoSuchFieldException, IllegalAccessException {
        ContractForBrandAndModelVisual reportVisual = (ContractForBrandAndModelVisual) ScenesManager.changeApContentTo(this.ap_content, "contractForBrandAndModel");
        reportVisual.onInit();
    }

    public void loadContractCountries() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException {
        ContractForCountryVisual reportVisual = (ContractForCountryVisual) ScenesManager.changeApContentTo(this.ap_content, "contractForCountry");
        reportVisual.onInit();
    }

    public void loadTourist() throws SQLException, ClassNotFoundException, IOException, NoSuchFieldException, IllegalAccessException {
        TouristVisual reportVisual = (TouristVisual) ScenesManager.changeApContentTo(this.ap_content, "touristReport");
        reportVisual.onInit();
    }

    public void loadTouristFailContract() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException {
        TouristFailContractVisual reportVisual = (TouristFailContractVisual) ScenesManager.changeApContentTo(this.ap_content, "touristFailContract");
        reportVisual.onInit();
    }

    public void loadDrivers() throws SQLException, ClassNotFoundException, IOException, NoSuchFieldException, IllegalAccessException {
        DriversVisual reportVisual = (DriversVisual) ScenesManager.changeApContentTo(this.ap_content, "driverReport");
        reportVisual.onInit();
    }

    public void loadIncomeAnnual() throws SQLException, ClassNotFoundException, IOException, NoSuchFieldException, IllegalAccessException {
        IncomeAnnualVisual reportVisual = (IncomeAnnualVisual) ScenesManager.changeApContentTo(this.ap_content, "incomeAnnual");
        reportVisual.onInit();
    }

    public void loadCar() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException {
        CarReportVisual reportVisual = (CarReportVisual) ScenesManager.changeApContentTo(this.ap_content, "carReport");
        reportVisual.onInit();
    }

    public void loadCarStatus() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException {
        CarStatusVisual reportVisual = (CarStatusVisual) ScenesManager.changeApContentTo(this.ap_content, "carStatus");
        reportVisual.onInit();
    }
}
