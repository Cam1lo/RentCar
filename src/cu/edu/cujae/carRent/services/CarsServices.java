package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dtos.BrandDto;
import cu.edu.cujae.carRent.dtos.CarDto;
import cu.edu.cujae.carRent.dtos.CarStatusDto;
import cu.edu.cujae.carRent.dtos.ModelDto;

import java.sql.*;
import java.util.ArrayList;

public class CarsServices {

    public void insertCar(String carId, int codStatus, int codBrand, String color, double mileage)throws SQLException, ClassNotFoundException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_car( ?,?,?,?,? )}";
        String id = "K " + carId;
        CallableStatement call = connection.prepareCall(function);
        call.setString(1,id);
        call.setInt(2,codStatus);
        call.setInt(3,codBrand);
        call.setString(4,color);
        call.setDouble(5,mileage);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<CarDto> listCars()throws SQLException, ClassNotFoundException{
        ArrayList<CarDto> cars = new ArrayList<CarDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_cars()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            BrandDto brand = ServicesLocator.getBrandServices().getBrandById(result.getInt(4));
            CarStatusDto status = ServicesLocator.getStatusServices().getStatusById(result.getInt(3));
            String color = result.getString(5);
            double mileage = result.getDouble(6);
            cars.add(new CarDto(result.getInt(1),result.getString(2),status,brand,color,mileage));
        }
        call.close();
        connection.close();
        return cars;
    }

    public void deleteCar(int code)throws SQLException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_car(?)}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1,code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updateCar(int code, String carId, int codStatus, int codBrand, String color, double mileage)throws SQLException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_car( ?,?,?,?,?,? )}";
        String id = "K " + carId;
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1,code);
        call.setString(2,id);
        call.setInt(3,codStatus);
        call.setInt(4,codBrand);
        call.setString(5,color);
        call.setDouble(6,mileage);
        call.execute();
        call.close();
        connection.close();
    }

    public CarDto getCarById(int code)throws SQLException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call return_car( ? )}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2,code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        BrandDto brand = ServicesLocator.getBrandServices().getBrandById(result.getInt(4));
        CarStatusDto status = ServicesLocator.getStatusServices().getStatusById(result.getInt(3));
        String color = result.getString(5);
        double km_driver = result.getDouble(6);
        CarDto car = new CarDto(result.getInt(1),result.getString(2),status,brand,color,km_driver);
        call.close();
        connection.close();
        return car;
    }
}
