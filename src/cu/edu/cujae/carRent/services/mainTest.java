package cu.edu.cujae.carRent.services;

import java.sql.SQLException;

public class mainTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ServicesLocator.getTouristServices().insertTourist("Juan","Perez Alonso","123ewsrt56","EEUU",'M',30,"457832");


    }
}
