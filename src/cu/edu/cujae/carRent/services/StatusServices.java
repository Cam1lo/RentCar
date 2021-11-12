package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dot.CarStatusDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StatusServices {

    public CarStatusDto returnStatus(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String funcion = "{?= call return_status(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(funcion);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        CarStatusDto status = new CarStatusDto(result.getString(2));
        call.close();
        con.close();
        return status;

    }

    public void insertStatus(String status) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call insert_status( ? )}";
        CallableStatement insert = connection.prepareCall(funcion);
        insert.setString(1, status);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void deleteStatus(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call delete_staus( ? )}";
        CallableStatement insert = connection.prepareCall(funcion);
        insert.setInt(1, code);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void updateStatus(int code, String status) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call update_status( ?,? )}";
        CallableStatement call = connection.prepareCall(funcion);
        call.setInt(1, code);
        call.setString(2, status);
        call.execute();
        call.close();
        connection.close();
    }

}
