package main;

import model.Company;
import model.location.Address;
import model.location.Coordinate;
import model.account.Account;
import model.account.Admin;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Menu;
import model.local.Product;
import model.location.Location;
import service.AdminService;
import service.BasicService;
import service.DriverService;
import service.UserService;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        BasicService basicService = new BasicService();
        UserService userService = new UserService();
        DriverService driverService = new DriverService();
        AdminService adminService = new AdminService();

        //singleton
        Company company = Company.getInstance();

        Account a= new User("u","",new Coordinate(20,20),"p");
        Account b = new Driver("a","",new Coordinate(25,25),"p");
        Account c = new Driver("c","ema",new Coordinate(30,30),"p");

        ArrayList<User> users = userService.readUsers();
        ArrayList<Driver> drivers = driverService.readDrivers();
        ArrayList<Admin> admins = adminService.readAdmins();
        Set<Local> locals = basicService.readLocals();

        ArrayList<Account> costumers = new ArrayList<>();
        costumers.addAll(users);
        costumers.addAll(drivers);

        company.setAdmins(admins);




        company.setLocals(locals);

        costumers.add(a);
        costumers.add(b);
        costumers.add(c);
        company.setCostumers(costumers);

        basicService.displayMainMenu(company);

    }
}
