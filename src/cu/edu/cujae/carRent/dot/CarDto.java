package cu.edu.cujae.carRent.dot;

public class CarDto {
    private int code ;
    private String carID;
    private CarStatusDto status;
    private ModelDto model;

    public CarDto(int code, String carID, CarStatusDto status, ModelDto model) {
        this.code = code;
        this.carID = carID;
        this.status = status;
        this.model = model;
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
}
