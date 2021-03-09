package service;

import model.Local.Local;
import model.Local.Product;
import model.User.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class UserServices {
    public static void displayMenu(Set<Local> locals){
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
    public static void createOrder(ArrayList<Local> locals){
        // alege product ID - ul
        // apoi trimite intr-un fel la toti driverii din range 500 fata de local
        // de la care a comandat
    }

    public static void deleteAccount(ArrayList<User> users,String username){
        User toDeleteUser = null;
        for (User user : users){
            if (user.getUsername().equals(username)){
                System.out.println("Enter password:");
                Scanner scanner = new Scanner(System.in);
                String password = scanner.next();
                if (password.equals(user.getPassword())){
                    toDeleteUser=user;
                }
                else{
                    System.out.println("Wrong Password");
                    System.out.println("Resetting...");
                }
            }
        }
        users.remove(toDeleteUser);
    }
}
