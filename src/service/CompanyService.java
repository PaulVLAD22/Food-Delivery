package service;

import model.Company;
import model.Coordinates;
import model.local.Local;
import model.user.Driver;
import model.user.Account;
import model.user.User;

import java.util.Scanner;

public class CompanyService {
    UserService userService = new UserService();
    DriverService driverService = new DriverService();
    LocalService localService = new LocalService();

    public void displayMenu(Company c, User user){
        userService.displayMenu(c.getLocals(),user);

    }

    public void addAccount(Company company, Account account){
        company.getPeople().add(account);
        if (account instanceof User){
            company.getUsers().add((User) account);
        }
        else{
            company.getDrivers().add((Driver) account);
        }
    }
    public void removeAccount(Company company, Account account){
        Account toDeleteAccount = null;
        for (Account a : company.getPeople()){
            if (a.equals(account)){
                System.out.println("Enter password:");
                Scanner scanner = new Scanner(System.in);
                String password = scanner.next();
                if (password.equals(a.getPassword())){
                    toDeleteAccount =a;
                }
                else{
                    System.out.println("Wrong Password");
                    System.out.println("Resetting...");
                }
            }
        }
        company.getPeople().remove(toDeleteAccount);
    }

    public double calculateDistance(Coordinates c1, Coordinates c2){
        return Math.sqrt(Math.pow(c2.getX()-c1.getX(),2)+
                Math.pow(c2.getY()-c1.getY(),2));
    }
}
