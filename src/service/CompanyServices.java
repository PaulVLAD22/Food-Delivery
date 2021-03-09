package service;

import model.Company;
import model.User.Driver;
import model.User.Person;
import model.User.User;

import java.util.Scanner;

public class CompanyServices {
    public static void addPerson(Company company, Person person){
        company.getPeople().add(person);
        if (person instanceof User){
            company.getUsers().add((User)person);
        }
        else{
            company.getDrivers().add((Driver)person);
        }
    }
    public static void removePerson(Company company, Person person){
        Person toDeletePerson = null;
        for (Person p : company.getPeople()){
            if (p.equals(person)){
                System.out.println("Enter password:");
                Scanner scanner = new Scanner(System.in);
                String password = scanner.next();
                if (password.equals(p.getPassword())){
                    toDeletePerson=p;
                }
                else{
                    System.out.println("Wrong Password");
                    System.out.println("Resetting...");
                }
            }
        }
        company.getPeople().remove(toDeletePerson);
    }
}
