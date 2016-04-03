package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.game.Coord;
import de.fanalin.futoshiki.solver.game.GameSolution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A iterator over solutions candidates, where each solution candidate consists of valid rows (no duplicates in a row)
 */
public class ValidRowIterator implements SolutionIterator {
    private final int size;
    private final RowIteratorFactory rowIteratorFactory;
    private List<Iterator<List<Integer>>> rowIterators;

    private GameSolution currentSolution;

    public ValidRowIterator(int size, List<Iterator<List<Integer>>> startingIterators, final RowIteratorFactory rowIteratorFactory) {
        this.size = size;
        this.rowIteratorFactory = rowIteratorFactory;
        this.rowIterators = startingIterators;

        currentSolution = new GameSolution(size);
        for (int i = 0; i < size; ++i) {
            fillSolution(startingIterators.get(i).next(), i);
        }
    }

    @Override
    public boolean hasNext() {
        for (Iterator<List<Integer>> iter : rowIterators) {
            if (iter.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public GameSolution next() {
        for (int i = 0, len = rowIterators.size(); i < len; ++i) {
            Iterator<List<Integer>> iter = rowIterators.get(i);

            if (iter.hasNext()) {
                List<Integer> row = iter.next();
                fillSolution(row, i);
                return currentSolution;
            }

            rowIterators.set(i, rowIteratorFactory.get());
        }

        return null;
    }


    private void fillSolution(List<Integer> row, int rowIndex) {
        for (int x = 0; x < size; ++x) {
            int val = row.get(x);
            currentSolution.getField(new Coord(x, rowIndex)).setValue(val);
        }

    }

    public static List<Iterator<List<Integer>>> getIteratorList(RowIteratorFactory factory, int size) {
        List<Iterator<List<Integer>>> list = new ArrayList<>(size);

        for (int x = 0; x < size; ++x) {
            list.add(factory.get());
        }

        return list;
    }
}
