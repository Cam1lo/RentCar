package cu.edu.cujae.carRent.dot;

public class PaymentsDto {
    private int code;
    private String payment;

    public PaymentsDto(String payment) {
        this.payment = payment;
    }

    public String getPayment() { return payment; }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
