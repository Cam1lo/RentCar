package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.utils.bdResponses.LoginResponse;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UserServices {

    public LoginResponse authentication(String name, String pass) throws SQLException {
        String error = "Invalid user";
        UserDto user = null;
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call list_users()}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            if (result.getString(2).equals(name)) {
                if (result.getString(3).equals(pass)) {
                    user = new UserDto(result.getInt(1), name, pass, result.getBoolean(4));
                } else {
                    error = "Wrong password";
                }
            }
        }
        LoginResponse response = new LoginResponse(user,error);
        call.close();
        con.close();
        return response;
    }

    public void insertUser(String name, String pass, boolean is_admin ) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_user( ?,?,? )}";
        CallableStatement insert = connection.prepareCall(function);
        insert.setString(1, name);
        insert.setString(2, pass);
        insert.setBoolean(3, is_admin);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void deleteUser(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_user( ? )}";
        CallableStatement insert = connection.prepareCall(function);
        insert.setInt(1, code);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void updateUser(int code, String name,String pass, boolean is_admin) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_user( ?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, name);
        call.setString(3, pass);
        call.setBoolean(4, is_admin);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<UserDto> listUsers()throws SQLException, ClassNotFoundException{
        ArrayList<UserDto> user = new ArrayList<UserDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_user()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            int code = result.getInt(1);
            String name = result.getString(2);
            String pass = result.getString(3);
            boolean is_admin = result.getBoolean(4);
            user.add(new UserDto(code,name,pass,is_admin));
        }
        call.close();
        connection.close();
        return user;
    }
}
