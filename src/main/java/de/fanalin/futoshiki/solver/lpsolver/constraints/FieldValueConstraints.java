package de.fanalin.futoshiki.solver.lpsolver.constraints;

import de.fanalin.futoshiki.solver.Coord;
import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.lpsolver.ConstraintAdder;
import de.fanalin.futoshiki.solver.lpsolver.LpSolver;
import net.sf.javailp.Linear;
import net.sf.javailp.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Constraint: original (pre-set) values from the Futoshiki game
 */
class FieldValueConstraints implements ConstraintAdder {
    @Override
    public void addConstraint(FutoshikiGame game, Problem problem) {

        for (Coord coord : getAllCoordinates(game)) {
            int value = game.getValue(coord);

            if (value != FutoshikiGame.NO_VALUE) {
                addValueConstraint(problem, coord, value);
            }
        }


    }

    private void addValueConstraint(Problem problem, Coord coord, int value) {
        Linear linear = new Linear();
        linear.add(1, LpSolver.getVarName(coord.getX(), coord.getY(), value));
        problem.add(linear, "=", 1);
    }

    private List<Coord> getAllCoordinates(final FutoshikiGame game) {
        List<Coord> coords = new ArrayList<>();
        for (int i = 0; i < game.getProps().getSize(); ++i) {
            for (int j = 0; j < game.getProps().getSize(); ++j) {
                coords.add(new Coord(i, j));
            }
        }
        return coords;
    }
}
