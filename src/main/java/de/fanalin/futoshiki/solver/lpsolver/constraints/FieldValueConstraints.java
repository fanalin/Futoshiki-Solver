package de.fanalin.futoshiki.solver.lpsolver.constraints;

import de.fanalin.futoshiki.solver.game.Coord;
import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.lpsolver.ConstraintAdder;
import de.fanalin.futoshiki.solver.lpsolver.LpSolver;
import net.sf.javailp.Linear;
import net.sf.javailp.Problem;

/**
 * Constraint: original (pre-set) values from the Futoshiki game
 */
class FieldValueConstraints implements ConstraintAdder {
    @Override
    public void addConstraint(FutoshikiGame game, Problem problem) {

        for (Coord coord : Coord.getCoordList(game.getProps().getSize())) {
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
}
