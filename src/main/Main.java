package main;

import model.Company;
import model.account.Account;
import model.account.Admin;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import service.*;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        // Clasele cu .java sunt ideea mea initiala de a face un reader care citeste liniile ca List<String>
        // writer care primeste parametru string si fisier
        // si in clasele servicii pot fi gasite metodele adiacente acestei idei

        // Am abordat in final ideea de a incerca un CsvReader generic:

        // dezavantaje : instante multiple de CsvReader pt fiecare clasa
        // avantaje : mai putin cod scris, toata logica citirii este tinuta intr-o singura clasa

        // De asemenea un Csv Writer generic:

        //dezavantaje: instante multiple de CsvWriter
        //avantaje: toata logica scrierii e tinuta intr-o singura clasa

        UserService userService = new UserService();
        DriverService driverService = new DriverService();
        LocalService localService = new LocalService();
        AdminService adminService = new AdminService();
        BasicService basicService = new BasicService();

        //singleton
        Company company = Company.getInstance();

        List<User> users = userService.readUsers();
        List<Driver> drivers = driverService.read();
        List<Admin> admins = adminService.read();
        Set<Local> locals = Set.copyOf(localService.read());

        List<Account> costumers = new ArrayList<>();
        costumers.addAll(users);
        costumers.addAll(drivers);

        company.setAdmins(admins);
        company.setLocals(locals);
        company.setCostumers(costumers);

        basicService.displayMainMenu(company);

    }
}
