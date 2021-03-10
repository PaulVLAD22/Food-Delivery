package model;

import model.local.Local;
import model.order.Order;
import model.user.Driver;
import model.user.Account;
import model.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Company {
    private Set<Local> locals = new HashSet<>();
    private ArrayList <Account> people= new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Driver> drivers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public ArrayList<Account> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Account> people) {
        this.people = people;
    }

    public Set<Local> getLocals() {
        return locals;
    }

    public void setLocals(Set<Local> locals) {
        this.locals = locals;
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

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
