package service;

import model.Company;
import model.account.Driver;

import java.nio.file.Path;
import java.util.*;
import java.util.Scanner;

public class DriverService {
    private OrderService orderService = new OrderService();
    private BasicService basicService = new BasicService();
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

    public List<Driver> read() {
        return driverCsvReader.read(DRIVERS_PATH);
    }

    public void write(Driver driver) {
        driverCsvWriter.write(driver);
    }

//    public ArrayList<Driver> readDrivers(){
//        ArrayList<Driver> drivers = new ArrayList<>();
//        String filename = basicService.DRIVERS_PATH.toString();
//        List<String> fileOutput = basicService.readService.read(filename);
//
//        for (String line : fileOutput) {
//            String [] information = line.split(",");
//            String username = information[0];
//            String email = information[1];
//            String password = information[2];
//            String[] coordinates = information[3].split(":");
//            int coordinateX = Integer.parseInt(coordinates[0]);
//            int coordinateY = Integer.parseInt(coordinates[1]);
//
//            drivers.add(new Driver(username, email, new Coordinate(coordinateX, coordinateY), password));
//        }
//
//        return drivers;
//    }


}
