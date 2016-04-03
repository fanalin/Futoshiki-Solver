package de.fanalin.futoshiki.solver.lpsolver.constraints;

import de.fanalin.futoshiki.solver.Coord;
import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.lpsolver.ConstraintAdder;
import net.sf.javailp.Linear;
import net.sf.javailp.Problem;

import static de.fanalin.futoshiki.solver.lpsolver.LpSolver.getVarName;

/**
 * Adds constraints to a LP problem which defines that the variables must be binary (0, 1)
 */
class BinaryVarConstraint implements ConstraintAdder {
    @Override
    public void addConstraint(FutoshikiGame game, Problem problem) {
        int size = game.getProps().getSize();

        for (Coord coord : Coord.getCoordList(size)) {
            for (int k = 1; k <= size; ++k) {
                setVarType(problem, coord, k);
            }
        }

        problem.setVarType("y", Integer.class);
    }

    private void setVarType(Problem problem, Coord coord, int val) {

        String varName = getVarName(coord.getX(), coord.getY(), val);

        problem.setVarType(varName, Integer.class);

        Linear linearGt0 = new Linear();
        linearGt0.add(1, varName);
        problem.add(linearGt0, ">=", 0);

        Linear linearSt1 = new Linear();
        linearSt1.add(1, varName);
        problem.add(linearSt1, "<=", 1);
    }
}
