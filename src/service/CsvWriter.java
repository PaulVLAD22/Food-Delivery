package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.account.Driver;
import model.account.User;
import model.local.Local;

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
            String message = "";
            if (objectName.equals("users")) {
                message = writeUser((User) object);
            } else if (objectName.equals("drivers")) {
                message = writeDriver((Driver) object);
            } else if (objectName.equals("locals")) {
                message = writeLocal((Local) object);
            }
            writer.write('\n' + message);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String writeLocal(Local local) {
        String localName = local.getName();
        String country = local.getLocation().getAddress().getCountry();
        String city = local.getLocation().getAddress().getCity();
        String street = local.getLocation().getAddress().getStreet();
        int coordinateX = local.getLocation().getCoordinate().getX();
        int coordinateY = local.getLocation().getCoordinate().getY();
        String output = localName + "," + "null" + "," + country + ":" + city + ":" + street + "," + coordinateX + ":" + coordinateY;
        return output;
    }

    private String writeDriver(Driver driver) {
        String username = driver.getUsername();
        String email = driver.getEmail();
        String password = driver.getPassword();
        int coordinateX = driver.getCoordinate().getX();
        int coordinateY = driver.getCoordinate().getY();
        String output = username + "," + email + "," + password + "," + coordinateX + ":" + coordinateY;
        return output;
    }

    private String writeUser(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        int coordinateX = user.getCoordinate().getX();
        int coordinateY = user.getCoordinate().getY();
        String output = username + "," + email + "," + password + "," + coordinateX + ":" + coordinateY;
        return output;
    }

}
