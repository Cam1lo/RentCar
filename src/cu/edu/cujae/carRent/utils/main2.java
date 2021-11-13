package cu.edu.cujae.carRent.utils;

import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.bdResponses.LoginResponse;
import javafx.util.Pair;

import java.sql.SQLException;

public class main2 {

    public static void main(String[] args) throws SQLException {

       LoginResponse user = ServicesLocator.getUserServices().authentication("Juan", "12345678");

        System.out.println(user.getUser().getName());
    }
}
