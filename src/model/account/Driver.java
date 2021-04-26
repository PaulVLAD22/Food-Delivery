package model.account;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.location.Coordinate;
import model.order.Order;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Driver extends Account {
    private Order currentOrder;
    private double salary = 0;

    public Driver(int id, String username, String email, Coordinate coordinate, String password) {
        super(id, username, email, coordinate, password);
    }

    @Override
    public String toString() {
        return "Driver id: " + this.getId() + " " + super.toString();
    }
}
