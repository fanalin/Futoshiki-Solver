package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.Coord;
import de.fanalin.futoshiki.solver.GameSolution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by matti on 20.02.2016.
 */
public class ValidRowIterator implements SolutionIterator {
    private final int size;
    private GameSolution currentSolution;
    private final RowIteratorFactory rowIteratorFactory;
    private List<Iterator<List<Integer>>> rowIterators;

    public ValidRowIterator(int size, List<Iterator<List<Integer>>> startingIterators, final RowIteratorFactory rowIteratorFactory) {
        this.size = size;
        this.rowIteratorFactory = rowIteratorFactory;
        this.rowIterators = startingIterators;
        this.currentSolution = new GameSolution(size);

        for (int i = 0; i < size; ++i) {
            fillInSolution(startingIterators.get(i).next(), i, currentSolution);
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
                fillInSolution(row, i, currentSolution);

                return currentSolution;
            }

            rowIterators.set(i, rowIteratorFactory.get());
        }

        return null;
    }


    private void fillInSolution(final List<Integer> row, int y, GameSolution solution) {
        for (int x = 0, len = row.size(); x < len; ++x) {
            int val = row.get(x);
            solution.getField(new Coord(x, y)).setValue(val);
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
