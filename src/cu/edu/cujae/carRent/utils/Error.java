package cu.edu.cujae.carRent.utils;

public class Error {
    private String errorMsg;

    public Error(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Error() {
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
