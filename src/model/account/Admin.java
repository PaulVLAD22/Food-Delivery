package model.account;

public class Admin extends Account {

    public Admin(int id, String username, String email, String password) {
        super(id, username, email, null, password);
    }
}
