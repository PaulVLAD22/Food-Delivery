package model.order;

import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Product;
import java.util.Map;

public class Order {
    private static int orders_id=0;
    private int id;
    private User user;
    private Driver driver;
    private Local local;
    private Map<Product,Integer> productsQuantity;

    public Order(User user, Driver driver, Local local, Map<Product, Integer> products_quantity) {
        orders_id+=1;
        this.id = orders_id;
        this.user = user;
        this.driver = driver;
        this.local = local;
        this.productsQuantity = products_quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", driver=" + driver +
                ", local=" + local +
                ", products_quantity=" + productsQuantity +
                '}';
    }

    public Map<Product, Integer> getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(Map<Product, Integer> productsQuantity) {
        this.productsQuantity = productsQuantity;
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
