package model.local;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.location.Location;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Local {
    private int id;
    private String name;
    private Menu menu;
    private Location location;

}
