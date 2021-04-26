package model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import model.location.Coordinate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class User extends Account {

    public User(int id, String username, String email, Coordinate coordinate, String password) {
        super(id, username, email, coordinate, password);
    }

    @Override
    public String toString() {
        return "User id: " + this.getId() + " " + super.toString();
    }
}
