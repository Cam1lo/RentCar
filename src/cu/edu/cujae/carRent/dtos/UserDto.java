package cu.edu.cujae.carRent.dtos;

public class UserDto {
    private int code;
    private String name;
    private String password;
    private boolean isAdmin;

    public UserDto(int code, String name, String password, boolean isAdmin) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return isAdmin; }

    public void setAdmin(boolean admin) { isAdmin = admin; }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
