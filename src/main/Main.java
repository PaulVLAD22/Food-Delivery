package main;

import models.Coordinates;
import models.Local.Local;
import models.Local.Menu;
import models.Local.Product;
import models.User.Driver;
import models.User.User;
import services.UserServices;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Local> locals = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Driver> drivers = new ArrayList<>();

        Product product1 = new Product("Product1", 100);

        users.add(new User("vlad1","paulvlad34@gmail.com",
                new Coordinates(10,20),"parola"));
        ArrayList<Product> products1 =new ArrayList<>();
        products1.add(product1);

        locals.add(new Local("Local 1",new Menu(products1),
                new Coordinates(100,200)));

        UserServices.displayMenu(locals);
        UserServices.deleteAccount(users,"vlad1");
        System.out.println(users.size());


    }
}
