package service;

import exception.UnrecognizedFileException;
import model.Company;
import model.account.Admin;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Menu;
import model.local.Product;
import model.location.Address;
import model.location.Coordinate;
import model.location.Location;
import model.order.Order;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;


public class CsvReader<T> {

    public List<T> read(Path PATH, Company company) {
        List<T> objects = new ArrayList<>();
        String objectName = PATH.toString().split(Pattern.quote(File.separator))[PATH.toString().split(Pattern.quote((File.separator))).length - 1].split("\\.")[0];
        try {
            BufferedReader reader = Files.newBufferedReader(PATH);
            objects = parseObjects(reader, objectName, company);
        } catch (NoSuchFileException e) {
            System.out.println("The file with the name " + PATH + " doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
        return objects;
    }

    private List<User> parseUsers(BufferedReader reader) throws IOException {
        List<User> users = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] information = line.split(",");
            int id = Integer.parseInt(information[0]);
            String username = information[1];
            String email = information[2];
            String password = information[3];
            String[] coordinates = information[4].split(":");
            int coordinateX = Integer.parseInt(coordinates[0]);
            int coordinateY = Integer.parseInt(coordinates[1]);
            users.add(new User(id,username, email, new Coordinate(coordinateX, coordinateY), password));
        }
        return users;
    }

    private List<Driver> parseDrivers(BufferedReader reader) throws IOException {
        List<Driver> drivers = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] information = line.split(",");
            int id = Integer.parseInt(information[0]);
            String username = information[1];
            String email = information[2];
            String password = information[3];
            String[] coordinates = information[4].split(":");
            int coordinateX = Integer.parseInt(coordinates[0]);
            int coordinateY = Integer.parseInt(coordinates[1]);

            drivers.add(new Driver(id,username, email, new Coordinate(coordinateX, coordinateY), password));
        }

        return drivers;
    }

    private List<Local> parseLocals(BufferedReader reader) throws IOException {
        List<Local> locals = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] information = line.split(",");
            int id = Integer.parseInt(information[0]);
            String name = information[1];

            Menu menu = new Menu();
            Location location = new Location();
            if (!information[2].equals("null")) {
                String[] productStrings = information[2].split(";");
                for (String productString : productStrings) {
                    String[] productNameQuantity = productString.split(":");
                    menu.getProducts().add(new Product(productNameQuantity[0], Integer.parseInt(productNameQuantity[1])));
                }
            }
            String[] addressString = information[3].split(":");
            String[] coordinates = information[4].split(":");
            location.setAddress(new Address(addressString[0], addressString[1], addressString[2]));
            location.setCoordinate(new Coordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));

            locals.add(new Local(id,name, menu, location));
        }
        return locals;
    }

    private List<Admin> parseAdmins(BufferedReader reader) throws IOException {
        List<Admin> admins = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] information = line.split(",");
            int id = Integer.parseInt(information[0]);
            String username = information[1];
            String email = information[2];
            String password = information[3];

            admins.add(new Admin(id,username, email, password));
        }
        return admins;
    }

    private List<Order> parseOrders(BufferedReader reader, Company company) throws IOException {
        List<Order> orders = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] information = line.split(",");
            int order_id = Integer.parseInt(information[0]);
            int user_id = Integer.parseInt(information[1]);
            int driver_id = Integer.parseInt(information[2]);
            int local_id = Integer.parseInt(information[3]);

            User chosenUser = company.getUsers().stream().filter(user -> user.getId()==user_id).findFirst().orElse(null);
            Driver chosenDriver = company.getDrivers().stream().filter(driver -> driver.getId()==driver_id).findFirst().orElse(null);
            Local chosenLocal = company.getLocals().stream().filter(local->local.getId()==local_id).findFirst().orElse(null);

            Map<Product,Integer> order_products = new HashMap<>();
            String[] entry = information[4].split(";");
            for (String s : entry) {
                String[] info = s.split(":");
                int productId = parseInt(info[0]);
                int productQuantity = parseInt(info[1]);
                if (chosenLocal != null) {
                    order_products.put(chosenLocal.getMenu().getProducts().get(productId), productQuantity);
                }
            }

            orders.add(new Order(order_id,chosenUser,chosenDriver,chosenLocal,order_products));
        }
        return orders;

    }

    public List<T> parseObjects(BufferedReader reader, String objectName, Company company) throws IOException, UnrecognizedFileException {
        List<T> objects = new ArrayList<>();
        try {
            objects = switch (objectName) {
                case "users" -> (List<T>) parseUsers(reader);
                case "drivers" -> (List<T>) parseDrivers(reader);
                case "locals" -> (List<T>) parseLocals(reader);
                case "admins" -> (List<T>) parseAdmins(reader);
                case "orders" -> (List<T>) parseOrders(reader, company);
                default -> throw new UnrecognizedFileException();
            };
        } catch (ClassCastException e) {
            System.out.println("Wrong file association");
        }

        return objects;
    }
}