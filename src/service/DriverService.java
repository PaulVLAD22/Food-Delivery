package service;

import model.Company;
import model.account.Driver;

import java.util.Scanner;

public class DriverService extends BasicService {

    public void displayMenu(Driver driver, Company company){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1.Confirm Delivery");
            System.out.println("2.View expected salary");
            System.out.println("3.Log Out");
            System.out.println("4.Delete Account");
            choice = readIntChoice();

            switch (choice) {
                case 1:
                    driver.setSalary(driver.getSalary()+driver.getCurrentOrder().calculateOrder()/10);
                    driver.setCurrentOrder(null);
                    break;
                case 2:
                    System.out.println("Your Expected salary is "+ driver.getSalary());
                    break;
                case 3:
                    displayMainMenu(company);
                    break;
                case 4:
                    System.out.println("Are you sure? (1-yes/0-no)");
                    choice= scanner.nextInt();
                    if (choice==1) {
                        company.getDrivers().remove(driver);
                    }
                    else{
                        displayMenu(driver,company);
                    }
                default:
                    System.out.println("Choose a valid option");
            }
        }

    }

}
