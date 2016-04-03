package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.game.Coord;
import de.fanalin.futoshiki.solver.game.Field;
import de.fanalin.futoshiki.solver.game.GameSolution;

import java.util.List;

/**
 * Iterate over all possible solutions
 */
class AllSolutionIterator implements SolutionIterator {

    private final int size;
    private GameSolution currentSolution;
    private final List<Coord> coordList;

    public AllSolutionIterator(final int size) {
        this.size = size;

        this.coordList = Coord.getCoordList(size);
        this.currentSolution = new GameSolution(size, 1);
    }

    @Override
    public boolean hasNext() {
        for (Coord coord : coordList) {
            if (currentSolution.getValue(coord) < size) {
                return true;
            }
        }
        return false;
    }

    @Override
    public GameSolution next() {
        // we iterate through the coordinate-list and try to increment to "lowest" possible field by 1
        // (low is identified by the position in the coordList).
        // If we can increment a field, we can immediately return a new solution. If a field got incremented beyound the
        // field boundary (size), we re-set it to 1 and proceed with the next field.

        for (Coord coord : coordList) {
            Field field = currentSolution.getField(coord);
            int newVal = field.getValue() + 1;

            // if we can increment the current field (the new value is <= the maximal allowed value `size`
            // then we do so and we have a new `valid` solution (all fields have values within the boundaries [1,size]).
            // If not, we set the field to `1` and proceed with the next field.
            if (newVal <= size) {
                field.setValue(newVal);
                return currentSolution;
            }

            field.setValue(1);
        }

        // we can only arrive here if all fields had value `6` and
        return null;
    }
}
