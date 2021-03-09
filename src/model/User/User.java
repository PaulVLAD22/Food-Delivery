package model.User;

import model.Coordinates;

public class User extends Person {

    public User(String username, String email, Coordinates coordinate, String password) {
        super(username, email, coordinate, password);
    }
}
