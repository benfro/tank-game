package net.benfro.tanks;


import org.junit.jupiter.api.Test;

import static net.benfro.tanks.Movement.normalizeAngle;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TankTest {

    @Test
    public void name() throws Exception {
        assertEquals(0.0, normalizeAngle(0), 0.0);
        assertEquals(0.0, normalizeAngle(2 * Math.PI), 0.0);
        assertEquals(0.0, normalizeAngle(4 * Math.PI), 0.0);
        assertEquals(Math.PI, normalizeAngle(5 * Math.PI), 0.0);
        assertEquals(Math.PI, normalizeAngle(-Math.PI), 0.0);
        assertEquals(Math.PI, normalizeAngle(Math.PI), 0.0);
    }
}
