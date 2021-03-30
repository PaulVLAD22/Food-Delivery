package model.account;

import lombok.Getter;
import lombok.Setter;
import model.location.Coordinate;
import model.order.Order;
@Getter
@Setter
public class Driver extends Account {
    private Order currentOrder=null;
    private double salary;

    public Driver(String username, String email, Coordinate coordinate, String password) {
        super(username, email,coordinate, password);
        this.salary=0;
    }
    @Override
    public String toString(){
        return "Driver id: "+this.getId()+" "+super.toString()+"Current order: "+ (currentOrder!=null ? currentOrder.toString() : "none");
    }
}
