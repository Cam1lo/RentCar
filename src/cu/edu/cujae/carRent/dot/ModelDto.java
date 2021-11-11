package cu.edu.cujae.carRent.dot;

public class ModelDto {
    private String model;
    private BrandDto brand;

    public ModelDto(String model, BrandDto brand) {
        this.model = model;
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BrandDto getBrand() { return brand; }

    public void setBrand(BrandDto brand) { this.brand = brand; }
}
