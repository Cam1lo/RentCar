package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dot.UserDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UserServices {

    public UserDto authentication(String name, String pass)throws SQLException {

        UserDto user = null;
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call list_users()}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            if(result.getString(2).equals(name) && result.getString(3).equals(pass)){
                user = new UserDto(name, pass, result.getBoolean(4));
            }
        }
        call.close();
        con.close();
        return user;

    }
}
