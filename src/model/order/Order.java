package model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Product;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
