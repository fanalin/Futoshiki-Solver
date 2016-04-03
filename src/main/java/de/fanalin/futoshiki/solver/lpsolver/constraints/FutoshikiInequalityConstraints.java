package de.fanalin.futoshiki.solver.lpsolver.constraints;

import de.fanalin.futoshiki.solver.game.Coord;
import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.lpsolver.ConstraintAdder;
import de.fanalin.futoshiki.solver.lpsolver.LpSolver;
import net.sf.javailp.Linear;
import net.sf.javailp.Problem;

/**
 * Constraint: all inequalities from the Futoshiki game
 */
class FutoshikiInequalityConstraints implements ConstraintAdder {
    @Override
    public void addConstraint(FutoshikiGame game, Problem problem) {
        addRightHandSideInequalities(game, problem);
        addLowerHandSideInequalities(game, problem);
    }


    private void addRightHandSideInequalities(final FutoshikiGame game, Problem problem) {

        for (int i = 0; i < game.getProps().getSize() - 1; ++i) {
            for (int j = 0; j < game.getProps().getSize(); ++j) {
                Coord coord = new Coord(i, j);
                Coord rightHand = new Coord(i+1, j);

                int sign = game.getEquation(coord, rightHand);

                if (sign == FutoshikiGame.SMALLER_THAN) {
                    addConstraintForFieldPair(game, problem, coord, rightHand);
                } else if (sign == FutoshikiGame.GREATER_THAN) {
                    addConstraintForFieldPair(game, problem, rightHand, coord);
                }
            }
        }

    }

    private void addLowerHandSideInequalities(final FutoshikiGame game, Problem problem) {

        for (int i = 0; i < game.getProps().getSize(); ++i) {
            for (int j = 0; j < game.getProps().getSize() - 1; ++j) {
                Coord coord = new Coord(i, j);
                Coord lowerHand = new Coord(i, j+1);

                int sign = game.getEquation(coord, lowerHand);

                if (sign == FutoshikiGame.SMALLER_THAN) {
                    addConstraintForFieldPair(game, problem, coord, lowerHand);
                } else if (sign == FutoshikiGame.GREATER_THAN) {
                    addConstraintForFieldPair(game, problem, lowerHand, coord);
                }
            }
        }

    }

    /**
     * add constraints for a pair of fields f1, f2 where value(f1) < value(f2) holds
     * @param game the game
     * @param problem the problem to add the constraint to
     * @param f1 the field which must be smaller
     * @param f2 the field which must be greater
     */
    private void addConstraintForFieldPair(FutoshikiGame game, Problem problem, Coord f1, Coord f2) {

        int size = game.getProps().getSize();

        int M = size + 1;

        for (int k = 1; k <= size; ++k) {
            for (int l = 1; l < k; ++l) {
                Linear linear = new Linear();
                linear.add(1, LpSolver.getVarName(f2.getX(), f2.getY(), l));
                linear.add(M, LpSolver.getVarName(f1.getX(), f1.getY(), k));
                problem.add(linear, "<=", M);
            }
        }

        for (int l = 1; l <= size; ++l) {
            for (int k = l+1; k < size; ++k) {
                Linear linear = new Linear();
                linear.add(1, LpSolver.getVarName(f1.getX(), f1.getY(), k));
                linear.add(M, LpSolver.getVarName(f2.getX(), f2.getY(), l));
                problem.add(linear, "<=", M);
            }
        }
    }
}
