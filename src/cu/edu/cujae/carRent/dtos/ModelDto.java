package cu.edu.cujae.carRent.dtos;

public class ModelDto {
    private int code;
    private String model;
    private BrandDto brand;

    public ModelDto(int code, String model, BrandDto brand) {
        this.code = code;
        this.model = model;
        this.brand = brand;
    }

    public String getModelText() {
        return model;
    }

    public void setModelText(String model) {
        this.model = model;
    }

    public BrandDto getBrand() { return brand; }

    public void setBrand(BrandDto brand) { this.brand = brand; }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
