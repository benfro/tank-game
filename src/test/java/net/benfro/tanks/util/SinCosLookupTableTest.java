package net.benfro.tanks.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinCosLookupTableTest {
    @Test
    public void sin() throws Exception {
        assertEquals(Math.sin(0), SinCosLookupTable.sin(0), 0.001);
        assertEquals(Math.sin(Math.toRadians(90)), SinCosLookupTable.sin(90), 0.001);
        assertEquals(Math.sin(Math.toRadians(30)), SinCosLookupTable.sin(30), 0.001);
        assertEquals(Math.sin(Math.toRadians(185)), SinCosLookupTable.sin(185), 0.001);
    }

    @Test
    public void cos() throws Exception {
        assertEquals(Math.cos(0), SinCosLookupTable.cos(0), 0.001);
        assertEquals(Math.cos(Math.toRadians(90)), SinCosLookupTable.cos(90), 0.001);
        assertEquals(Math.cos(Math.toRadians(30)), SinCosLookupTable.cos(30), 0.001);
        assertEquals(Math.cos(Math.toRadians(185)), SinCosLookupTable.cos(185), 0.001);
    }

}
