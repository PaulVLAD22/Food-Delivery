package service;

import exception.UsernameOrEmailAlreadyTaken;
import model.Company;
import model.audit.Action;
import model.location.Coordinate;
import model.account.Account;
import model.account.Admin;
import model.account.Driver;
import model.account.User;

import java.nio.file.Path;
import java.util.*;

public class BasicService {
    private static BasicService INSTANCE;

    private BasicService() {

    }

    public static BasicService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BasicService();
        }
        return INSTANCE;
    }

    private final Scanner scanner = new Scanner(System.in);
    private int choice;

    private final Path AUDIT_DIRECTORY = Path.of("resources/audit");
    private final Path AUDIT_PATH = Path.of(AUDIT_DIRECTORY + "/audit.csv");

    CsvWriter<Action> actionCsvWriter = new CsvWriter<>(AUDIT_DIRECTORY, AUDIT_PATH);

    public void displayMainMenu(Company company) {
        while (true) {
            System.out.println("1. Login as User");
            System.out.println("2. Login as Driver");
            System.out.println("3. Login as Administrator");
            System.out.println("4. Sign up as User");
            System.out.println("5. Sign up as Driver");
            System.out.println("10. Exit");
            choice = readIntChoice();

            switch (choice) {
                case 1:
                    System.out.println("Username:");
                    String username = scanner.next();
                    System.out.println("Password:");
                    String password = scanner.next();
                    Account account = this.login(username, password, company.getCostumers());

                    if (account instanceof User) {
                        UserService userService = UserService.getInstance();
                        userService.displayMenu((User) account, company);
                    } else {
                        System.out.println("Invalid Login");
                    }
                    break;
                case 3:
                    System.out.println("Admin Username:");
                    String adminUsername = scanner.next();
                    System.out.println("Admin Password:");
                    String adminPassword = scanner.next();
                    Admin admin = this.loginAdmin(adminUsername, adminPassword, company.getAdmins());
                    if (admin != null) {
                        AdminService adminService = AdminService.getInstance();
                        adminService.displayMenu(admin, company);
                    }
                    break;
                case 4:
                    try {
                        signUpUser(company);
                    } catch (UsernameOrEmailAlreadyTaken e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        signUpDriver(company);
                    } catch (UsernameOrEmailAlreadyTaken e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Username:");
                    String usernameDriver = scanner.next();
                    System.out.println("Password:");
                    String passwordDriver = scanner.next();
                    Account accountDriver = this.login(usernameDriver, passwordDriver, company.getCostumers());

                    if (accountDriver instanceof Driver) {
                        DriverService driverService = DriverService.getInstance();
                        driverService.displayMenu((Driver) accountDriver, company);
                    } else {
                        System.out.println("Invalid Login");
                    }
                    break;

                case 10:
                    System.exit(0);
                default:
                    System.out.println("Choose a valid option");
            }
        }

    }

    private void signUpUser(Company company) throws UsernameOrEmailAlreadyTaken {
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Email:");
        String email = scanner.next();

        User newUser = User.builder().username(username).email(email).build();

        if (company.getUsers().stream().anyMatch(driver -> driver.equals(newUser)))
            throw new UsernameOrEmailAlreadyTaken();

        System.out.println("Password");
        String password = scanner.next();
        System.out.println("Coordinate X:");
        int coordinateX = readIntChoice();
        System.out.println("Coordinate Y:");
        int coordinateY = readIntChoice();
        int newID = company.getUsers().stream().max(Comparator.comparingInt(Account::getId)).get().getId() + 1;
        User user = new User(newID, username, email, new Coordinate(coordinateX, coordinateY), password);
        company.getCostumers().add(user);
        company.getUsers().add(user);


        actionCsvWriter.write(new Action("User Sign up"));
        UserService userService = UserService.getInstance();
        userService.write(user);
    }

    private void signUpDriver(Company company) throws UsernameOrEmailAlreadyTaken {

        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Email:");
        String email = scanner.next();

        Driver newDriver = Driver.builder().
                username(username).
                email(email).build();

        if (company.getDrivers().stream().anyMatch(driver -> driver.equals(newDriver)))
            throw new UsernameOrEmailAlreadyTaken();


        System.out.println("Password");
        String password = scanner.next();
        System.out.println("Coordinate X:");
        int coordinateX = readIntChoice();
        System.out.println("Coordinate Y:");
        int coordinateY = readIntChoice();

        Driver driver = new Driver((company.getDrivers().stream().max(Comparator.comparingInt(Account::getId)).get().getId() + 1), username, email, new Coordinate(coordinateX, coordinateY), password);
        company.getCostumers().add(driver);
        company.getDrivers().add(driver);

        actionCsvWriter.write(new Action("Driver Sign up"));
        DriverService driverService = DriverService.getInstance();
        driverService.write(driver);
    }

    private Account login(String username, String password, List<Account> accounts) {
        actionCsvWriter.write(new Action("Log in attempt"));
        return accounts.stream().filter(account -> account.getUsername().equals(username)
                && account.getPassword().equals(password)).findFirst().orElse(null);
    }

    private Admin loginAdmin(String username, String password, List<Admin> admins) {
        actionCsvWriter.write(new Action("Admin log in attempt"));
        return admins.stream().filter(account -> account.getUsername().equals(username)
                && account.getPassword().equals(password)).findFirst().orElse(null);

    }

    int readIntChoice() {
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again");
                scanner.next();

            } catch (Exception e) {
                System.out.println("Unexpected error");
                scanner.next();
                break;
            }
        }
        return 0;
    }

}
