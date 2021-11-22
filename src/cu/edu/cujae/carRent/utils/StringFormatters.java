package cu.edu.cujae.carRent.utils;

import cu.edu.cujae.carRent.dtos.BillDto;
import cu.edu.cujae.carRent.dtos.CarDto;
import cu.edu.cujae.carRent.dtos.DriverDto;
import cu.edu.cujae.carRent.dtos.TouristDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class StringFormatters {
    public static String twoDatesToString(LocalDate date1, LocalDate date2) {
        String date1String = date1.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String date2String = date2.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

        return date1String + " -> " + date2String;
    }

    public static String billsToString(BillDto bill) {
        String regular = String.valueOf(bill.getAmount());
        String special = "";
        if (bill.getSpecialAmount() > 0) {
            special = "special: " + String.valueOf(bill.getSpecialAmount());
        }
        return regular + special;
    }

    public static String touristToString(TouristDto t) {
        return t.getIdPassport() + " " + t.getName() + " " + t.getLastName();
    }

    public static String carToString(CarDto c) {
        return c.getCarID() + " ";
    }

    public static String driverToString(DriverDto d) {
        return d.getId() + " " + d.getName() + " " + d.getLastName();
    }
}
