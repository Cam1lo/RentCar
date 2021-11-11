package cu.edu.cujae.carRent.services;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class StatusServices {

    public String returnStatus(int code) throws SQLException {

        String status;
        java.sql.Connection con = ServicesLocator.getConnection();
        String funcion = "{?= call return_status(?)}";
        CallableStatement call = con.prepareCall(funcion);
        call.registerOutParameter(1, Types.VARCHAR);
        call.setInt(2, code);
        call.execute();
        status = call.getString(1);
        call.close();
        con.close();

        return status;

    }

    public void insertBrand(String brand) throws SQLException {

        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call insert_brand( ? )}";
        CallableStatement insert = connection.prepareCall(funcion);
        insert.setString(1, brand);
        insert.execute();

        insert.close();
        connection.close();

    }

    public void deleteBrand(int code) throws SQLException {

        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call delete_brand(?)}";
        CallableStatement insert = connection.prepareCall(funcion);
        insert.setInt(1, code);
        insert.execute();

        insert.close();
        connection.close();
    }

    public void updateBrand(int code, String brand) throws SQLException {

        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call update_brand( ?,? )}";
        CallableStatement call = connection.prepareCall(funcion);
        call.setInt(1, code);
        call.setString(2, brand);
        call.execute();

        call.close();
        connection.close();

    }

}
