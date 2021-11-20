package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dtos.DriverDto;
import cu.edu.cujae.carRent.dtos.DriversCategoriesDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class DriverServices {

    public void insertDriver(String id, String name, String last_name, String address, int category) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_driver( ?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setString(1, id);
        call.setString(2, name);
        call.setString(3, last_name);
        call.setString(4, address);
        call.setInt(5, category);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<DriverDto> listDriver() throws SQLException, ClassNotFoundException {
        ArrayList<DriverDto> drivers = new ArrayList<DriverDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_driver()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            int code = result.getInt(1);
            String id = result.getString(2);
            String name = result.getString(3);
            String last_name = result.getString(4);
            String address = result.getString(5);
            DriversCategoriesDto category = ServicesLocator.getDriverCategoryServices().returnDriverCategory(result.getInt(6));
            drivers.add(new DriverDto(code,id,name,last_name,address,category));
        }
        call.close();
        connection.close();
        return drivers;
    }

    public void updateDriver(int code, String id, String name, String last_name, String address, int category) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_driver( ?,?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, id);
        call.setString(3, name);
        call.setString(4, last_name);
        call.setString(5, address);
        call.setInt(6, category);
        call.execute();
        call.close();
        connection.close();
    }

    public DriverDto returnDriver(int cod) throws SQLException, ClassNotFoundException {
        DriverDto driver = null;
        if(cod != 0) {
            java.sql.Connection connection = ServicesLocator.getConnection();
            String function = "{?= call return_driver( ? )}";
            connection.setAutoCommit(false);
            CallableStatement call = connection.prepareCall(function);
            call.registerOutParameter(1, Types.OTHER);
            call.setInt(2, cod);
            call.execute();
            ResultSet result = (ResultSet) call.getObject(1);
            result.next();
            int code = result.getInt(1);
            String id = result.getString(2);
            String name = result.getString(3);
            String last_name = result.getString(4);
            String address = result.getString(5);
            DriversCategoriesDto category = ServicesLocator.getDriverCategoryServices().returnDriverCategory(result.getInt(6));
            driver = new DriverDto(code, id, name, last_name, address, category);
            call.close();
            connection.close();
        }
        return driver;
    }

    public void deleteDriver(int code)throws SQLException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_driver(?)}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1,code);
        call.execute();
        call.close();
        connection.close();
    }
}
