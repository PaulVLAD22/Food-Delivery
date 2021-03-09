package model.User;

import model.Coordinates;
import model.Local.Product;

public abstract class Person {
    protected static int persons_id=0;
    protected int id;
    protected String username;
    protected String email;
    protected Coordinates coordinates;
    protected String password;

    public Person(String username, String email, Coordinates coordinate, String password) {
        persons_id+=1;
        this.id=persons_id;
        this.username = username;
        this.email=email;
        this.coordinates = coordinate;
        this.password = password;
    }
    @Override
    public boolean equals(Object o){
        if ( o == this){
            return true;
        }
        if (!(o instanceof Person)){
            return false;
        }

        Person p = (Person) o;
        return (this.username.equals(p.username) && this.email.equals(p.email));
    }

    @Override
    public String toString(){
        return "Username: "+username+" Email:"+email+" Coordinates: "+coordinates;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinate) {
        this.coordinates = coordinate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getUsers_id() {
        return persons_id;
    }

    public static void setUsers_id(int users_id) {
        Person.persons_id = users_id;
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
