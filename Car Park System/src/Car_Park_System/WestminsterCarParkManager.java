package Car_Park_System;

import java.util.*;

/**
 * Created by Tharaka on 11/16/2016.
 */

//main function class which hold all the methods
public class WestminsterCarParkManager implements CarParkManager {


    private List<Vehicle> vehicles = new ArrayList<>(20);//creating an array list with 20 initialCapacity
    private final FileHandler fh = new FileHandler();//declaring filehandler
    public static int parkingSlots = 20;//initializing 20 for parkingSlots variable


    //add vehicle method
    @Override
    public void add_Vehicle(Vehicle vehicle) {

        if (vehicle.toString().equals("Van")) {//check if van or not
            parkingSlots -= 2;
        } else {
            parkingSlots--;
        }
        fh.writeData(vehicle);//call filewriter
        vehicles.add(vehicle);//add vehicle to array
    }

    //deleting vehicle method
    @Override
    public Vehicle delete_Vehicle(String iDPlate) {
        Vehicle v = null;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getIDPlate().equals(iDPlate)) {//search matching iDPlate
                v = vehicles.get(i);//get vehicle data
                if (v.toString().equals("Van")) {//check if van or not
                    parkingSlots += 2;
                } else {
                    parkingSlots++;
                }
                vehicles.remove(i);//remove vehicle from array
            }
        }
        return v;
    }

    //View the list of the vehicles parked
    @Override
    public void print_Current_Vehicle_List() {

        System.out.println("| ID Plate | Brand | Parked Time | Type |");
        List<Vehicle> tempVehicles = vehicles;
        Collections.reverse(tempVehicles);
        for (Vehicle v : tempVehicles) {
            System.out.println("| " + v.getIDPlate() + " | " + v.getBrand() + " | " + v.getDateTime().toString() + " | " + v.toString() + " | ");
        }
    }

    public int getSize() {
        return vehicles.size();
    }

    //Print statistics
    @Override
    public void print_Statistics() {
        int total = getSize();
        int cars = 0;
        int vans = 0;
        int bikes = 0;
        for (Vehicle vehicle : vehicles) {
            switch (vehicle.toString()) {
                case "Car":
                    cars++;
                    break;
                case "Van":
                    vans++;
                    break;
                default:
                    bikes++;
                    break;
            }
        }

        System.out.println("Presentage if cars : " + ((double) cars / total) * 100 + " %");
        System.out.println("Presentage if vans : " + ((double) vans / total) * 100 + " %");
        System.out.println("Presentage if motorbikes : " + ((double) bikes / total) * 100 + " %");
        Vehicle maxv = vehicles.get(0);
        Vehicle minv = vehicles.get(0);
        long max = maxv.getDateTime().timeDifference();
        long min = minv.getDateTime().timeDifference();
        for (int i = 1; i < vehicles.size(); i++) {
            if (max < vehicles.get(i).getDateTime().timeDifference()) {
                maxv = vehicles.get(i);
                max = maxv.getDateTime().timeDifference();
            }

            if (min > vehicles.get(i).getDateTime().timeDifference()) {
                minv = vehicles.get(i);
                min = minv.getDateTime().timeDifference();
            }
        }

        System.out.println("longest Vehicle | " + maxv.getIDPlate() + " | " + maxv.getBrand() + " | " + maxv.getDateTime().toString() + " | " + maxv.toString() + " | ");
        System.out.println("latest Vehicle | " + minv.getIDPlate() + " | " + minv.getBrand() + " | " + minv.getDateTime().toString() + " | " + minv.toString() + " | \n");
        System.out.println(parkingSlots == 0 ? "There are no slots availble." : parkingSlots + " free slots availble.");

    }

    //Print the list of vehicle which enter in the parking in a specified day
    @Override
    public List<Vehicle> short_by_specific_day(int year, int month, int day) {
        List<Vehicle> v = fh.readData();
        List<Vehicle> vehicleList = new ArrayList<>();

        for (Vehicle vehicle : v) {
            DateTime dateTime = vehicle.getDateTime();
            if (dateTime.getYear() == year && dateTime.getMonth() == month && dateTime.getDay() == day) {
                vehicleList.add(vehicle);
            }

        }
        return vehicleList;
    }

    //Pay car park charge
    @Override
    public double calculatePayment(Vehicle vehicle) {
        double charge = 0;
        long time = vehicle.getDateTime().timeDifference();
        long hours = (time / (1000 * 60 * 60)) % 24;
        if (hours > 3) {
            charge = (9 + (hours - 3));
        } else if (hours == 0) {
            charge = 3;
        } else if (hours <= 3) {
            charge = (int) (hours) * 3;

        }
        return charge;
    }

    @Override
    public void printParkingCostforAllVehicle() {
        System.out.println("| ID Plate | Brand | Parked Time | Type | Fee |");

        for (Vehicle v : vehicles) {
            System.out.println("| " + v.getIDPlate() + " | " + v.getBrand() + " | " + v.getDateTime().toString() + " | " + v.toString() + " |  " + calculatePayment(v) + "$  | ");
        }

    }

    @Override
    public Vehicle getVehicle(String iDPlate) {
        try {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getIDPlate().equals(iDPlate)) {//search matching id plate
                    return vehicle;
                }
            }
        } catch (NullPointerException e) {

        }
        return null;

    }

    public void menu() {

        {
            Scanner sc = new Scanner(System.in);
            Vehicle vehicle = null;
            WestminsterCarParkManager carParkManager = new WestminsterCarParkManager();
            Loop:
            while (true) {

                System.out.println("Welcome to Westminster Car Park Manager!!!\n");
                System.out.println("\t \t \t");
                System.out.println("\t\t\t**** Main Menu ****");
                System.out.println("=============================================");
                System.out.println("Press \"1\" . Add a new Vehicle");
                System.out.println("Press \"2\" . Delete a Vehicle");
                System.out.println("Press \"3\" . View the list of the vehicles parked");
                System.out.println("Press \"4\" . Print statistics");
                System.out.println("Press \"5\" . Print the list of vehicle which enter in the parking in a specified day");
                System.out.println("Press \"6\" . Pay car park charge");
                System.out.println("Press \"0\" . EXIT");
                System.out.println("============================================\n");
                System.out.println("\t********** Vehicle Park System **********");
                System.out.print("\nChoose An Option : ");


                char x = sc.next().charAt(0);
                switch (x) {
                    case '1':
                        if (parkingSlots > 0) {

                            System.out.println("Enter ID Plate : ");
                            String id = sc.next();

                            System.out.println("Enter brand : ");
                            String brand = sc.next();

                            System.out.println("Press 1 to add car");
                            System.out.println("Press 2 to add van");
                            System.out.println("Press 3 to add motorbike");

                            int y = sc.nextInt();
                            switch (y) {
                                case 1:
                                    int noOfDoors = 0;
                                    do {
                                        try {
                                            System.out.println("Enter no of doors : ");
                                            noOfDoors = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input.");
                                        }
                                        sc.nextLine();
                                    }
                                    while ((noOfDoors <= 0 || noOfDoors >= 6) && !(noOfDoors % 2 == 1));//validate input
                                    System.out.println("Enter color : ");
                                    String color = sc.next();
                                    vehicle = new Car(noOfDoors, color);
                                    vehicle.setIDPlate(id);
                                    vehicle.setBrand(brand);
                                    vehicle.setDateTime(new DateTime());
                                    break;
                                case 2:
                                    if (parkingSlots > 1) {
                                        int cargoVolume = 0;
                                        do {
                                            try {
                                                System.out.println("Enter cargo volume : ");
                                                cargoVolume = sc.nextInt();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input.");
                                            }
                                            sc.nextLine();
                                        } while (cargoVolume <= 0);
                                        vehicle = new Van(cargoVolume);
                                        vehicle.setIDPlate(id);
                                        vehicle.setBrand(brand);
                                        vehicle.setDateTime(new DateTime());

                                    } else {
                                        System.out.println("Error there are no empty slots available");
                                    }
                                    break;
                                case 3:
                                    int engineSize = 0;
                                    do {
                                        try {
                                            System.out.println("Enter size of the engine : ");
                                            engineSize = sc.nextInt();

                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input.");
                                        }
                                        sc.nextLine();
                                    } while (engineSize <= 25 || engineSize >= 1500);//validate input
                                    vehicle = new Motorbike(engineSize);
                                    vehicle.setIDPlate(id);
                                    vehicle.setBrand(brand);
                                    vehicle.setDateTime(new DateTime());

                                    break;

                                default:
                                    System.out.println("Invalid Selection");
                            }

                            carParkManager.add_Vehicle(vehicle);

                            System.out.println(parkingSlots == 0 ? "There are no slots availble." : parkingSlots + " free slots availble.");
                        } else {
                            System.out.println("Error there are no empty slots available");
                        }
                        break;
                    case '2':
                        System.out.println("Enter ID Plate : ");
                        String iDPlate = sc.next();
                        if (carParkManager.getVehicle(iDPlate) != null) {
                            Vehicle v = carParkManager.delete_Vehicle(iDPlate);
                            System.out.println(v.toString() + " is leaving car park.");
                        } else {
                            System.out.println("Error unknown ID Plate.");
                        }
                        break;
                    case '3':
                        carParkManager.print_Current_Vehicle_List();
                        break;

                    case '4':
                        carParkManager.print_Statistics();
                        break;
                    case '5':
                        System.out.println("Enter Date ex(11/16/2016): ");
                        try {

                            String day = sc.next();

                            String ar[] = day.split("/");

                            List<Vehicle> v = carParkManager.short_by_specific_day(Integer.parseInt(ar[2]), Integer.parseInt(ar[0]), Integer.parseInt(ar[1]));
                            if (v.isEmpty()) {
                                System.out.println("No vehicles are parked in " + day);
                            } else {
                                System.out.println("| ID Plate | Brand | Parked Time | Type |");
                                for (Vehicle vehicle1 : v) {
                                    System.out.println("| " + vehicle1.getIDPlate() + " | " + vehicle1.getBrand() + " | " + vehicle1.getDateTime().toString() + " | " + vehicle1.toString() + " | ");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid Input.");
                        }
                        break;

                    case '6':
                        carParkManager.printParkingCostforAllVehicle();
                        break;
                    default:
                        System.out.println("Invalid Selection");
                        break;

                }
            }

        }


    }
}














