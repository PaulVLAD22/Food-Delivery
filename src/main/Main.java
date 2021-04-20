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

        CsvReader<User> userCsvReader = new CsvReader<>();
        CsvReader<Driver> driverCsvReader = new CsvReader<>();
        CsvReader<Local> localCsvReader = new CsvReader<>();
        CsvReader<Admin> adminCsvReader = new CsvReader<>();

        BasicService basicService = new BasicService();

        //singleton
        Company company = Company.getInstance();


        List<User> users = userCsvReader.read(basicService.USERS_PATH);
        List<Driver> drivers = driverCsvReader.read(basicService.DRIVERS_PATH);
        List<Admin> admins = adminCsvReader.read(basicService.ADMIN_PATH);
        Set<Local> locals = Set.copyOf(localCsvReader.read(basicService.LOCALS_PATH));

        List<Account> costumers = new ArrayList<>();
        costumers.addAll(users);
        costumers.addAll(drivers);

        company.setAdmins(admins);
        company.setLocals(locals);
        company.setCostumers(costumers);

        basicService.displayMainMenu(company);

    }
}
