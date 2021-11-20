package cu.edu.cujae.carRent.dtos;

public class CarStatusDto {

    private int code;
    private String status;

    public CarStatusDto(int code, String status) {
        this.code  = code;
        this.status = status;
    }

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}
}