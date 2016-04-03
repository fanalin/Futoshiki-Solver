package de.fanalin.futoshiki.solver.equation;

import de.fanalin.futoshiki.solver.game.Coord;
import de.fanalin.futoshiki.solver.game.GameSolution;

/**
 * Created by matti on 26.09.2015.
 */
public class Equality implements Equation {
    private Coord coord;
    private int value;

    public Equality(Coord coord, int value) {
        this.coord = coord;
        this.value = value;
    }

    public Coord getField() {
        return coord;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return coord + "=" + value;
    }

    @Override
    public boolean isValid(GameSolution solution) {
        return solution.getValue(coord) == value;
    }
}
