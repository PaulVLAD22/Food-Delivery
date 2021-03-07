package models.Order;

import models.User.Driver;
import models.Local.Local;
import models.Local.Product;
import models.User.User;

import java.util.ArrayList;

public class Order {
    static int orders_id=0;
    int id;
    User user;
    Driver driver;
    Local local;
    ArrayList<Product> products;

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