package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Product;
import model.order.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvWriter<T> {
    private Path DIRECTORY;
    private Path PATH;

    public void write(T object) {
        if (!Files.exists(DIRECTORY)) {
            try {
                Files.createDirectories(DIRECTORY);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(PATH)) {
            try {
                Files.createFile(PATH);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(PATH,
                    StandardOpenOption.APPEND);
            String objectName = PATH.toString().split(Pattern.quote(File.separator))[PATH.toString().split(Pattern.quote((File.separator))).length - 1].split("\\.")[0];
            String message = switch (objectName) {
                case "users" -> writeUser((User) object);
                case "drivers" -> writeDriver((Driver) object);
                case "locals" -> writeLocal((Local) object);
                case "orders" -> writeOrder((Order) object);
                default -> "";
            };
            writer.write('\n' + message);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String writeLocal(Local local) {
        int id = local.getId();
        String localName = local.getName();
        String country = local.getLocation().getAddress().getCountry();
        String city = local.getLocation().getAddress().getCity();
        String street = local.getLocation().getAddress().getStreet();
        int coordinateX = local.getLocation().getCoordinate().getX();
        int coordinateY = local.getLocation().getCoordinate().getY();
        String output = id+","+localName + "," + "null" + "," + country + ":" + city + ":" + street + "," + coordinateX + ":" + coordinateY;
        return output;
    }

    private String writeOrder(Order order) {
        int id = order.getId();
        int userID = order.getUser().getId();
        int driverID = order.getDriver().getId();
        int localID = order.getLocal().getId();
        String productsQuantity ="";
        for (Product product : order.getProductsQuantity().keySet()){
            productsQuantity+=product.getId()+":"+order.getProductsQuantity().get(product)+";";
        }
        String output = id + "," + userID + "," + driverID +"," + localID +","+productsQuantity;
        return output;
    }

    private String writeDriver(Driver driver) {
        int id = driver.getId();
        String username = driver.getUsername();
        String email = driver.getEmail();
        String password = driver.getPassword();
        int coordinateX = driver.getCoordinate().getX();
        int coordinateY = driver.getCoordinate().getY();
        String output = id + "," + username + "," + email + "," + password + "," + coordinateX + ":" + coordinateY;
        return output;
    }

    private String writeUser(User user) {
        int id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        int coordinateX = user.getCoordinate().getX();
        int coordinateY = user.getCoordinate().getY();
        String output = id + "," + username + "," + email + "," + password + "," + coordinateX + ":" + coordinateY;
        return output;
    }

}
