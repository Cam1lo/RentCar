package cu.edu.cujae.carRent.utils.tables;

import cu.edu.cujae.carRent.dtos.ContractDto;
import cu.edu.cujae.carRent.dtos.TouristDto;
import cu.edu.cujae.carRent.services.ServicesLocator;

import java.sql.SQLException;
import java.util.ArrayList;

public class TouristTable {

    private String country;
    private String name;
    private String last_name;
    private String passport;
    private int amount_contract;
    private float rent_value;

    public TouristTable(String country, String name, String last_name, String passport, int amount_contract, float rent_value) {
        this.country = country;
        this.name = name;
        this.last_name = last_name;
        this.passport = passport;
        this.amount_contract = amount_contract;
        this.rent_value = rent_value;
    }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLast_name() { return last_name; }

    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getPassport() { return passport; }

    public void setPassport(String passport) { this.passport = passport; }

    public int getAmount_contract() { return amount_contract; }

    public void setAmount_contract(int amount_contract) { this.amount_contract = amount_contract; }

    public float getRent_value() { return rent_value; }

    public void setRent_value(float rent_value) { this.rent_value = rent_value; }

    public static ArrayList<TouristTable> getCarTable() throws SQLException, ClassNotFoundException {
        ArrayList<TouristTable> table = new ArrayList<>();
        ArrayList<TouristDto> tourists = ServicesLocator.getTouristServices().listTourist();
        for(TouristDto e : tourists){
            table.add(new TouristTable(e.getCountry(),e.getName(),e.getLastName(),e.getIdPassport(),countContract(e.getCode()),totalRentValue(e.getCode())));
        }
        return table;
    }

    private static int countContract(int code_tourist) throws SQLException, ClassNotFoundException {
        int count = 0;
        ArrayList<ContractDto> contracts = ServicesLocator.getContractServices().listContract();
        for(ContractDto con : contracts){
            if(code_tourist == con.getTourist().getCode()){
                count++;
            }
        }
        return count;
    }

    private static float totalRentValue(int code_tourist) throws SQLException, ClassNotFoundException {
        float amount = 0;
        ArrayList<ContractDto> contracts = ServicesLocator.getContractServices().listContract();
        for (ContractDto con : contracts) {
            if (code_tourist == con.getTourist().getCode()) {
                amount += con.getTotalAmount();
            }
        }
        return amount;
    }
}
