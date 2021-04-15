package service;

import exception.UsernameOrEmailAlreadyTaken;
import model.Company;
import model.local.Local;
import model.local.Menu;
import model.local.Product;
import model.location.Address;
import model.location.Coordinate;
import model.account.Account;
import model.account.Admin;
import model.account.Driver;
import model.account.User;
import model.location.Location;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.*;

public class BasicService {
    private Scanner scanner = new Scanner(System.in);
    private int choice;
    private final String AUDIT_DIRECTORY = "resources/audit";
    private final String AUDIT_PATH = AUDIT_DIRECTORY + "/audit.csv";

    public final String DRIVERS_DIRECTORY = "resources/drivers";
    public final String DRIVERS_PATH = DRIVERS_DIRECTORY+"/drivers.csv";

    public final String USERS_DIRECTORY = "resources/users";
    public final String USERS_PATH = USERS_DIRECTORY + "/users.csv";

    public final String LOCALS_DIRECTORY = "resources/locals";
    public final String LOCALS_PATH = LOCALS_DIRECTORY + "/locals.csv";

    public final WriteService writeService = WriteService.getInstance();
    public final ReadService readService = ReadService.getInstance();

    public void displayMainMenu(Company company){
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
                    Account account = this.login(username,password,company.getCostumers());

                    if (account instanceof User) {
                        UserService userService = new UserService();
                        userService.displayMenu((User) account, company);
                    }
                    else {
                        System.out.println("Invalid Login");
                    }
                    break;
                case 3:
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
                case 4:
                    try {
                        signUpUser(company);
                    }catch (UsernameOrEmailAlreadyTaken e){
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        signUpDriver(company);
                    }catch (UsernameOrEmailAlreadyTaken e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Username:");
                    String usernameDriver = scanner.next();
                    System.out.println("Password:");
                    String passwordDriver = scanner.next();
                    Account accountDriver = this.login(usernameDriver,passwordDriver,company.getCostumers());

                    if (accountDriver instanceof Driver){
                    DriverService driverService = new DriverService();
                    driverService.displayMenu((Driver) accountDriver,company);
                    }
                    else {
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

        for (User user : company.getUsers()){

            if (user.getEmail().equals(email) || user.getUsername().equals(username)){
                throw new UsernameOrEmailAlreadyTaken();
            }
        }

        System.out.println("Password");
        String password = scanner.next();
        System.out.println("Coordinate X:");
        int coordinateX = readIntChoice();
        System.out.println("Coordinate Y:");
        int coordinateY = readIntChoice();
        User user = new User(username,email,new Coordinate(coordinateX,coordinateY),password);
        company.getCostumers().add(user);
        company.getUsers().add(user);


        this.auditMessage("User Signup");
        this.writeNewUser(user);
    }

    private void signUpDriver(Company company ) throws UsernameOrEmailAlreadyTaken {

        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Email:");
        String email = scanner.next();

        for (Driver driver : company.getDrivers()){
            if (driver.getEmail().equals(email) || driver.getUsername().equals(username)){
                throw new UsernameOrEmailAlreadyTaken();
            }
        }

        System.out.println("Password");
        String password = scanner.next();
        System.out.println("Coordinate X:");
        int coordinateX = readIntChoice();
        System.out.println("Coordinate Y:");
        int coordinateY = readIntChoice();
        Driver driver = new Driver(username,email,new Coordinate(coordinateX,coordinateY),password);
        company.getCostumers().add(driver);
        company.getDrivers().add(driver);

        this.auditMessage("Driver Signup");
        this.writeNewDriver(driver);
    }

    private Account login(String username,String password, List<Account> accounts){
        this.auditMessage("Login Attempt");
        for (Account account : accounts){
            if (account.getUsername().equals(username) && account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }
    private Admin loginAdmin(String username,String password, List<Admin> admins){
        this.auditMessage("Admin Login Attempt");
        for (Admin admin : admins){
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                return admin;
            }
        }
        return null;

    }
    protected int readIntChoice(){
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again");
                scanner.next();

            }
            catch(Exception e){
                System.out.println("Unexpected error");
                scanner.next();
                break;
            }
        }
        return 0;
    }
    public Set<Local> readLocals(){
        Set<Local> locals = new HashSet<>();
        String filename = this.LOCALS_PATH;
        List<String> fileOutput = this.readService.read(filename);

        for (String line : fileOutput) {
            String [] information = line.split(",");
            String name = information[0];
            String [] productStrings= information[1].split(";");
            Menu menu = new Menu();
            Location location = new Location();
            for (String productString : productStrings){
                String [] productNameQuantity = productString.split(":");
                System.out.println(productString);
                menu.getProducts().add(new Product(productNameQuantity[0],Integer.parseInt(productNameQuantity[1])));
            }
            String [] addressString = information[2].split(":");
            String [] coordinates = information[3].split(":");
            location.setAddress(new Address(addressString[0],addressString[1],addressString[2]));
            location.setCoordinate(new Coordinate(Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1])));

            locals.add(new Local(name,menu,location));
        }
        return locals;
    }

    protected void writeNewUser(User user){
        String username=user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        int coordinateX = user.getCoordinate().getX();
        int coordinateY = user.getCoordinate().getY();
        String output = username+","+email+","+password+","+coordinateX+":"+coordinateY;
        writeService.writeToFile(USERS_DIRECTORY,USERS_PATH,
                output);
    }
    protected void writeNewDriver(Driver driver){
        String username=driver.getUsername();
        String email =driver.getEmail();
        String password = driver.getPassword();
        int coordinateX = driver.getCoordinate().getX();
        int coordinateY = driver.getCoordinate().getY();
        String output = username+","+email+","+password+","+coordinateX+":"+coordinateY;
        writeService.writeToFile(DRIVERS_DIRECTORY,DRIVERS_PATH,
                output);
    }

    public void auditMessage(String message) {
        writeService.writeToFile(AUDIT_DIRECTORY,AUDIT_PATH,
                "action: " +message+",time: "+ new Timestamp(System.currentTimeMillis()));
    }

}
