package parkingoffice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ParkingOffice {
    Map<String,CarData> cars = new HashMap<>();
    Set<CarData> ticketedCars = new TreeSet<>();
    List<LotData> Lots = new ArrayList<>();

    /**
     * Creates parking office
     * @param totLots total parking lots there are
     * @param filename file for the events that occur
     * @throws FileNotFoundException if file is not found throw exception
     */
    public ParkingOffice(int totLots, String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        for (int i = 0; i < totLots; i++) Lots.add(new LotData(i));

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] cdata = line.split(" ",2);
            cars.put(cdata[0], new CarData(cdata[0], Integer.parseInt(cdata[1])));
        }
        in.close();
    }

    /**
     * Processing events and printing results, handles all tickets, the worst offenders, and lot usage
     * @param events all instances of Days beginning, ending, license plates, etc
     * @throws FileNotFoundException throws exception if file not found
     */
    public void processDays(String events) throws FileNotFoundException {
        Scanner in = new Scanner(new File(events));
        boolean payingTickets = false;
        LotData currentLot = null;

        while(in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("BeginDay")){
                payingTickets = false;
                for (LotData lot: Lots) {
                    lot.newDay();
                }
            }
            else if (line.equals("EndDay")){
                payingTickets = false;
                System.out.println("------------\nEnd of day. Worst offenders are:");
                int i = 0;
                for (CarData car: ticketedCars) {
                    System.out.println(car.getPlate()+" (lot "+car.getAllowedLot()+
                    ") : "+car.getTickets()+" ticket(s)");
                    i++;
                    if (i > 9){break;}// may need to be fixed
                }
                System.out.println("Lot usage was:");
                for (LotData lot: Lots) {
                    System.out.println(lot.report());
                }
            }
            else if (line.equals("P")){
                payingTickets = true;
            }
            else if (line.matches("\\d")){
                payingTickets = false;
                currentLot = Lots.get(Integer.parseInt(line));
            }
            else{
                CarData currentCar = cars.get(line);

                if (payingTickets){
                    ticketedCars.remove(currentCar);
                    currentCar.payTickets();
                }
                else if (currentLot.sawCar(line)){
                    if (!currentCar.isOk(currentLot.getCurrentLot())){
                        ticketedCars.remove(currentCar);
                        currentCar.giveTicket();
                        ticketedCars.add(currentCar);
                    }
                }
            }
        }
    }
}