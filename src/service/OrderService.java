package service;

import model.Company;
import model.local.Product;
import model.order.Order;

import java.nio.file.Path;
import java.util.Map;
import java.util.List;

public class OrderService {
    private BasicService basicService = BasicService.getInstance();
    private static OrderService INSTANCE;

    private OrderService() {

    }

    public static OrderService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderService();
        }
        return INSTANCE;
    }

    public final Path ORDERS_DIRECTORY = Path.of("resources/orders");
    public final Path ORDERS_PATH = Path.of(ORDERS_DIRECTORY + "/orders.csv");

    private CsvReader<Order> orderCsvReader = new CsvReader<>();
    public CsvWriter<Order> orderCsvWriter = new CsvWriter<>(ORDERS_DIRECTORY, ORDERS_PATH);

    public double calculateOrder(Order order) {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry : order.getProductsQuantity().entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public List<Order> read(Company company) {
        return orderCsvReader.read(ORDERS_PATH, company);
    }

    public void write(Order order) {
        orderCsvWriter.write(order);
    }

}
