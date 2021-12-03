package cu.edu.cujae.carRent.utils;

import org.w3c.dom.Text;

import java.awt.*;
import java.util.ArrayList;

public class Validations {
    public static Error loginValidation(String username, String password) {
        Error error = new Error();

        if (username.equals("")) {
            error.setErrorMsg("User field is required");
            if (password.equals("")) {
                error.setErrorMsg("User and password field are required");
            }
        } else if (password.equals("")) {
            error.setErrorMsg("Password field is required");
        }

        return error;
    }

    public static Error newUserValidation(String username, String password, String passConfirm) {
        Error error = new Error();

        if (username.equals("")) {
            error.setErrorMsg("User field is required");
            if (password.equals("")) {
                error.setErrorMsg("User and password field are required");

                if (passConfirm.equals("")) {
                    error.setErrorMsg("User, password and repeat password fields are required");
                }
            }
        } else if (password.equals("")) {
            error.setErrorMsg("Password field is required");
            if (passConfirm.equals("")) {
                error.setErrorMsg("Password and repeat password fields are required");
            }
        } else if (passConfirm.equals("")) {
            error.setErrorMsg("Repeat password field is required");
        }

        return error;
    }

    public static Error noEmptyStringValidation(ArrayList<String> stringsVals) {
        Error error = new Error();

        for (String string : stringsVals) {
            if (string == null || string.equals("")) {
                error.setErrorMsg("All fields must be filled.");
            }
        }

        return error;
    }
}
