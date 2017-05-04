package Car_Park_System;

import java.util.List;

/**
 * Created by Tharaka on 11/16/2016.
 */
//creating CarParkManager interface class
public interface CarParkManager {
//all the methods that using to run this software
    void add_Vehicle(Vehicle vehicle);

    Vehicle delete_Vehicle(String iDPlate);

    void print_Current_Vehicle_List();

    void print_Statistics();

    List<Vehicle> short_by_specific_day(int year, int month, int day);

    double calculatePayment(Vehicle vehicle);

    void printParkingCostforAllVehicle();

    Vehicle getVehicle(String iDPlate);


}
