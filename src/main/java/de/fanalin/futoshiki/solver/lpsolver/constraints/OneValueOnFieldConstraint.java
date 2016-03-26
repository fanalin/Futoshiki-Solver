package de.fanalin.futoshiki.solver.lpsolver.constraints;

import de.fanalin.futoshiki.solver.FutoshikiGame;
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

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addConstraintForField(game, problem, i, j);
            }
        }
    }

    private void addConstraintForField(FutoshikiGame game, Problem problem, int i, int j) {
        Linear linear = new Linear();

        int size = game.getProps().getSize();
        for (int k = 1; k <= size; ++k) {
            linear.add(1, getVarName(i, j, k));
        }

        problem.add(linear, "=", 1);
    }
}
