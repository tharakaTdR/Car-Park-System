package Car_Park_System;

import java.io.Serializable;

/**
 * Created by Tharaka on 11/16/2016.
 */
//abstract vehicle class
public abstract class Vehicle implements Serializable {

    private String iDPlate;
    private String brand;
    private DateTime dateTime;

    public String getIDPlate() {
        return iDPlate;
    }

    public void setIDPlate(String iDPlate) {
        this.iDPlate = iDPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
