package Car_Park_System;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tharaka on 11/17/2016.
 */
//creating class FileHandler to save to stats to textfile
public class FileHandler {

    //this method write data to text file
    public void writeData(Vehicle vehicle) {
        List<Vehicle> list = readData();
        list.add(vehicle);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Data.txt"))) {

            objectOutputStream.writeObject(list);//write object to txt  file
            objectOutputStream.flush();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


        //This method read data from txt file
    public List<Vehicle> readData() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {

            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Data.txt"))) {

                Object line = null;

                while ((line = objectInputStream.readObject()) != null) {
                    vehicles = (List<Vehicle>) line;

                }

            }

        }catch(EOFException e){

        }catch (ClassNotFoundException | IOException ex) {

            System.out.println(ex.getMessage());

        }
        return vehicles;

    }

}