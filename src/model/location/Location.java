package model.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private Address address;
    private Coordinate coordinate;

    public Location() {
    }

    public Location(Address address, Coordinate coordinate) {
        this.address = address;
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address=" + address +
                ", coordinate=" + coordinate +
                '}';
    }
}
