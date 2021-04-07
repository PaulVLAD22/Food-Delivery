package model.local;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.location.Location;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Local {
    private static int localID =0;
    private int id;
    private String name;
    private Menu menu;
    private Location location;

    public Local(String name, Menu menu, Location location) {
        localID +=1;
        this.id= localID;
        this.name = name;
        this.menu = menu;
        this.location = location;
    }

}
