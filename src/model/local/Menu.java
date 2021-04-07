package model.local;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Menu {
    private static int menuID =0;
    private int id;
    private List<Product> products;

    public Menu(List<Product> products) {
        menuID += 1;
        this.id = menuID;
        this.products = products;
    }
}
