package cu.edu.cujae.carRent.dtos;

public class RoleDto {

    private int code;
    private String roleText;

    public RoleDto(int code, String roleText) {
        this.code = code;
        this.roleText = roleText;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getRoleText() { return roleText; }

    public void setRoleText(String roleText) { this.roleText = roleText; }
}
