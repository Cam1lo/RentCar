package cu.edu.cujae.carRent.dot;

public class BrandDto {
    private String brand;
    private int code;

    public BrandDto(int code, String brand) {
        this.code = code;
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
