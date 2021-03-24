package model.account;

import lombok.Getter;
import lombok.Setter;
import model.Coordinate;

import java.util.Objects;
@Getter
@Setter
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

}
