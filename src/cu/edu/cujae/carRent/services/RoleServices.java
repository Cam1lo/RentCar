package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dtos.RoleDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class RoleServices {

    public ArrayList<RoleDto> listRoles() throws SQLException {
        ArrayList<RoleDto> roles = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_roles()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            int code = result.getInt(1);
            String role = result.getString(2);
            roles.add(new RoleDto(code,role));
        }
        call.close();
        connection.close();
        return roles;
    }

    public RoleDto getRoleById(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_role(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        RoleDto role = new RoleDto(result.getInt(1),result.getString(2));
        call.close();
        con.close();
        return role;
    }

    public RoleDto getRoleByText(String role) throws SQLException {
        ArrayList<RoleDto> roles = listRoles();
        RoleDto result = null;
        boolean found = false;
       for(int i = 0;i<roles.size() && !found;i++){
            if(roles.get(i).getRoleText().equals(role)){
                found = true;
                result = new RoleDto(roles.get(i).getCode(),roles.get(i).getRoleText());
            }
        }
       return result;
    }



}
