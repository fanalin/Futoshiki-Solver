package de.fanalin.futoshiki.solver.Validator;

import de.fanalin.futoshiki.solver.GameSolution;


/**
 * Created by matti on 14.02.2016.
 */
class UniquenessCheck implements Check {

    private final int size;

    public UniquenessCheck(final int size) {
        this.size = size;
    }

    @Override
    public boolean check(final GameSolution solution) {
        // every value should  occur exactly once in each row and column.
        for (int val = 1; val <= size; ++val) {

            // z can be a row or column index here. The distinction is made within the isValueUnique* methods
            for (int z = 0; z < size; ++z) {
                if (! isValueUniqueInRow(solution, val, z)) {
                    return false;
                }

                if (! isValueUniqueInColumn(solution, val, z)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValueUniqueInRow(final GameSolution solution, int value, int row) {
        int occs = 0;

        for (int x = 0; x < size; ++x) {
            if (solution.getValue(x, row) == value) {
                ++occs;
            }
        }

        return occs == 1;
    }

    private boolean isValueUniqueInColumn(final GameSolution solution, int value, int column) {
        int occs = 0;

        for (int y = 0; y < size; ++y) {
            if (solution.getValue(column, y) == value) {
                ++occs;
            }
        }

        return occs == 1;
    }
}
