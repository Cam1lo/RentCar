package cu.edu.cujae.carRent.services;

import cu.edu.cujae.carRent.utils.Connection;

import java.sql.SQLException;

public class ServicesLocator {

    private static CarsServices carsServices = null;
    private static BrandServices brandServices = null;
    private static ModelServices modelServices = null;
    private static UserServices userServices = null;

    public static CarsServices getCarsServices() {
        if (carsServices == null) {
            carsServices = new CarsServices();
        }
        return carsServices;
    }

    public static UserServices getUserServices() {
        if (userServices == null) {
            userServices = new UserServices();
        }
        return userServices;
    }

    public static BrandServices getBrandServices() {
        if (brandServices == null) {
            brandServices = new BrandServices();
        }
        return brandServices;
    }

    public static ModelServices getModelServices() {
        if (modelServices == null) {
            modelServices = new ModelServices();
        }
        return modelServices;
    }

    public static java.sql.Connection getConnection() {
        Connection connection = null;
        try {
            connection = new Connection("localhost", "Rent_Car", "postgres", "1234");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection.getConnection();
    }

}
