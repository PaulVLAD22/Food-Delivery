package services;

import models.Order.Order;
import models.User.Driver;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverServices {

    public static void sendOrder(Driver driver, Order order){
        System.out.println(driver.getUsername()+"! Accept order? (Yes/No)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equals("Yes") || choice.equals("yes")){
            order.setDriver(driver);
        }
        driver.setCurrentOrder(order);
    }

    public static void deleteAccount(ArrayList<Driver> drivers, String username){
        Driver toDeleteDriver=null;
        for (Driver driver : drivers){
            if (driver.getUsername().equals(username)){
                System.out.println("Enter password:");
                Scanner scanner = new Scanner(System.in);
                String password = scanner.next();
                if (password.equals(driver.getPassword())){
                    toDeleteDriver=driver;
                }
                else{
                    System.out.println("Wrong Password");
                    System.out.println("Resetting...");
                }
            }
        }
        drivers.remove(toDeleteDriver);
    }
}
