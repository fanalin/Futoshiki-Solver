package de.fanalin.futoshiki.solver.game;

/**
 * Created by matti on 26.09.2015.
 */
public class Field {
    private final Coord coord;

    private int value = 0;

    public Field(final Coord coord, int value) {
        this.coord = coord;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Coord getCoord() {
        return coord;
    }

    public boolean isValueSet() {
        return value != 0;
    }

    @Override
    public String toString() {
        String ret = "Field{coord=" + coord;

        if (isValueSet()) {
            ret += ", value = " + value;
        }

        return ret + '}';
    }
}
