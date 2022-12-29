package parkingoffice;

import java.util.*;

/**
 * A class that represent a parking lot. Every lot has a unique number and a collection
 * of the cars parked in it.
 *
 * @author Chase Killorin
 */
public class LotData {
    Set<String> seenCars;
    private int currentLot;
    /**
     * Constructor takes in the number of the lot.
     * @param n Lot number
     */
    public LotData(int n) {
        currentLot = n;
        seenCars = new HashSet<>();
    }
    /**
     * Returns a string reporting the number of unique cars seen in this lot.
     * The report string is of the form:
     * "Lot {id} was used by {n} car(s) today.", where id is the lot's unique number and n is the number of cars.
     * @return the usage report for the day.
     */
    public String report() {
        return "Lot "+currentLot+" was used by "+seenCars.size()+" car(s) today.";
    }
    /**
     * Takes in a license plate, records the information, and returns whether the car
     * is newly-seen in this lot this day.
     * @param plate A license plate
     * @return True if the car had not previously been seen.
     */
    public boolean sawCar(String plate) {
        if(!seenCars.contains(plate)){
            seenCars.add(plate);
            return true;
        }
        return false;
    }
    /**
     * Resets any data structures for a new day of ticketing.
     */
    public void newDay() {
        seenCars = new HashSet<>();
    }
    public int getCurrentLot(){
        return currentLot;
    }
}