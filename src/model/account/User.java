package model.account;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import model.location.Coordinate;

public class User extends Account {

    public User(){

    }

    public User(String username, String email, Coordinate coordinate, String password) {
        super(username, email,coordinate,password);
    }
    @Override
    public String toString(){
        return "User id: "+this.getId()+" "+super.toString();
    }
}
