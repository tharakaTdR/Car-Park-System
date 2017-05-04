package Car_Park_System;

/**
 * Created by Tharaka on 11/16/2016.
 */
//creating motorbike class by extending vehicle abstract class
public class Motorbike extends Vehicle  {
    private int engineSize;

    public Motorbike() {
    }

    public Motorbike(int engineSize) {
        this.engineSize = engineSize;
    }


    public int getEngineSize() {
        return engineSize;
    }


    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

}

