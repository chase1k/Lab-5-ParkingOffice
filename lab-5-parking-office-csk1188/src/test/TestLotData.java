package test;

import org.junit.jupiter.api.Test;
import parkingoffice.LotData;

import static org.junit.jupiter.api.Assertions.*;

public class TestLotData {

    @Test
    public void testReport() {
        LotData l1 = new LotData(0);
        assertEquals("Lot 0 was used by 0 car(s) today.",l1.report());
        l1.sawCar("ABC1234");
        assertEquals("Lot 0 was used by 1 car(s) today.",l1.report());

    }

    @Test
    public void testSaw() {
        LotData l1 = new LotData(0);
        assertTrue(l1.sawCar("ABC1234"));
        assertTrue(l1.sawCar("DEF2345"));
        assertEquals("Lot 0 was used by 2 car(s) today.",l1.report());
        assertFalse(l1.sawCar("ABC1234"));
        assertEquals("Lot 0 was used by 2 car(s) today.",l1.report());
    }

    @Test
    public void testDay() {
        LotData l1 = new LotData(0);
        l1.sawCar("ABC1234");
        assertEquals("Lot 0 was used by 1 car(s) today.",l1.report());
        l1.newDay();
        assertEquals("Lot 0 was used by 0 car(s) today.",l1.report());
    }
}
