package model.account;

import model.Coordinate;

import java.util.Objects;

public abstract class Account {
    protected static int account_id=0;
    protected int id;
    protected String username;
    protected String email;
    protected String password;
    protected Coordinate coordinate;

    public Account(String username, String email,Coordinate coordinate, String password) {

        this.username = username;
        this.email=email;
        this.password = password;
        this.coordinate=coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return username.equals(account.username) &&
                email.equals(account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public String toString(){
        return "Username: "+username+" Email:"+email+" Coordinate : "+coordinate;
    }

    public static int getAccount_id() {
        return account_id;
    }

    public static void setAccount_id(int account_id) {
        Account.account_id = account_id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
