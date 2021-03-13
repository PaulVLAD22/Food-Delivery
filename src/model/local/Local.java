package model.local;

import model.Coordinates;

public class Local {
    private static int locals_id;
    private int id;
    private String name;
    private Menu menu;
    private Coordinates coordinates;

    public Local(String name, Menu menu,Coordinates coordinates) {
        locals_id+=1;
        this.id=locals_id;
        this.name = name;
        this.menu = menu;
        this.coordinates=coordinates;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
