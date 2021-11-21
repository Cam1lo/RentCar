package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dtos.BrandDto;
import cu.edu.cujae.carRent.dtos.ModelDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class ModelServices {

    public ModelDto getModelById(int code) throws SQLException {
        ModelDto model;
        java.sql.Connection con = ServicesLocator.getConnection();
        String funcion = "{?= call return_model(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(funcion);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        BrandDto brand = ServicesLocator.getBrandServices().getBrandById(result.getInt(2));
        model = new ModelDto(result.getInt(1),result.getString(3),brand);
        call.close();
        con.close();
        return model;
    }

    public void insertModel(String model) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call insert_model( ? )}";
        CallableStatement call = connection.prepareCall(funcion);
        call.setString(1, model);
        call.execute();
        call.close();
        connection.close();
    }

    public void deleteModel(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call delete_model( ? )}";
        CallableStatement call = connection.prepareCall(funcion);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updateModel(int code, String model) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call update_model( ?,? )}";
        CallableStatement call = connection.prepareCall(funcion);
        call.setInt(1, code);
        call.setString(2, model);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<ModelDto> listModel() throws SQLException {
        ArrayList<ModelDto> models = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_model()}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            BrandDto brand = ServicesLocator.getBrandServices().getBrandById(result.getInt(2));
            models.add(new ModelDto(result.getInt(1),result.getString(3),brand));
        }
        call.close();
        connection.close();
        return models;
    }

}
