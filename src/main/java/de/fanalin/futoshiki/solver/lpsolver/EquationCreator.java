package de.fanalin.futoshiki.solver.lpsolver;

import de.fanalin.futoshiki.solver.*;
import de.fanalin.futoshiki.solver.equation.Equality;
import de.fanalin.futoshiki.solver.equation.Equation;
import de.fanalin.futoshiki.solver.equation.Inequal;
import de.fanalin.futoshiki.solver.equation.SmallerThan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matti on 27.09.2015.
 */
@Service
public class EquationCreator {

    public List<Equation> getEquations(final FutoshikiGame game) {
        List<Equation> eqs = getFieldValueEquations(game);

        eqs.addAll(getOnceInRowConstraints(game));
        eqs.addAll(getOnceInColumnConstraints(game));
        eqs.addAll(getRightHandSideInequalities(game));
        eqs.addAll(getLowerHandSideInequalities(game));
        return eqs;
    }


    private List<Equation> getFieldValueEquations(final FutoshikiGame game) {
        List<Equation> eqs = new ArrayList<Equation>();
        for (Coord coord : getAllCoordinates(game)) {
            int value = game.getValue(coord);

            if (value != FutoshikiGame.NO_VALUE) {
                eqs.add(new Equality(coord, value));
            }
        }
        return eqs;
    }

    private List<Equation> getOnceInRowConstraints(final FutoshikiGame game) {
        List<Equation> eqs = new ArrayList<Equation>();

        for (int i = 0; i < game.getProps().getSize(); ++i) {

            for (int j = 0; j < game.getProps().getSize(); ++j) {
                Coord lh = new Coord(i, j);

                for (int x = j+1; x < game.getProps().getSize(); ++x) {
                    Coord rh = new Coord(i, x);
                    eqs.add(new Inequal(lh, rh));
                }
            }
        }
        return eqs;
    }

    private List<Equation> getOnceInColumnConstraints(final FutoshikiGame game) {
        List<Equation> eqs = new ArrayList<Equation>();

        for (int i = 0; i < game.getProps().getSize(); ++i) {

            for (int j = 0; j < game.getProps().getSize(); ++j) {
                Coord lh = new Coord(j, i);

                for (int x = j+1; x < game.getProps().getSize(); ++x) {
                    Coord rh = new Coord(x, i);
                    eqs.add(new Inequal(lh, rh));
                }
            }
        }
        return eqs;
    }

    private List<Equation> getRightHandSideInequalities(final FutoshikiGame game) {
        List<Equation> eqs = new ArrayList<Equation>();

        for (int i = 0; i < game.getProps().getSize() - 1; ++i) {
            for (int j = 0; j < game.getProps().getSize(); ++j) {
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

        return eqs;
    }

    private List<Equation> getLowerHandSideInequalities(final FutoshikiGame game) {
        List<Equation> eqs = new ArrayList<Equation>();

        for (int i = 0; i < game.getProps().getSize(); ++i) {
            for (int j = 0; j < game.getProps().getSize() - 1; ++j) {
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

        return eqs;
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
