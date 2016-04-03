package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;
import org.springframework.stereotype.Service;

/**
 * Created by matti on 03.04.2016.
 */
@Service
public class ValidRowIteratorFactory implements SolutionIteratorFactory {

    @Override
    public SolutionIterator get(FutoshikiGameProperties props) {

        RowPermutationIteratorFactory factory = new RowPermutationIteratorFactory(
            RowPermutationIteratorFactory.createValidNumbersList(props.getSize())
        );

        return new ValidRowIterator(
            props.getSize(),
            ValidRowIterator.getIteratorList(factory, props.getSize()),
            factory
        );
    }
}
