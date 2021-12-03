package cu.edu.cujae.carRent.utils;

import org.w3c.dom.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean validateLetters(String txt) {

        String regx = "^[a-zA-Z\\s]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();

    }

    public static boolean IdCorrect(String  text){
        if(text.length()==11){
            for(int i = 0; i< text.length(); i++){
                if(!Character.isDigit(text.charAt(i))){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    public static boolean validatePassportId(String text){
        if(!Character.isUpperCase(text.charAt(0))){
            return false;
        }else{
            if(text.length()!=7){
                return false;
            }
            for(int i = 1; i<text.length(); i++){
                if(!Character.isDigit(text.charAt(i))){
                    return false;
                }
            }
        }
        return true;
    }
}