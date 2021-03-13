package service;

import model.local.Local;
import model.local.Product;
import model.user.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class UserService {
    public void displayMenu(Set<Local> locals,User user){
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
            createOrder(locals,user);
        }
    }
    public void createOrder(Set<Local> locals, User user){
        // alege product ID - ul
        // apoi trimite intr-un fel la toti driverii din range 500 fata de local
        // de la care a comandat
    }

}
