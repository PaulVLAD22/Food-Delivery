package model.local;

import lombok.Getter;
import lombok.Setter;
import model.location.Location;

import java.util.Objects;

@Getter
@Setter
public class Local {
    private static int localID =0;
    private int id;
    private String name;
    private Menu menu;
    private Location location;

    public Local(){

    }

    public Local(String name, Menu menu, Location location) {
        localID +=1;
        this.id= localID;
        this.name = name;
        this.menu = menu;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Local local = (Local) o;
        return Objects.equals(name, local.name) &&
                Objects.equals(menu, local.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, menu);
    }

    @Override
    public String toString() {
        return "Local{" + " id = "+id+
                " name='" + name + '\'' +
                ", coordinate=" + location +
                '}'+"\n"+menu+"\n";
    }

}
