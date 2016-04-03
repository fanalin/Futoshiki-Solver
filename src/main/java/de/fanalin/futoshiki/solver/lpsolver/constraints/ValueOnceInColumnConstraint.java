package de.fanalin.futoshiki.solver.lpsolver.constraints;

import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.lpsolver.ConstraintAdder;
import net.sf.javailp.Linear;
import net.sf.javailp.Problem;

import static de.fanalin.futoshiki.solver.lpsolver.LpSolver.getVarName;

/**
 * Constraint: value must be set exactly once in a column
 */
class ValueOnceInColumnConstraint implements ConstraintAdder {

    @Override
    public void addConstraint(FutoshikiGame game, Problem problem) {
        int size = game.getProps().getSize();

        for (int i = 0; i < size; ++i) {
            for (int k = 1; k <= size; ++k) {
                addConstraintForRowAndValue(game, problem, i, k);
            }
        }
    }

    private void addConstraintForRowAndValue(FutoshikiGame game, Problem problem, int i, int k) {
        Linear linear = new Linear();

        int size = game.getProps().getSize();
        for (int j = 0; j < size; ++j) {
            linear.add(1, getVarName(i, j, k));
        }

        problem.add(linear, "=", 1);
    }
}
