package cu.edu.cujae.carRent.utils.reportTables;

public class ContractForCountryReport {

    private String country;
    private String carBran;
    private String carModel;
    private int totalExtensionDays;
    private float incomeForCash;
    private float totalIncome;

    public ContractForCountryReport(String country, String carBran, String carModel, int totalExtensionDays, float incomeForCash, float totalIncome) {
        this.country = country;
        this.carBran = carBran;
        this.carModel = carModel;
        this.totalExtensionDays = totalExtensionDays;
        this.incomeForCash = incomeForCash;
        this.totalIncome = totalIncome;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarBran() {
        return carBran;
    }

    public void setCarBran(String carBran) {
        this.carBran = carBran;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getTotalExtensionDays() {
        return totalExtensionDays;
    }

    public void setTotalExtensionDays(int totalExtensionDays) {
        this.totalExtensionDays = totalExtensionDays;
    }

    public float getIncomeForCash() {
        return incomeForCash;
    }

    public void setIncomeForCash(float incomeForCash) {
        this.incomeForCash = incomeForCash;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
    }


}
