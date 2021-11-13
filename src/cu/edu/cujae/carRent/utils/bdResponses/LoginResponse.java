package cu.edu.cujae.carRent.utils.bdResponses;

import cu.edu.cujae.carRent.dot.UserDto;

public class LoginResponse {

    private UserDto user;
    private String error;

    public LoginResponse(UserDto user, String error) {
        this.user = user;
        this.error = error;
    }

    public UserDto getUser() {return user;}

    public void setUser(UserDto user) {this.user = user;}

    public String getError() {return error;}

    public void setError(String error) {this.error = error;}
}
