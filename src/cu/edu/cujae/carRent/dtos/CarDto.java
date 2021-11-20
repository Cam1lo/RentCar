package cu.edu.cujae.carRent.dtos;

public class CarDto {
    private int code ;
    private String carID;
    private String color;
    private CarStatusDto status;
    private ModelDto model;
    private double km_driver;

    public CarDto(int code, String carID, CarStatusDto status, ModelDto model, String color, double km_diver) {
        this.code = code;
        this.carID = carID;
        this.status = status;
        this.model = model;
        this.color = color;
        this.km_driver = km_diver;

    }


    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarID() {
        return carID;
    }

    public CarStatusDto getStatus() {
        return status;
    }

    public void setStatus(CarStatusDto status) {
        this.status = status;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public double getKm_driver() { return km_driver; }

    public void setKm_driver(double km_driver) { this.km_driver = km_driver; }
}
