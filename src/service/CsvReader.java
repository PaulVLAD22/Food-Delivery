package service;

import exception.UnrecognizedFileException;
import model.account.Admin;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Menu;
import model.local.Product;
import model.location.Address;
import model.location.Coordinate;
import model.location.Location;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;


public class CsvReader<T> {

    public List<T> read(Path PATH) {
        List<T> objects = new ArrayList<T>();
        String objectName = PATH.toString().split(Pattern.quote(File.separator))[PATH.toString().split(Pattern.quote((File.separator))).length - 1].split("\\.")[0];
        try {
            BufferedReader reader = Files.newBufferedReader(PATH);
            objects = parseObjects(reader, objectName);
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
            String username = information[0];
            String email = information[1];
            String password = information[2];
            String[] coordinates = information[3].split(":");
            int coordinateX = Integer.parseInt(coordinates[0]);
            int coordinateY = Integer.parseInt(coordinates[1]);
            users.add(new User(username, email, new Coordinate(coordinateX, coordinateY), password));
        }
        return users;
    }

    private List<Driver> parseDrivers(BufferedReader reader) throws IOException {
        List<Driver> drivers = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] information = line.split(",");
            String username = information[0];
            String email = information[1];
            String password = information[2];
            String[] coordinates = information[3].split(":");
            int coordinateX = Integer.parseInt(coordinates[0]);
            int coordinateY = Integer.parseInt(coordinates[1]);

            drivers.add(new Driver(username, email, new Coordinate(coordinateX, coordinateY), password));
        }

        return drivers;
    }

    private List<Local> parseLocals(BufferedReader reader) throws IOException {
        List<Local> locals = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] information = line.split(",");
            String name = information[0];

            Menu menu = new Menu();
            Location location = new Location();
            if (!information[1].equals("null")) {
                String[] productStrings = information[1].split(";");
                for (String productString : productStrings) {
                    String[] productNameQuantity = productString.split(":");
                    menu.getProducts().add(new Product(productNameQuantity[0], Integer.parseInt(productNameQuantity[1])));
                }
            }
            String[] addressString = information[2].split(":");
            String[] coordinates = information[3].split(":");
            location.setAddress(new Address(addressString[0], addressString[1], addressString[2]));
            location.setCoordinate(new Coordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));

            locals.add(new Local(name, menu, location));
        }
        return locals;
    }

    private List<Admin> parseAdmins(BufferedReader reader) throws IOException {
        List<Admin> admins = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] information = line.split(",");
            String username = information[0];
            String email = information[1];
            String password = information[2];

            admins.add(new Admin(username, email, password));
        }
        return admins;
    }

    public List<T> parseObjects(BufferedReader reader, String objectName) throws IOException, UnrecognizedFileException {
        List<T> objects = new ArrayList<>();
        try {
            if (objectName.equals("users")) {
                objects = (List<T>) parseUsers(reader);
            } else if (objectName.equals("drivers")) {
                objects = (List<T>) parseDrivers(reader);
            } else if (objectName.equals("locals")) {
                objects = (List<T>) parseLocals(reader);
            } else if (objectName.equals("admins")) {
                objects = (List<T>) parseAdmins(reader);
            } else {
                throw new UnrecognizedFileException();
            }
        } catch (ClassCastException e) {
            System.out.println("Wrong file association");
        }

        return objects;
    }
}