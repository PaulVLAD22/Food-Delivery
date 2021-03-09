package model.Order;

import model.User.Driver;
import model.Local.Local;
import model.Local.Product;
import model.User.User;

import java.util.ArrayList;

public class Order {
    private static int orders_id=0;
    private int id;
    private User user;
    private Driver driver;
    private Local local;
    private ArrayList<Product> products;

    public Order(User user, ArrayList<Product> products, Local local) {
        orders_id+=1;
        this.id=orders_id;
        this.user = user;
        this.products=products;
        this.local=local;
    }

    public static int getOrders_id() {
        return orders_id;
    }

    public static void setOrders_id(int orders_id) {
        Order.orders_id = orders_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
