package service;

import model.order.Order;
import model.user.Driver;

import java.util.Scanner;

public class DriverService {

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
