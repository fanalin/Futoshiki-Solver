package de.fanalin.futoshiki.solver.Validator;

import de.fanalin.futoshiki.solver.Coord;
import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.GameSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matti on 14.02.2016.
 */
class DefinedValuesCheck implements Check {

    private final FutoshikiGame game;

    /**
     * the list of values which are defined in the original game.
     * Lazy initialized in checkDefinedValues()
     */
    private List<Coord> definedValues;

    public DefinedValuesCheck(final FutoshikiGame game) {
        this.game = game;
    }

    @Override
    public boolean check(GameSolution solution) {
         initDefinedValues();

        for (Coord coord : definedValues) {
            int definedValue = game.getValue(coord);
            int solutionValue = solution.getValue(coord.getX(), coord.getY());
            if (definedValue != solutionValue) {
                return false;
            }
        }
        return true;
    }

    private void initDefinedValues() {
        if (definedValues != null) {
            return;
        }

        int size = game.getProps().getSize();

        definedValues = new ArrayList<>();
        for (int x = 0; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                Coord coord = new Coord(x, y);
                int value = game.getValue(coord);
                if (value != FutoshikiGame.NO_VALUE) {
                    definedValues.add(coord);
                }
            }
        }
    }


}