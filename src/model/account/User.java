package model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.location.Coordinate;

@Data
@AllArgsConstructor
@SuperBuilder
public class User extends Account {

    public User(String username, String email, Coordinate coordinate, String password) {
        super(username, email,coordinate,password);
    }
    @Override
    public String toString(){
        return "User id: "+this.getId()+" "+super.toString();
    }
}
