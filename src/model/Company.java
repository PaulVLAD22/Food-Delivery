package model;

import model.account.Admin;
import model.local.Local;
import model.order.Order;
import model.account.Driver;
import model.account.Account;
import model.account.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Company {
    private Set<Local> locals = new HashSet<>();
    private ArrayList <Account> costumers = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Driver> drivers = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Company(){
    }

    public Company(Set<Local> locals, ArrayList<Account> costumers, ArrayList<User> users, ArrayList<Driver> drivers, ArrayList<Admin> admins, ArrayList<Order> orders) {
        this.locals = locals;
        this.costumers = costumers;
        this.users = users;
        this.drivers = drivers;
        this.admins = admins;
        this.orders = orders;
    }

    public Set<Local> getLocals() {
        return locals;
    }

    public void setLocals(Set<Local> locals) {
        this.locals = locals;
    }

    public ArrayList<Account> getCostumers() {
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
