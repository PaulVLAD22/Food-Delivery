package model.account;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.location.Coordinate;
import model.order.Order;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Driver extends Account {
    private Order currentOrder;
    private double salary=0;

    public Driver(String username, String email, Coordinate coordinate, String password) {
        super(username, email,coordinate, password);
    }
}
