package cu.edu.cujae.carRent.dot;

public class PaymentsDto {

    private String payment;

    public PaymentsDto(String payment) {
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
