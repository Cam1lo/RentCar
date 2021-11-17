package cu.edu.cujae.carRent.utils;

public class Validations {
    public static String loginValidation(String username, String password) {
        String error = "";

        if (username.equals("")) {
            error = "User field is required";
            if (password.equals("")) {
                error = "User and password fields are required";
            }
        } else if (password.equals("")) {
            error = "Password field is required";
        }

        return error;
    }

    public static String newUserValidation(String username, String password, String passConfirm) {
        String error = "";

        if (username.equals("")) {
            error = "User field is required";
            if (password.equals("")) {
                error = "User and password fields are required";

                if (passConfirm.equals("")) {
                    error = "User, password and repeat password fields are required";
                }
            }
        } else if (password.equals("")) {
            error = "Password field is required";
            if (passConfirm.equals("")) {
                error = "Password and repeat password fields are required";
            }
        } else if (passConfirm.equals("")) {
            error = "Repeat password field is required";
        }

        return error;
    }
}
