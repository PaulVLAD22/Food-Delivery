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
    private int id;
    private User user;
    private Driver driver;
    private Local local;
    private Map<Product, Integer> productsQuantity;
}
