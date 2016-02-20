package de.fanalin.futoshiki.solver;

/**
 * Created by matti on 27.09.2015.
 */
public interface FutoshikiGame {
    int SMALLER_THAN = 0;
    int GREATER_THAN = 1;
    int NO_EQUATION = 2;
    int NO_VALUE = 0;

    FutoshikiGameProperties getProps();

    int getValue(Coord coord);

    int getEquation(Coord coord1, Coord coord2);
}
