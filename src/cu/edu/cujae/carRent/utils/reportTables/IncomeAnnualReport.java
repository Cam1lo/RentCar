package cu.edu.cujae.carRent.utils.reportTables;

import cu.edu.cujae.carRent.services.ServicesLocator;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<IncomeAnnualReport> generatedIncomeAnnualReport(){
        ArrayList<IncomeAnnualReport> report = new ArrayList<>();
        String[] moths = {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER","TOTAL"};
        float amount = 0;
        for(String m : moths){
            report.add(new IncomeAnnualReport(m,amount));
        }
        return report;
    }

    public static ArrayList<IncomeAnnualReport> getIncomeAnnualReport() throws SQLException, ClassNotFoundException {
        return ServicesLocator.getContractServices().incomeAnnualReport();
    }
}
