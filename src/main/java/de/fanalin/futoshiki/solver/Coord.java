package de.fanalin.futoshiki.solver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matti on 26.09.2015.
 */
public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean isLowerNeighborOf(final Coord c) {
        if (c == null) {
            throw new IllegalArgumentException("given coord must not be null");
        }

        if (this.getX() != c.getX()) {
            return false;
        }

        if (this.getY() -1 == c.getY()) {
            return true;
        }

        return false;
    }

    public boolean isRightNeighborOf(final Coord c) {
        if (c == null) {
            throw new IllegalArgumentException("given coord must not be null");
        }

        if (this.getY() != c.getY()) {
            return false;
        }

        if (this.getX() -1 == c.getX()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (x != coord.x) return false;
        return y == coord.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


    public static List<Coord> getCoordList(int size) {
        List<Coord> coordList = new ArrayList<>();
        for (int x = 0; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                coordList.add(new Coord(x, y));
            }
        }

        return coordList;
    }
}
