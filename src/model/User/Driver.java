package model.User;

import model.Coordinates;
import model.Order.Order;

public class Driver extends Person {
    private Order currentOrder;

    public Driver(String username, String email, Coordinates coordinate, String password) {
        super(username, email, coordinate, password);
    }
    @Override
    public String toString(){
        return super.toString()+"Current order: ";
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}
