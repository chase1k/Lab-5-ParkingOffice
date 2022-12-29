package parkingoffice;

import java.io.FileNotFoundException;

public class ParkingSim {
    /**
     * Main function for program
     * @param args arguments listed in usage
     * @throws FileNotFoundException if file isn't found throw error
     */
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 3) {
            System.out.println("Usage: java ParkingOffice <num-lots> <car-filename> <days-filename>");
        }
        else {
            ParkingOffice po = new ParkingOffice(Integer.parseInt(args[0]),args[1]);
                po.processDays(args[2]);
        }
    }
}