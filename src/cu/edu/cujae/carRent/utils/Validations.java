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
}
