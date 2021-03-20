package model.local;

import model.Coordinate;

import java.util.Objects;

public class Local {
    private static int locals_id;
    private int id;
    private String name;
    private Menu menu;
    private Coordinate coordinate;

    public Local(){

    }

    public Local(String name, Menu menu, Coordinate coordinate) {
        locals_id+=1;
        this.id=locals_id;
        this.name = name;
        this.menu = menu;
        this.coordinate = coordinate;
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
        return "Local{" +
                "name='" + name + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }

    public static int getLocals_id() {
        return locals_id;
    }

    public static void setLocals_id(int locals_id) {
        Local.locals_id = locals_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Coordinate getCoordinates() {
        return coordinate;
    }

    public void setCoordinates(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
