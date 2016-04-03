package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.*;

/**
 * Created by matti on 03.04.2016.
 */
public class AllSolutionIteratorSolverFactory implements FutoshikiGameSolverFactory {
    @Override
    public FutoshikiGameSolver get(FutoshikiGameProperties props) {
        return new BruteForceSolver(
            new AllSolutionIterator(
                props.getSize(),
                new GameSolution(props.getSize()),
                Coord.getCoordList(props.getSize())
            )
        );
    }
}
