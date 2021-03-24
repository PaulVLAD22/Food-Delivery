package model.local;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;
@Getter
@Setter
public class Menu {
    private static int menus_id=0;
    private int id;
    private ArrayList<Product> products;

    public Menu(ArrayList<Product> products) {
        menus_id+=1;
        this.id=menus_id;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return products.equals(menu.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
