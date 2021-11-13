package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dot.BrandDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class BrandServices {

    public BrandDto returnBrand(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_brand(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        BrandDto brand = new BrandDto(result.getInt(1), result.getString(2));
        call.close();
        con.close();
        return brand;

    }

    public void insertBrand(String brand) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_brand( ? )}";
        CallableStatement insert = connection.prepareCall(function);
        insert.setString(1, brand);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void deleteBrand(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_brand(?)}";
        CallableStatement insert = connection.prepareCall(function);
        insert.setInt(1, code);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void updateBrand(int code, String brand) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_brand( ?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, brand);
        call.execute();
        call.close();
        connection.close();
    }


}
