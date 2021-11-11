package cu.edu.cujae.carRent.dot;

public class BillDto {
    private float amount;
    private float specialAmount;

    public BillDto(float amount, float specialAmount) {
        this.amount = amount;
        this.specialAmount = specialAmount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getSpecialAmount() { return specialAmount; }

    public void setSpecialAmount(float specialAmount) { this.specialAmount = specialAmount; }
}
