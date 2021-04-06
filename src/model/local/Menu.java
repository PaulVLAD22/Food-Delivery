package model.local;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
public class Menu {
    private static int menuID =0;
    private int id;
    private List<Product> products;

    public Menu() {
    }

    public Menu(List<Product> products) {
        menuID +=1;
        this.id= menuID;
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
