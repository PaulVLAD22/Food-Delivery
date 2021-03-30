package main;

import model.Company;
import model.location.Address;
import model.location.Coordinate;
import model.account.Account;
import model.account.Admin;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Menu;
import model.local.Product;
import model.location.Location;
import service.BasicService;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        BasicService basicService = new BasicService();
        //singleton
        Company company = Company.getInstance();

        Account a= new User("u","",new Coordinate(20,20),"p");
        Account b = new Driver("a","",new Coordinate(25,25),"p");
        Account c = new Driver("c","ema",new Coordinate(30,30),"p");

        ArrayList<Account> costumers = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();

        admins.add(new Admin("us","em","pa"));
        company.setAdmins(admins);

        products.add(new Product("product1",50));
        products.add(new Product("aroduct1",50));
        Menu menu = new Menu(products);

        Set<Local> locals = new HashSet<>();
        Location location1 = new Location(new Address(),new Coordinate(25,25));
        locals.add(new Local("name",menu,location1));
        company.setLocals(locals);

        costumers.add(a);
        costumers.add(b);
        costumers.add(c);
        company.setCostumers(costumers);

        basicService.displayMainMenu(company);



    // user service va avea ce poate face user uul
    // la fel pt restu

        // fa la inceput un meniu :

        // 1-> Login as User
            // 1-> View menu
            // 2-> Order by product id/ (poate name)
                // care atribuie cel mai apropiat driver fara comanda actuala
            // 3-> Log out

        // 2-> Login as Driver
            //rezolveOrder()
        // 3 -> Login as Local
            // change Menu
        // 4 -> Login as Administrator care poate sterge/adauga chestii
            // add Local, remove Local
            // add acciount/ remove
        // 5->(adauga user)
        // 6->(adauga driver)



//        Product product1 = new Product("Product1", 100);
//
//        companyService.addAccount(company, new User("vlad1", "paulvlad34@gmail.com",
//                new Coordinates(10, 20), "parola"));
//        System.out.println(company.getUsers().get(0));
//        ArrayList<Product> products1 = new ArrayList<>();
//        products1.add(product1);
//
//        company.getLocals().add(new Local("Local 1", new Menu(products1),
//                new Coordinates(100, 200)));
//
//        companyService.displayMenu(company, new User("vlad", "vlad@mail.com",
//                new Coordinates(25, 25), "parola"));
//        System.out.println(company.getUsers().size());
//        User a = new User("a", "a", new Coordinates(25, 25), "a");
//        Driver b = new Driver("a", "a", new Coordinates(25, 25), "a");
//        Local local = Local.builder().name("AA").build();
//        System.out.println();
    }
}
