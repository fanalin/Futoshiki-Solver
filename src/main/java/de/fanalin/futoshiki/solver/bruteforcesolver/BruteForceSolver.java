package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.*;
import de.fanalin.futoshiki.solver.Validator.SolutionValidator;

/**
 * Created by matti on 13.02.2016.
 */
public class BruteForceSolver implements FutoshikiGameSolver {

    private final SolutionIterator solutionIterator;

    public BruteForceSolver(SolutionIterator solutionIterator) {
        this.solutionIterator = solutionIterator;
    }

    @Override
    public GameSolution solve(final FutoshikiGame game) {
        SolutionValidator validator = new SolutionValidator(game);
        int count = 1;

        while (solutionIterator.hasNext()) {
            GameSolution solution = solutionIterator.next();

            ++count;
            print(solution, count);

            if (validator.isValid(solution)) {
                return solution;
            }
        }

        System.out.println("No more possible solutions available");
        return null;
    }

    private void print(final GameSolution solution, int count) {
        System.out.println("Solution #" + count);
        solution.print();
        System.out.println("--------------------");
    }
}
