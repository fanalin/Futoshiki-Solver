package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.FutoshikiGameProperties;
import de.fanalin.futoshiki.solver.FutoshikiGameSolver;
import de.fanalin.futoshiki.solver.FutoshikiGameSolverFactory;

/**
 * Created by matti on 03.04.2016.
 */
public class ValidRowIteratorSolverFactory implements FutoshikiGameSolverFactory {

    @Override
    public FutoshikiGameSolver get(FutoshikiGameProperties props) {

        RowPermutationIteratorFactory factory = new RowPermutationIteratorFactory(
            RowPermutationIteratorFactory.createValidNumbersList(props.getSize())
        );

        return new BruteForceSolver(
            new ValidRowIterator(
                props.getSize(),
                ValidRowIterator.getIteratorList(factory, props.getSize()),
                factory
            )
        );
    }
}
