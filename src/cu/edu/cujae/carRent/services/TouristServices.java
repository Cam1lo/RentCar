package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dot.ContractDto;
import cu.edu.cujae.carRent.dot.TouristDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class TouristServices {

    public void insertTourist(String name, String last_name, String passport, String country, String sex, int age, String telephone) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_tourist( ?,?,?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setString(1, name);
        call.setString(2, last_name);
        call.setString(3, passport);
        call.setString(4, country);
        call.setString(5, sex);
        call.setInt(6, age);
        call.setString(7, telephone);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<TouristDto> listTourist() throws SQLException, ClassNotFoundException {
        ArrayList<TouristDto> tourists = new ArrayList<TouristDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_tourist()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            int code = result.getInt(1);
            String name = result.getString(2);
            String last_name = result.getString(3);
            String passport = result.getString(4);
            int age = result.getInt(5);
            String sex = result.getString(6);
            String telephone = result.getString(7);
            String country = result.getString(8);
            tourists.add(new TouristDto(code, name, last_name, passport, age, sex, telephone, country));
        }
        call.close();
        connection.close();
        return tourists;
    }

    public void updateTourist(int code, String name, String last_name, String passport, String country, String sex, int age, String telephone) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_tourist( ?,?,?,?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, name);
        call.setString(3, last_name);
        call.setString(4, passport);
        call.setString(5, country);
        call.setString(6, sex);
        call.setInt(7, age);
        call.setString(8, telephone);
        call.execute();
        call.close();
        connection.close();
    }

    public TouristDto returnTourist(int cod) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call return_tourist( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2,cod);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        int code = result.getInt(1);
        String name = result.getString(2);
        String last_name = result.getString(3);
        String passport = result.getString(4);
        int age = result.getInt(5);
        String sex = result.getString(6);
        String telephone = result.getString(7);
        String country = result.getString(8);
        TouristDto tourists = new TouristDto(code, name, last_name, passport, age, sex, telephone, country);
        call.close();
        connection.close();
        return tourists;
    }

    public void deleteTourist(int code) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_tourist(?)}";
        ArrayList<ContractDto> contracts = ServicesLocator.getContractServices().getContractsByTouristId(code);
        for(ContractDto c : contracts){
            ServicesLocator.getContractServices().deleteContract(c.getCode());
        }
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1,code);
        call.execute();
        call.close();
        connection.close();
    }
}
