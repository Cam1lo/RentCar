package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.dtos.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContractServices {

    public ArrayList<ContractDto> listContract() throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> contracts = new ArrayList<ContractDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_contract()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            int code = result.getInt(1);
            TouristDto tourist = ServicesLocator.getTouristServices().getTouristById(result.getInt(2));
            CarDto car = ServicesLocator.getCarsServices().getCarById(result.getInt(3));
            BillDto bill = ServicesLocator.getBillServices().returnBill(result.getInt(4));
            PaymentsDto payment = ServicesLocator.getPaymentsServices().getPaymentById(result.getInt(5));
            DriverDto driver = ServicesLocator.getDriverServices().getDriverById(result.getInt(6));
            LocalDate starting_date = result.getDate(7).toLocalDate();
            LocalDate final_date = result.getDate(8).toLocalDate();
            int extension = result.getInt(9);
            contracts.add(new ContractDto(code, tourist, car, bill, payment, driver, starting_date, final_date, extension));
        }
        call.close();
        connection.close();
        return contracts;
    }

    public ContractDto getContractById(int code) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call return_contract( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2,code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        TouristDto tourist = ServicesLocator.getTouristServices().getTouristById(result.getInt(2));
        CarDto car = ServicesLocator.getCarsServices().getCarById(result.getInt(3));
        BillDto bill = ServicesLocator.getBillServices().returnBill(result.getInt(4));
        PaymentsDto payment = ServicesLocator.getPaymentsServices().getPaymentById(result.getInt(5));
        DriverDto driver = ServicesLocator.getDriverServices().getDriverById(result.getInt(6));
        LocalDate starting_date = result.getDate(7).toLocalDate();
        LocalDate final_date = result.getDate(8).toLocalDate();
        int extension = result.getInt(9);
        ContractDto contract = new ContractDto(code, tourist, car, bill, payment, driver, starting_date, final_date, extension);
        call.close();
        connection.close();
        return contract;
    }

    public void insertContract(int cod_tourist, int cod_car, int cod_bill, int cod_payment, int cod_driver, LocalDate starting_date, LocalDate final_date, int extension)throws SQLException, ClassNotFoundException{
        BillDto bill = ServicesLocator.getBillServices().returnBill(cod_bill);
        ContractDto contract = new ContractDto(bill,starting_date,final_date,extension);
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_contract( ?,?,?,?,?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1,cod_tourist);
        call.setInt(2,cod_car);
        call.setInt(3,cod_bill);
        call.setInt(4,cod_payment);
        if(cod_driver!=0){
            call.setInt(5,cod_driver);
        }else{call.setObject(5,null);}
        call.setDate(6,java.sql.Date.valueOf(starting_date));
        call.setDate(7,java.sql.Date.valueOf(final_date));
        call.setInt(8,extension);
        call.setDouble(9,contract.getTotalAmount());
        call.execute();
        call.close();
        connection.close();
    }

    public void updateContract(int code,int cod_tourist, int cod_car, int cod_bill, int cod_payment, int cod_driver, LocalDate starting_date, LocalDate final_date, int extension)throws SQLException, ClassNotFoundException{
        BillDto bill = ServicesLocator.getBillServices().returnBill(cod_bill);
        ContractDto contract = new ContractDto(bill,starting_date,final_date,extension);
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_contract( ?,?,?,?,?,?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1,code);
        call.setInt(2,cod_tourist);
        call.setInt(3,cod_car);
        call.setInt(4,cod_bill);
        call.setInt(5,cod_payment);
        if(cod_driver!=0){
            call.setInt(6,cod_driver);
        }else{call.setObject(6,null);}
        call.setDate(7,java.sql.Date.valueOf(starting_date));
        call.setDate(8,java.sql.Date.valueOf(final_date));
        call.setInt(9,extension);
        call.setDouble(10,contract.getTotalAmount());
        call.execute();
        call.close();
        connection.close();
    }

    public void deleteContract(int code)throws SQLException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_contract(?)}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1,code);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<ContractDto> getContractsByTouristId(int id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for(ContractDto c : contracts){
            if(c.getTourist().getCode() == id){
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<ContractDto> getContractsByBillId(int bill_id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for(ContractDto c : contracts){
            if(c.getBill().getCode() == bill_id){
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<ContractDto> getContractsByCarId(int car_id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for(ContractDto c : contracts){
            if(c.getCar().getCode() == car_id){
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<ContractDto> getContractsByDriverId(int driver_id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for(ContractDto c : contracts){
            if(c.getDriver().getCode() == driver_id){
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<ContractDto> getContractsByPaymentId(int payment_id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for(ContractDto c : contracts){
            if(c.getPayment().getCode() == payment_id){
                result.add(c);
            }
        }
        return result;
    }

}
