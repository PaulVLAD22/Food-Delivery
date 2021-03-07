package services;

import models.Local.Local;
import models.Local.Product;
import models.User.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserServices {
    public static void displayMenu(ArrayList<Local> locals){
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