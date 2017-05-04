package Car_Park_System;

/**
 * Created by Tharaka on 11/16/2016.
 */
//creating car class by extend the  abstract vehicle class
public class Car extends Vehicle {

    private int noOfDoors;
    private String color;

    public Car() {
    }

    public Car(int noOfDoors, String color) {
        this.noOfDoors = noOfDoors;
        this.color = color;
    }


    public int getNoOfDoors() {
        return noOfDoors;
    }


    public void setNoOfDoors(int noOfDoors) {
        this.noOfDoors = noOfDoors;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }

}
