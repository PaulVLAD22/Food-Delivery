package model.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    public String country;
    public String city;
    public String street;

    public Address() {
    }

    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
