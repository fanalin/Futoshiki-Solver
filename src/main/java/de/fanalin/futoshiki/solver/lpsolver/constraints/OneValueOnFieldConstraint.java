package de.fanalin.futoshiki.solver.lpsolver.constraints;

import de.fanalin.futoshiki.solver.game.Coord;
import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.lpsolver.ConstraintAdder;
import net.sf.javailp.Linear;
import net.sf.javailp.Problem;

import static de.fanalin.futoshiki.solver.lpsolver.LpSolver.getVarName;

/**
 * Constraint: exactly one value must be set on a field
 */
class OneValueOnFieldConstraint implements ConstraintAdder {

    @Override
    public void addConstraint(FutoshikiGame game, Problem problem) {
        int size = game.getProps().getSize();

        for (Coord coord : Coord.getCoordList(size)) {
            addConstraintForField(game, problem, coord);
        }
    }

    private void addConstraintForField(FutoshikiGame game, Problem problem, Coord coord) {
        Linear linear = new Linear();

        int size = game.getProps().getSize();
        for (int k = 1; k <= size; ++k) {
            linear.add(1, getVarName(coord.getX(), coord.getY(), k));
        }

        problem.add(linear, "=", 1);
    }
}
