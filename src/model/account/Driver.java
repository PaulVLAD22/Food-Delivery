package model.account;

import model.Coordinate;
import model.order.Order;

public class Driver extends Account {
    private Order currentOrder;
    private double salary;

    public Driver(String username, String email, Coordinate coordinate, String password) {
        super(username, email,coordinate, password);
        this.salary=0;
    }
    @Override
    public String toString(){
        return super.toString()+"Current order: ";
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}
