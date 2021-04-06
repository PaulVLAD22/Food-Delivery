package model;

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

public class Company {
    private static Company instance = null;

    private Set<Local> locals = new HashSet<>();
    private List <Account> costumers = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    //singleton
    public Company(){
    }
    public static Company getInstance() {
        if (instance==null){
            instance=new Company();
        }
        return instance;
    }

    public Set<Local> getLocals() {
        return locals;
    }

    public void setLocals(Set<Local> locals) {
        this.locals = locals;
    }

    public List<Account> getCostumers() {
        return costumers;
    }

    public void setCostumers(ArrayList<Account> costumers) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
