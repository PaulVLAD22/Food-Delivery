package main;

import model.Company;
import model.Coordinates;
import model.local.Local;
import model.local.Menu;
import model.local.Product;
import model.user.User;
import service.CompanyService;
import service.UserService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CompanyService companyService = new CompanyService();
        Company company = new Company();

        Product product1 = new Product("Product1", 100);

        companyService.addAccount(company,new User("vlad1","paulvlad34@gmail.com",
                new Coordinates(10,20),"parola"));
        System.out.println(company.getUsers().get(0));
        ArrayList<Product> products1 =new ArrayList<>();
        products1.add(product1);

        company.getLocals().add(new Local("Local 1",new Menu(products1),
                new Coordinates(100,200)));

        companyService.displayMenu(company,new User("vlad","vlad@mail.com",
                new Coordinates(25,25),"parola"));
        System.out.println(company.getUsers().size());
    }
}
