package model.local;

import lombok.Builder;

import java.util.ArrayList;
import java.util.Objects;

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

    public static int getMenus_id() {
        return menus_id;
    }

    public static void setMenus_id(int menus_id) {
        Menu.menus_id = menus_id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
