package service;

import model.Company;
import model.account.Admin;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Product;
import model.order.Order;

import java.util.Scanner;

public class AdminService extends BasicService {

    private void changeLocalMenu() {

    }

    private void changeLocalName(Local local) {
        local.setName(scanner.next());
    }

    private void changeLocalDetails(Admin admin, Company company) {
        System.out.println("Local id:");
        int localId = scanner.nextInt();
        System.out.println("Local name:");
        String localName = scanner.next();
        Local chosenLocal = null;
        for (Local local : company.getLocals()) {
            if (local.getId() == localId && local.getName().equals(localName)) {
                chosenLocal = local;
            }
        }
        if (chosenLocal!=null) {
            while (true) {
                System.out.println("1.Change Local Name");
                System.out.println("2.Add product");
                System.out.println("3.Remove product");
                System.out.println("4.Delete Local");
                System.out.println("10. Cancel");
                choice = scanner.nextInt();
                String productName;
                switch (choice) {
                    case 1:
                        changeLocalName(chosenLocal);
                        break;
                    case 2:
                        System.out.println("Product Name:");
                        productName = scanner.next();
                        System.out.println("Product Price:");
                        double productPrice = scanner.nextDouble();
                        chosenLocal.getMenu().getProducts().add(new Product(productName, productPrice));
                        break;
                    case 3:
                        System.out.println("Enter product name:");
                        productName = scanner.next();
                        Product productToDelete = null;
                        for (Product product : chosenLocal.getMenu().getProducts()) {
                            if (product.getName().equals(productName)) {
                                productToDelete = product;
                            }
                        }
                        chosenLocal.getMenu().getProducts().remove(productToDelete);
                        break;
                    case 4:
                        company.getLocals().remove(chosenLocal);
                        break;
                    case 10:
                        displayMenu(admin, company);
                        break;
                    default:
                        System.out.println("Choose a valid option");
                }
            }
        }
        else{
            System.out.println("Invalid Details");
            displayMenu(admin,company);
        }

}

    public void displayMenu(Admin admin, Company company) {
        while (true) {
            System.out.println("1.List of Drivers.");
            System.out.println("2.List of Users.");
            System.out.println("3.List of Locals");
            System.out.println("4.List of Orders");
            System.out.println("5.Change Local details.");
            System.out.println("10.Log Out");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (Driver driver : company.getDrivers()) {
                        System.out.println(driver);
                    }
                    break;
                case 2:
                    for (User user : company.getUsers()) {
                        System.out.println(user);
                    }
                    break;
                case 3:
                    for (Local local : company.getLocals()) {
                        System.out.println(local);
                    }
                    break;
                case 4:
                    for (Order order: company.getOrders()){
                        System.out.println(order);
                    }
                    break;
                case 5:
                    this.changeLocalDetails(admin, company);
                    break;
                case 10:
                    displayMainMenu(company);
                    break;
                default:
                    System.out.println("Choose a valid option");
            }
        }

    }
}
