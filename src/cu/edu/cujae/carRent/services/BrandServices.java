package cu.edu.cujae.carRent.services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class BrandServices {

    public String returnBrand(int code) throws SQLException {

        String brand;
        java.sql.Connection con = ServicesLocator.getConnection();
        String funcion = "{?= call return_brand(?)}";
        CallableStatement call = con.prepareCall(funcion);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = call.getResultSet();
        brand = result.getString(2);

        call.close();
        con.close();

        return brand;

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
