package Car_Park_System;

/**
 * Created by Tharaka on 11/16/2016.
 */

//creating van class by extending vehicle abstract class
public class Van extends Vehicle {
    private int cargoVolume;

    public Van() {
    }

    public Van(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }


    public int getCargoVolume() {
        return cargoVolume;
    }


    public void setCargoVolume(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }
}
