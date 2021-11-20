package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.utils.Encription;
import cu.edu.cujae.carRent.utils.rawData.Country;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.*;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Base64;

public class mainTest {


    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //ServicesLocator.getTouristServices().insertTourist("Juan","Perez Alonso","123ewsrt56","EEUU",'M',30,"457832");
        //LocalDate date = LocalDate.of(2021, Month.APRIL,23);
        /*LocalDate date2 = LocalDate.of(2021,Month.APRIL,30);

        ServicesLocator.getContractServices().updateContract(4,2,3,3,2,1,date,date2,4);

        ContractDto con = ServicesLocator.getContractServices().returnContract(4);

        System.out.println(con.getTotalAmount());*/

//        ServicesLocator.getDriverCategoryServices().listDriverCategories();

    }
}
