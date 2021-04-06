package service;

import exception.NoDriverInRangeException;
import model.Company;
import model.location.Coordinate;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Product;
import model.order.Order;

import java.util.*;

import static java.lang.Integer.*;

public class UserService {
    private BasicService basicService = new BasicService();
    private int choice;
    private Scanner scanner = new Scanner(System.in);

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
                    for (Local local : locals) {
                        System.out.println("Local " + local.getName());
                        for (Product product : local.getMenu().getProducts()) {
                            System.out.println(product);
                        }
                    }
                    break;
                case 2:
                    ArrayList<Local> locals_arr = new ArrayList<>(locals);
                    System.out.println("Choose the local:");
                    for (Local local : locals_arr) {
                        System.out.println(locals_arr.indexOf(local)+1);
                        System.out.println(local);
                    }
                    choice = basicService.readIntChoice();
                    choice-=1;

                    Local chosenLocal = locals_arr.get(choice);
                    List<Product> localProducts = chosenLocal.getMenu().getProducts();
                    for (Product product : localProducts) {
                        System.out.println(localProducts.indexOf(product)+1);
                        System.out.println(product);
                    }
                    Map<Product, Integer> order_products = new HashMap<Product, Integer>();
                    System.out.println("Enter number of products you want to buy \n 1:2 means 2 pieces of product number 1");
                    String input = scanner.next();
                    String[] entry = input.split(",");
                    for (String s : entry) {
                        //System.out.println(s);
                        String[] info = s.split(":");
                        int productNumber = parseInt(info[0]);
                        int productQuantity = parseInt(info[1]);
                        order_products.put(localProducts.get(productNumber), productQuantity);
                    }
                    try {
                        Driver closestDriver = closestDriver(chosenLocal, company);
                        Order order = new Order(user, closestDriver, chosenLocal, order_products);
                        company.getOrders().add(order);
                        closestDriver.setCurrentOrder(order);
                        double totalDistance = calculateDistance(chosenLocal.getLocation().getCoordinate(), closestDriver.getCoordinate()) +
                                calculateDistance(chosenLocal.getLocation().getCoordinate(), user.getCoordinate());
                        //unitati de masura pe 10;
                        System.out.println("The order will arrive in :" + totalDistance / 10);
                        System.out.println("The driver will travel with speed of a 10 units per minute");
                    }
                    catch (NoDriverInRangeException e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    basicService.displayMainMenu(company);
                    break;
                case 4:
                    System.out.println("Are you sure? (1-yes)");
                    choice = basicService.readIntChoice();
                    if (choice==1) {
                        company.getUsers().remove(user);
                    }
                    else{
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
        double minimum_distance = Double.POSITIVE_INFINITY;
        int driver_index = -1;
        for (Driver driver : drivers) {
            double currentDistance = calculateDistance(driver.getCoordinate(), chosenLocal.getLocation().getCoordinate());
            if (currentDistance < 1000 && currentDistance < minimum_distance) {
                minimum_distance = currentDistance;
                driver_index=drivers.indexOf(driver);
            }
        }
        if (driver_index==-1){
            throw new NoDriverInRangeException();
        }
        return drivers.get(driver_index);
    }

}
