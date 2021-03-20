package service;

import model.Company;
import model.Coordinate;
import model.account.Account;
import model.account.Admin;
import model.account.Driver;
import model.account.User;

import java.util.ArrayList;
import java.util.Scanner;

public class BasicService {
    protected Scanner scanner = new Scanner(System.in);
    protected int choice;

    public void displayMainMenu(Company company){
        while (true) {
            System.out.println("1. Login as User or Driver");
            System.out.println("2. Login as Administrator");
            System.out.println("3. Sign up as User");
            System.out.println("4. Sign up as Driver");
            System.out.println("10. Exit");
            choice=scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Username:");
                    String username = scanner.next();
                    System.out.println("Password:");
                    String password = scanner.next();
                    Account account = this.login(username,password,company.getCostumers());
                    if (account instanceof User){
                        UserService userService = new UserService();
                        userService.displayMenu((User) account, company);

                    }
                    else if (account instanceof Driver){
                        DriverService driverService = new DriverService();
                        driverService.displayMenu((Driver) account,company);

                    }
                    else {
                        System.out.println("Invalid Login");
                    }
                    break;
                case 2:
                    System.out.println("Admin Username:");
                    String adminUsername = scanner.next();
                    System.out.println("Admin Password:");
                    String adminPassword = scanner.next();
                    Admin admin = this.loginAdmin(adminUsername,adminPassword,company.getAdmins());
                    if (admin!=null){
                        AdminService adminService = new AdminService();
                        adminService.displayMenu(admin, company);
                    }
                    break;
                case 3:
                    signUpUser(company);
                    break;
                case 4:
                    signUpDriver(company);
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Choose a valid option");
            }
        }

    }

    // ar ajuta un inline function ca sa nu citesc datele contului
    // si in user si in driver

    private void signUpUser(Company company) {
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Email:");
        String email = scanner.next();
        System.out.println("Password");
        String password = scanner.next();
        System.out.println("Coordinate X:");
        int coordinateX = scanner.nextInt();
        System.out.println("Coordinate Y:");
        int coordinateY = scanner.nextInt();
        User user = new User(username,email,new Coordinate(coordinateX,coordinateY),password);
        company.getCostumers().add(user);
        company.getUsers().add(user);
    }

    private void signUpDriver(Company company ) {
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Email:");
        String email = scanner.next();
        System.out.println("Password");
        String password = scanner.next();
        System.out.println("Coordinate X:");
        int coordinateX = scanner.nextInt();
        System.out.println("Coordinate Y:");
        int coordinateY = scanner.nextInt();
        Driver driver = new Driver(username,email,new Coordinate(coordinateX,coordinateY),password);
        company.getCostumers().add(driver);
        company.getDrivers().add(driver);
    }

    private Account login(String username,String password, ArrayList<Account> accounts){
        for (Account account : accounts){
            if (account.getUsername().equals(username) && account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }
    private Admin loginAdmin(String username,String password, ArrayList<Admin> admins){
        for (Admin admin : admins){
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                return admin;
            }
        }
        return null;
    }



}
