package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dtos.RoleDto;
import cu.edu.cujae.carRent.dtos.UserDto;
import cu.edu.cujae.carRent.utils.Encription;
import cu.edu.cujae.carRent.utils.bdResponses.LoginResponse;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UserServices {

    public LoginResponse authentication(String name, String pass) throws SQLException, NoSuchAlgorithmException {
        String error = "Invalid user";
        UserDto user = null;
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call list_users_with_role()}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            if (result.getString(2).equals(name)) {
                if (result.getString(3).equals(Encription.encrypt(pass))) {
                    RoleDto role = ServicesLocator.getRoleServices().getRoleByText(result.getString(4));
                    user = new UserDto(result.getInt(1), name, Encription.encrypt(pass), role);
                } else {
                    error = "Wrong password";
                }
            }
        }
        LoginResponse response = new LoginResponse(user, error);
        call.close();
        con.close();
        return response;
    }

    public void insertUser(String name, String pass, RoleDto role) throws SQLException, NoSuchAlgorithmException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_user( ?,? )}";
        CallableStatement insert = connection.prepareCall(function);
        insert.setString(1, name);
        insert.setString(2, Encription.encrypt(pass));
        insert.execute();
        insert.close();
        connection.setAutoCommit(false);
        String function1 = "{?= call get_last_user()}";
        CallableStatement call = connection.prepareCall(function1);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        connection.setAutoCommit(true);
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        String function2 = "{call insert_role_user(?,?)}";
        CallableStatement call1 = connection.prepareCall(function2);
        call1.setInt(1, result.getInt(1));
        call1.setInt(2, role.getCode());
        call1.execute();
        call.close();
        call1.close();
        connection.close();
    }

    public void deleteUser(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function1 = "{call delete_role_user( ? )}";
        CallableStatement call = connection.prepareCall(function1);
        call.setInt(1, code);
        call.execute();
        call.close();
        String function = "{call delete_user( ? )}";
        CallableStatement insert = connection.prepareCall(function);
        insert.setInt(1, code);
        insert.execute();
        insert.close();
        connection.close();
    }

    public void updateUser(int code, String name, String pass, RoleDto role) throws SQLException, NoSuchAlgorithmException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_user( ?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, name);
        call.setString(3, Encription.encrypt(pass));
        call.execute();
        call.close();
        String function1 = "{call update_role_user( ?,? )}";
        CallableStatement call1 = connection.prepareCall(function1);
        call1.setInt(1, code);
        call1.setInt(2, role.getCode());
        call1.execute();
        call1.close();
        connection.close();
    }

    public ArrayList<UserDto> listUsers() throws SQLException, ClassNotFoundException {
        ArrayList<UserDto> user = new ArrayList<UserDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_users_with_role()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            int code = result.getInt(1);
            String name = result.getString(2);
            String pass = result.getString(3);
            RoleDto role = ServicesLocator.getRoleServices().getRoleByText(result.getString(4));
            user.add(new UserDto(code, name, pass, role));
        }
        call.close();
        connection.close();
        return user;
    }

    public UserDto getUserById(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= get_user_with_role(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        RoleDto role = ServicesLocator.getRoleServices().getRoleByText(result.getString(4));
        UserDto user = new UserDto(result.getInt(1), result.getString(2), result.getString(3), role);
        call.close();
        con.close();
        return user;
    }
}
