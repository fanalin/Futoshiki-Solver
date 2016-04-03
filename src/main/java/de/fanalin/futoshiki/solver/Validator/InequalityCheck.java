package de.fanalin.futoshiki.solver.Validator;

import de.fanalin.futoshiki.solver.game.Coord;
import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.game.GameSolution;
import de.fanalin.futoshiki.solver.equation.Equation;
import de.fanalin.futoshiki.solver.equation.SmallerThan;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks the validity of greater/smaller inequalities
 */
public class InequalityCheck implements Check {

    private final FutoshikiGame game;

    /**
     * the list of values which are defined in the original game.
     * Lazy initialized in checkDefinedValues()
     */
    private List<Equation> equations;

    public InequalityCheck(final FutoshikiGame game) {
        this.game = game;
    }

    @Override
    public boolean check(GameSolution solution) {
        initEquations();

        for (Equation eq : equations) {
            if (! eq.isValid(solution)) {
                return false;
            }
        }
        return true;
    }

    private void initEquations() {
        if (equations != null) {
            return;
        }

        equations = new ArrayList<>();
        addRightHandSideInequalities(equations);
        addLowerHandSideInequalities(equations);
    }

    private void addRightHandSideInequalities(List<Equation> eqs) {
        int size = game.getProps().getSize();
        for (int i = 0; i < size - 1; ++i) {
            for (int j = 0; j < size; ++j) {
                Coord coord = new Coord(i, j);
                Coord rightHand = new Coord(i+1, j);

                int sign = game.getEquation(coord, rightHand);

                if (sign == FutoshikiGame.SMALLER_THAN) {
                    eqs.add(new SmallerThan(coord, rightHand));
                } else if (sign == FutoshikiGame.GREATER_THAN) {
                    eqs.add(new SmallerThan(rightHand, coord));
                }
            }
        }
    }

    private void addLowerHandSideInequalities(List<Equation> eqs) {
        int size = game.getProps().getSize();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size - 1; ++j) {
                Coord coord = new Coord(i, j);
                Coord lowerHand = new Coord(i, j+1);

                int sign = game.getEquation(coord, lowerHand);

                if (sign == FutoshikiGame.SMALLER_THAN) {
                    eqs.add(new SmallerThan(coord, lowerHand));
                } else if (sign == FutoshikiGame.GREATER_THAN) {
                    eqs.add(new SmallerThan(lowerHand, coord));
                }
            }
        }
    }
}
