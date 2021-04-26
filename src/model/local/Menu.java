package model.local;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Menu {
    private static int menuID = 0;
    private int id;
    private List<Product> products;

    public Menu() {
        menuID += 1;
        this.id = menuID;
        products = new ArrayList<>();
    }

    public Menu(List<Product> products) {
        menuID += 1;
        this.id = menuID;
        this.products = products;
    }
}
