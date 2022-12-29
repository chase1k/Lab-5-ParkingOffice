package parkingoffice;

import java.util.Objects;

public class CarData implements Comparable<CarData> {
    private String plate;
    private int allowedLot;
    private int tickets;

    /**
     * Sets up car data object
     * @param plate license plate
     * @param allowedLot parking lot for car
     */
    public CarData(String plate, int allowedLot){
        this.plate = plate;
        this.allowedLot = allowedLot;
        tickets = 0;

    }

    /**
     * Checks if car is allowed to park in lot
     * @param parkedLot lot parked in
     * @return true if allowed false if not
     */
    public boolean isOk(int parkedLot){return allowedLot == parkedLot;}

    /**
     * Adds ticket to car object
     */
    public void giveTicket(){tickets++;}

    /**
     * Overrides toString
     * @return plate, allowed lot, and tickets
     */
    public String toString(){return plate+" (lot "+allowedLot+") : "+
            tickets+" ticket(s)";}

    /**
     * Resets tickets
     */
    public void payTickets(){tickets = 0;}
    public String getPlate(){return plate;}
    public int getAllowedLot(){return allowedLot;}
    public int getTickets() {return tickets;}

    /**
     * Compares car data objects by tickets then lexicographically
     * @param c the object to be compared.
     * @return 0 if equal (if same lexicographically), >0 if more tickets, <0 if less tickets
     */
    @Override
    public int compareTo(CarData c){
        if (c.tickets - this.tickets == 0){
            if (equals(c)){
                return 0;
            }
            else {
                return this.plate.compareTo(c.getPlate());
            }
        }
        return c.tickets - this.tickets;
    }

    /**
     * hashes plate for sorting
     * @return a hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(plate);
    }

    /**
     * Cars are equal is plate, allowedLot, and tickets are same
     * @param o other object
     * @return true if equal false if not
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof CarData car){
            return this.plate.equals(car.plate) &&
                    this.allowedLot == car.allowedLot &&
                    this.tickets == car.tickets;
        }
        return false;
    }
}
