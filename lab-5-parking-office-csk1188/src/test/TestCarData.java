package test;

import org.junit.jupiter.api.Test;
import parkingoffice.CarData;

import static org.junit.jupiter.api.Assertions.*;

public class TestCarData {

    @Test
    public void testTickets() {
        CarData car1 = new CarData("ABC1234",0);
        assertEquals("ABC1234 (lot 0) : 0 ticket(s)",car1.toString());
        car1.giveTicket();
        assertEquals("ABC1234 (lot 0) : 1 ticket(s)",car1.toString());
        car1.giveTicket();
        assertEquals("ABC1234 (lot 0) : 2 ticket(s)",car1.toString());
        car1.payTickets();
        assertEquals("ABC1234 (lot 0) : 0 ticket(s)",car1.toString());
    }

    @Test
    public void testOK() {
        CarData car1 = new CarData("ABC1234",0);
        assertFalse(car1.isOk(1));
        assertTrue(car1.isOk(0));
    }

    @Test
    public void testEq() {
        // same plate but different lot is undefined (should never happen), so not tested.
        CarData car1 = new CarData("ABC1234", 0);
        CarData car2 = new CarData("ABC1234", 0);
        CarData car3 = new CarData("DEF1234", 0);
        assertTrue(car1.equals(car2));
        assertFalse(car1.equals(car3));
        // but different amounts of tickets does matter for sorting...
        car1.giveTicket();
        assertFalse(car1.equals(car2));
    }

    @Test
    public void testHash() {
        // just testing that identical cars have same hash and different cars have different hash.
        CarData car1 = new CarData("ABC1234", 0);
        CarData car2 = new CarData("ABC1234", 0);
        CarData car3 = new CarData("DEF1234", 0);
        assertEquals(car1.hashCode(),car2.hashCode());
        assertNotEquals(car1.hashCode(),car3.hashCode());
    }

    // compareTo not tested since it could be done increasing or decreasing by tickets
    // and either way can work in context.
}
