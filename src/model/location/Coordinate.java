package model.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return x+"-"+y;
    }


}
