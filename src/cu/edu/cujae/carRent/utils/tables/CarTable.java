package cu.edu.cujae.carRent.utils.tables;

import cu.edu.cujae.carRent.dtos.CarDto;
import cu.edu.cujae.carRent.services.ServicesLocator;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarTable {

    private String car_Id;
    private String brand;
    private String model;
    private String color;
    private double mileage;

    public CarTable(String car_Id, String brand, String model, String color, double mileage) {
        this.car_Id = car_Id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
    }

    public String getCar_Id() { return car_Id; }

    public void setCar_Id(String car_Id) { this.car_Id = car_Id; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public double getMileage() { return mileage; }

    public void setMileage(double km_driver) { this.mileage = km_driver; }

    public static ArrayList<CarTable> getCarTable()throws SQLException, ClassNotFoundException {
        ArrayList<CarTable> table = new ArrayList<>();
        ArrayList<CarDto> cars = ServicesLocator.getCarsServices().listCars();
        for(CarDto e : cars){
            table.add(new CarTable(e.getCarID(),e.getModel().getBrand().getBrandText(),e.getModel().getModelText(),e.getColor(),e.getMilaege()));
        }
        return table;
    }
}
