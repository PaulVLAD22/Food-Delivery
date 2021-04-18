package service;

import model.account.User;
import model.local.Product;
import model.location.Coordinate;
import model.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {
    private BasicService basicService = new BasicService();
    public double calculateOrder(Order order){
        double sum=0;
        for (Map.Entry<Product,Integer> entry : order.getProductsQuantity().entrySet() ){
            sum+= entry.getKey().getPrice()*entry.getValue();
        }
        return sum;
    }

}
