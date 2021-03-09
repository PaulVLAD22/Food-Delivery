package service;

import model.Order.Order;
import model.User.Driver;

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
}
