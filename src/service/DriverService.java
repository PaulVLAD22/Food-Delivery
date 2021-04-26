package service;

import model.Company;
import model.account.Driver;

import java.nio.file.Path;
import java.util.*;
import java.util.Scanner;

public class DriverService {
    private static DriverService INSTANCE;

    private DriverService() {

    }

    public static DriverService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverService();
        }
        return INSTANCE;
    }

    private OrderService orderService = OrderService.getInstance();
    private BasicService basicService = BasicService.getInstance();
    private int choice;

    public final Path DRIVERS_DIRECTORY = Path.of("resources/drivers");
    public final Path DRIVERS_PATH = Path.of(DRIVERS_DIRECTORY + "/drivers.csv");

    private CsvReader<Driver> driverCsvReader = new CsvReader<>();
    public CsvWriter<Driver> driverCsvWriter = new CsvWriter<>(DRIVERS_DIRECTORY, DRIVERS_PATH);

    public void displayMenu(Driver driver, Company company) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.Confirm Delivery");
            System.out.println("2.View expected salary");
            System.out.println("3.Log Out");
            System.out.println("4.Delete Account");
            choice = basicService.readIntChoice();

            switch (choice) {
                case 1:
                    driver.setSalary(driver.getSalary() + orderService.calculateOrder(driver.getCurrentOrder()) / 10);
                    driver.setCurrentOrder(null);
                    // stergem order-ul
                    break;
                case 2:
                    System.out.println("Your Expected salary is " + driver.getSalary());
                    break;
                case 3:
                    basicService.displayMainMenu(company);
                    break;
                case 4:
                    System.out.println("Are you sure? (1-yes/0-no)");
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        company.getDrivers().remove(driver);
                    } else {
                        displayMenu(driver, company);
                    }
                default:
                    System.out.println("Choose a valid option");
            }
        }

    }

    public List<Driver> read(Company company) {
        return driverCsvReader.read(DRIVERS_PATH, company);
    }

    public void write(Driver driver) {
        driverCsvWriter.write(driver);
    }

}
