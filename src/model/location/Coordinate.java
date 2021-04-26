package model.location;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinate {
    private int x;
    private int y;

    @Override
    public String toString() {
        return x + "-" + y;
    }


}
