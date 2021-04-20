package model;

import lombok.Getter;
import lombok.Setter;
import model.account.Admin;
import model.local.Local;
import model.order.Order;
import model.account.Driver;
import model.account.Account;
import model.account.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Company {
    private static Company instance = null;

    private Set<Local> locals = new HashSet<>();
    private List <Account> costumers = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    //singleton
    private Company(){
    }
    public static Company getInstance() {
        if (instance==null){
            instance=new Company();
        }
        return instance;
    }

    public void setCostumers(List<Account> costumers) {
        this.costumers = costumers;
        for (Account account : costumers){
            if (account instanceof User){
                this.getUsers().add((User)account);
            }
            else{
                this.getDrivers().add((Driver)account);
            }
        }
    }
}
