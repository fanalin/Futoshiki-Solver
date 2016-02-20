package de.fanalin.futoshiki.solver;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by matti on 13.02.2016.
 */
public class CoordTest extends TestCase {

    @Test
    public void testIsLowerNeighborOf() throws Exception {
        Coord c1 = new Coord(0, 1);
        Coord c2 = new Coord(0, 2);

        Coord c3 = new Coord(4, 4);

        assertTrue(c2.isLowerNeighborOf(c1));
        assertFalse(c1.isLowerNeighborOf(c2));

        assertFalse(c1.isLowerNeighborOf(c1));
        assertFalse(c1.isLowerNeighborOf(c3));
        assertFalse(c3.isLowerNeighborOf(c1));
    }

    @Test
    public void testIsRightNeighborOf() throws Exception {
        Coord c1 = new Coord(1, 0);
        Coord c2 = new Coord(2, 0);

        Coord c3 = new Coord(4, 4);

        assertTrue(c2.isRightNeighborOf(c1));
        assertFalse(c1.isRightNeighborOf(c2));

        assertFalse(c1.isRightNeighborOf(c1));
        assertFalse(c1.isRightNeighborOf(c3));
        assertFalse(c3.isRightNeighborOf(c1));

    }
}