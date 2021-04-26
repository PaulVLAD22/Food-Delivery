package service;

import exception.NoDriverInRangeException;
import model.Company;
import model.audit.Action;
import model.location.Coordinate;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Product;
import model.order.Order;

import java.nio.file.Path;
import java.util.*;

import static java.lang.Integer.*;

public class UserService {
    private static final UserService INSTANCE = new UserService();

    private UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    private BasicService basicService = BasicService.getInstance();
    private int choice;
    private Scanner scanner = new Scanner(System.in);

    private final Path USERS_DIRECTORY = Path.of("resources/users");
    private final Path USERS_PATH = Path.of(USERS_DIRECTORY + "/users.csv");

    private CsvReader<User> userCsvReader = new CsvReader<>();
    private CsvWriter<User> userCsvWriter = new CsvWriter<>(USERS_DIRECTORY, USERS_PATH);


    public void displayMenu(User user, Company company) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.View Menu");
            System.out.println("2.Order");
            System.out.println("3.Log Out");
            System.out.println("4.Delete account");
            choice = scanner.nextInt();

            ArrayList<Product> all_products = new ArrayList<>();
            Set<Local> locals = company.getLocals();
            for (Local local : locals) {
                all_products.addAll((local.getMenu().getProducts()));
            }

            switch (choice) {
                case 1:
                    basicService.actionCsvWriter.write(new Action("Menu Viewed set"));
                    locals.forEach(System.out::println);
                    break;
                case 2:
                    ArrayList<Local> locals_arr = new ArrayList<>(locals);
                    System.out.println("Choose the local:");
                    for (Local local : locals_arr) {
                        if (local.getMenu().getProducts().size()!=0) {
                            System.out.println(locals_arr.indexOf(local) + 1);
                            System.out.println(local);
                        }
                    }
                    choice = basicService.readIntChoice();
                    choice -= 1;

                    Local chosenLocal = locals_arr.get(choice);
                    List<Product> localProducts = chosenLocal.getMenu().getProducts();
                    localProducts.stream().forEach(product -> {
                        System.out.println(localProducts.indexOf(product) + 1);
                        System.out.println(product);
                    });

                    Map<Product, Integer> order_products = new HashMap<Product, Integer>();
                    System.out.println("Enter number of products you want to buy \n 1:2 means 2 pieces of product number 1");
                    String input = scanner.next();
                    String[] entry = input.split(",");
                    for (String s : entry) {
                        String[] info = s.split(":");
                        int productNumber = parseInt(info[0])-1;
                        int productQuantity = parseInt(info[1]);
                        order_products.put(localProducts.get(productNumber), productQuantity);
                    }
                    try {
                        Driver closestDriver = closestDriver(chosenLocal, company);
                        int newID = (company.getOrders().stream().max(Comparator.comparingInt(Order::getId)).get().getId() + 1);
                        Order order = Order.builder().
                                id(newID).
                                user(user).
                                driver(closestDriver).
                                local(chosenLocal).
                                productsQuantity(order_products).build();

                        company.getOrders().add(order);
                        closestDriver.setCurrentOrder(order);
                        double totalDistance = calculateDistance(chosenLocal.getLocation().getCoordinate(), closestDriver.getCoordinate()) +
                                calculateDistance(chosenLocal.getLocation().getCoordinate(), user.getCoordinate());

                        System.out.println("The order will arrive in :" + totalDistance / 10);
                        System.out.println("The driver will travel with speed of a 10 units per minute");

                        OrderService.getInstance().write(order);
                        basicService.actionCsvWriter.write(new Action("Order set"));
                    } catch (NoDriverInRangeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    basicService.actionCsvWriter.write(new Action("Logout"));
                    basicService.displayMainMenu(company);
                    break;
                case 4:
                    basicService.actionCsvWriter.write(new Action("Account Deletion Attempt"));
                    System.out.println("Are you sure? (1-yes)");
                    choice = basicService.readIntChoice();
                    if (choice == 1) {
                        basicService.actionCsvWriter.write(new Action("Account Deletion"));
                        company.getUsers().remove(user);
                    } else {
                        System.out.println("Didn't delete account");
                    }

                default:
                    System.out.println("Choose a valid option");
            }
        }

    }

    private double calculateDistance(Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2) +
                Math.pow(c2.getY() - c1.getY(), 2));
    }

    private Driver closestDriver(Local chosenLocal, Company company) throws NoDriverInRangeException {
        List<Driver> drivers = company.getDrivers();
        double minimumDistance = Double.POSITIVE_INFINITY;
        int driverIndex = -1;

        for (Driver driver : drivers) {
            double currentDistance = calculateDistance(driver.getCoordinate(), chosenLocal.getLocation().getCoordinate());
            if (currentDistance < 1000 && currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                driverIndex = drivers.indexOf(driver);
            }
        }
        if (driverIndex == -1) {
            throw new NoDriverInRangeException();
        }
        return drivers.get(driverIndex);
    }

    public List<User> readUsers(Company company) {
        return userCsvReader.read(USERS_PATH, company);
    }

    public void write(User user) {
        userCsvWriter.write(user);
    }


}
