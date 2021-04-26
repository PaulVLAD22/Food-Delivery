package model.local;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Product {
    private static int productID = 0;
    private int id;
    private String name;
    private double price;

    public Product(String name, double price) {
        productID += 1;
        this.id = productID;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                name.equals(product.name);
    }
}
