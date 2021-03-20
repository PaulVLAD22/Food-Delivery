package model.local;

import lombok.Builder;

import java.util.Objects;

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

    public static int getProducts_id() {
        return products_id;
    }

    public static void setProducts_id(int products_id) {
        Product.products_id = products_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
