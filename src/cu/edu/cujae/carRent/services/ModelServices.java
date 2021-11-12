package cu.edu.cujae.carRent.services;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ModelServices {

    public String returnModel(int code) throws SQLException {
        String status;
        java.sql.Connection con = ServicesLocator.getConnection();
        String funcion = "{?= call return_model(?)}";
        CallableStatement call = con.prepareCall(funcion);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        status = call.getString(1);
        call.close();
        con.close();
        return status;
    }

    public void insertModel(String model) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call insert_model( ? )}";
        CallableStatement insert = connection.prepareCall(funcion);
        insert.setString(1, model);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void deleteStatus(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call delete_model( ? )}";
        CallableStatement insert = connection.prepareCall(funcion);
        insert.setInt(1, code);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void updateBrand(int code, String model) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call update_model( ?,? )}";
        CallableStatement call = connection.prepareCall(funcion);
        call.setInt(1, code);
        call.setString(2, model);
        call.execute();
        call.close();
        connection.close();
    }

}
