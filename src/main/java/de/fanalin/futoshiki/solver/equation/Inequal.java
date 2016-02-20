package de.fanalin.futoshiki.solver.equation;

import de.fanalin.futoshiki.solver.Coord;
import de.fanalin.futoshiki.solver.GameSolution;

/**
 * Created by matti on 26.09.2015.
 */
public class Inequal implements Equation {
    private Coord lh;
    private Coord rh;

    public Inequal(Coord lh, Coord rh) {
        this.lh = lh;
        this.rh = rh;
    }

    public Coord getLeftHand() {
        return lh;
    }

    public Coord getRightHand() {
        return rh;
    }

    public String getComparator() {
        return "!=";
    }

    @Override
    public String toString() {
        return lh + " != " + rh;
    }

    @Override
    public boolean isValid(GameSolution solution) {
        return solution.getValue(lh) != solution.getValue(rh);
    }
}
