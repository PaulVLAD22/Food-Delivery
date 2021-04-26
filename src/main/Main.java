package main;

import model.Company;
import model.account.Account;
import model.account.Admin;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.order.Order;
import service.*;

import java.util.*;
import java.util.List;


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

        UserService userService = UserService.getInstance();
        DriverService driverService = DriverService.getInstance();
        LocalService localService = LocalService.getInstance();
        AdminService adminService = AdminService.getInstance();
        BasicService basicService = BasicService.getInstance();
        OrderService orderService = OrderService.getInstance();

        Company company = Company.getInstance();

        List<User> users = userService.readUsers(company);
        List<Driver> drivers = driverService.read(company);

        List<Account> costumers = new ArrayList<>();
        costumers.addAll(users);
        costumers.addAll(drivers);
        company.setCostumers(costumers);

        List<Admin> admins = adminService.read(company);
        company.setAdmins(admins);
        Set<Local> locals = Set.copyOf(localService.read(company));
        company.setLocals(locals);
        List <Order> orders = orderService.read(company);
        company.setOrders(orders);

        basicService.displayMainMenu(company);

    }
}
