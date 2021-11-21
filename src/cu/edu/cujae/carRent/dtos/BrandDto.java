package cu.edu.cujae.carRent.dtos;

public class BrandDto {
    private String brand;
    private int code;

    public BrandDto(int code, String brand) {
        this.code = code;
        this.brand = brand;
    }

    public String getBrandText() {
        return brand;
    }

    public void setBrandText(String brand) {
        this.brand = brand;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
