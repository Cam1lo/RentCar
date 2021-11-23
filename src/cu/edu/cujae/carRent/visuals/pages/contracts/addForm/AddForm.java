package cu.edu.cujae.carRent.visuals.pages.contracts.addForm;

import cu.edu.cujae.carRent.dtos.*;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.StringFormatters;
import cu.edu.cujae.carRent.visuals.pages.contracts.Contracts;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private ComboBox<String> tourist;
    @FXML
    private ComboBox<String> car;
    @FXML
    private ComboBox<String> driver;
    @FXML
    private ComboBox<String> payment;
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private TextField regularBill;
    @FXML
    private TextField specialBill;
    @FXML
    private Spinner<Integer> extension;


    private Contracts parent;
    private Map<String, Integer> touristsMap = new HashMap<>();
    private Map<String, Integer> carsMap = new HashMap<>();
    private Map<String, Integer> driversMap = new HashMap<>();
    private Map<String, Integer> paymentMap = new HashMap<>();

    public void onInit(Contracts parent) throws SQLException, ClassNotFoundException {
        this.parent = parent;

        this.extension.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));

        ArrayList<TouristDto> tourists = ServicesLocator.getTouristServices().listTourist();
        ArrayList<String> touristsTextList = new ArrayList<>();
        for (TouristDto t : tourists) {
            touristsTextList.add(StringFormatters.touristToString(t));
            touristsMap.put(StringFormatters.touristToString(t), t.getCode());
        }
        this.tourist.setItems(FXCollections.observableList(touristsTextList));

        ArrayList<CarDto> cars = ServicesLocator.getCarsServices().getAvailableCars();
        ArrayList<String> carsTextList = new ArrayList<>();
        for (CarDto c : cars) {
            carsTextList.add(StringFormatters.carToString(c));
            carsMap.put(StringFormatters.carToString(c), c.getCode());
        }
        this.car.setItems(FXCollections.observableList(carsTextList));

        ArrayList<DriverDto> drivers = ServicesLocator.getDriverServices().listDriver();
        ArrayList<String> driversTextList = new ArrayList<>();
        for (DriverDto d : drivers) {
            driversTextList.add(StringFormatters.driverToString(d));
            driversMap.put(StringFormatters.driverToString(d), d.getCode());
        }
        driversTextList.add("-");
        this.driver.setItems(FXCollections.observableList(driversTextList));

        ArrayList<PaymentsDto> payments = ServicesLocator.getPaymentsServices().listPaymaent();
        ArrayList<String> paymentsTextList = new ArrayList<>();
        for (PaymentsDto p : payments) {
            paymentsTextList.add(p.getPayment());
            paymentMap.put(p.getPayment(), p.getCode());
        }
        this.payment.setItems(FXCollections.observableList(paymentsTextList));

    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNew() throws SQLException, ClassNotFoundException {
        BillDto bill = ServicesLocator.
                getBillServices().
                insertBill(Float.parseFloat(this.regularBill.getText()), Float.parseFloat(this.specialBill.getText()));


        Integer cod_tourist = this.touristsMap.get(this.tourist.getValue());
        Integer cod_car = this.carsMap.get(this.car.getValue());
        Integer cod_bill = bill.getCode();
        Integer cod_payment = this.paymentMap.get(this.payment.getValue());

        Integer cod_driver = 0;

        if (!driver.getValue().equals("-")) {
            cod_driver = this.driversMap.get(this.driver.getValue());
        }


        LocalDate fromDate = this.fromDate.getValue();
        LocalDate toDate = this.toDate.getValue();
        Integer extension = this.extension.getValue();

//        String errors = Validations.newUserValidation(username, password, passConfirm);

        ServicesLocator.
                getContractServices().
                insertContract(
                        cod_tourist,
                        cod_car,
                        cod_bill,
                        cod_payment,
                        cod_driver,
                        fromDate,
                        toDate,
                        extension
                );


        this.parent.refreshTable();
        cancel();
    }
}