package models.User;

import models.Coordinates;

public class User {
    private static int users_id=0;
    private int id;
    private String username;
    private String email;
    private Coordinates coordinates;
    private String password;

    public User(String username, String email, Coordinates coordinate, String password) {
        users_id+=1;
        this.id=users_id;
        this.username = username;
        this.email=email;
        this.coordinates = coordinate;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinate) {
        this.coordinates = coordinate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getUsers_id() {
        return users_id;
    }

    public static void setUsers_id(int users_id) {
        User.users_id = users_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
