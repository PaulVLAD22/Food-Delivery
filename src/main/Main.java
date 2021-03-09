package main;

import model.Company;
import model.Coordinates;
import model.Local.Local;
import model.Local.Menu;
import model.Local.Product;
import model.Order.Order;
import model.User.Driver;
import model.User.User;
import service.CompanyServices;
import service.UserServices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserServices userServices = new UserServices();
        Company company = new Company();

        Product product1 = new Product("Product1", 100);

        CompanyServices.addPerson(company,new User("vlad1","paulvlad34@gmail.com",
                new Coordinates(10,20),"parola"));
        System.out.println(company.getUsers().get(0));
        ArrayList<Product> products1 =new ArrayList<>();
        products1.add(product1);

        company.getLocals().add(new Local("Local 1",new Menu(products1),
                new Coordinates(100,200)));

        userServices.displayMenu(company.getLocals());
        userServices.deleteAccount(company.getUsers(),"vlad1");
        System.out.println(company.getUsers().size());
    }
}
