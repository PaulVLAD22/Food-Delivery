package model.local;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
public class Product {
    private static int products_id=0;
    private int id;
    private String name;
    private double price;

    public Product(String name, double price) {
        products_id+=1;
        this.id = products_id;
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

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString(){
        return ("Product ID: " + this.id + " - " +this.name+" - "+this.price+"$");
    }
}
