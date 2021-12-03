package cu.edu.cujae.carRent.utils.reportTables;

public class IncomeAnnualReport {

    private String moth;
    private float incomeMonthly;

    public IncomeAnnualReport(String moth, float incomeMonthly) {
        this.moth = moth;
        this.incomeMonthly = incomeMonthly;
    }

    public String getMoth() {
        return moth;
    }

    public void setMoth(String moth) {
        this.moth = moth;
    }

    public float getIncomeMonthly() {
        return incomeMonthly;
    }

    public void setIncomeMonthly(float incomeMonthly) {
        this.incomeMonthly = incomeMonthly;
    }
}
