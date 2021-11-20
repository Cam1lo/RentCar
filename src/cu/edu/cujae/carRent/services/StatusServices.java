package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dtos.CarStatusDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class StatusServices {

    public CarStatusDto returnStatus(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_status(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        CarStatusDto status = new CarStatusDto(result.getInt(1), result.getString(2));
        call.close();
        con.close();
        return status;

    }

    public void insertStatus(String status) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_status( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setString(1, status);
        call.execute();
        call.close();
        connection.close();
    }

    public void deleteStatus(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_staus( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updateStatus(int code, String status) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_status( ?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, status);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<CarStatusDto> listStatus() throws SQLException {
        ArrayList<CarStatusDto> statuses = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_status()}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            statuses.add(new CarStatusDto(result.getInt(1),result.getString(2)));
        }
        call.close();
        connection.close();
        return statuses;
    }

}
