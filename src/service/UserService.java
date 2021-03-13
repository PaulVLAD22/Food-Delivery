package service;

import model.local.Local;
import model.local.Product;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class UserService {
    public void displayMenu(Set<Local> locals){
        for (Local local:locals){
            System.out.println("Local "+local.getName());
            for (Product product : local.getMenu().getProducts()){
                System.out.println(product);
            }
        }
        System.out.println("Order? (Yes/No)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equals("Yes") || choice.equals("yes")){
        }
    }
    public void createOrder(ArrayList<Local> locals){
        // alege product ID - ul
        // apoi trimite intr-un fel la toti driverii din range 500 fata de local
        // de la care a comandat
    }

}
