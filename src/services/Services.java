package services;

import models.Coordinates;

public class Services {
    public double calculateDistance(Coordinates c1, Coordinates c2){
        return Math.sqrt(Math.pow(c2.getX()-c1.getX(),2)+
                Math.pow(c2.getY()-c1.getY(),2));
    }
}
