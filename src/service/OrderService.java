package service;

import model.local.Product;
import model.order.Order;

import java.util.Map;

public class OrderService {
    public double calculateOrder(Order order){
        double sum=0;
        for (Map.Entry<Product,Integer> entry : order.getProductsQuantity().entrySet() ){
            sum+= entry.getKey().getPrice()*entry.getValue();
        }
        return sum;
    }
}
