package cu.edu.cujae.carRent.services;


import cu.edu.cujae.carRent.dot.BrandDto;
import cu.edu.cujae.carRent.dot.CarDto;
import cu.edu.cujae.carRent.dot.CarStatusDto;
import cu.edu.cujae.carRent.dot.ModelDto;

import java.sql.*;
import java.util.ArrayList;

public class CarsServices {

    public void insertCar(String carId, int codStatus, int codModel)throws SQLException, ClassNotFoundException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_car( ?,?,? )}";
        CallableStatement insert = connection.prepareCall(function);
        insert.setString(1,carId);
        insert.setInt(3,codStatus);
        insert.setInt(2,codModel);
        insert.execute();
        insert.close();
        connection.close();
    }

    public ArrayList<CarDto> listCars()throws SQLException, ClassNotFoundException{
        ArrayList<CarDto> cars = new ArrayList<CarDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_cars()}";
        CallableStatement insert = connection.prepareCall(function);
        insert.registerOutParameter(1, Types.OTHER);
        insert.execute();
        ResultSet result = (ResultSet) insert.getObject(1);
        while(result.next()){
            ModelDto model = ServicesLocator.getModelServices().returnModel(result.getInt(4));
            CarStatusDto status = ServicesLocator.getStatusServices().returnStatus(result.getInt(3));
            cars.add(new CarDto(result.getInt(1),result.getString(2),status,model));
        }
        insert.close();
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

    public void updateCar(int code, String carId, int codStatus, int codModel)throws SQLException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_car(?,?,?,?)}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1,code);
        call.setString(2,carId);
        call.setInt(4,codStatus);
        call.setInt(3,codModel);
        call.execute();
        call.close();
        connection.close();
    }

    public String returnCar(int code)throws SQLException{
        String car;
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_cars()}";
        connection.setAutoCommit(false);
        CallableStatement insert = connection.prepareCall(function);
        insert.registerOutParameter(1, Types.OTHER);
        insert.execute();
        ResultSet result = (ResultSet) insert.getObject(1);
        result.next();
        car = result.getString(2);
        insert.close();
        connection.close();
        return car;
    }
}
