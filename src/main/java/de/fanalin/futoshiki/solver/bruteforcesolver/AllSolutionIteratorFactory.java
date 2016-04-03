package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;
import org.springframework.stereotype.Service;

/**
 * Factory for AllSolutionIterators
 */
@Service
public class AllSolutionIteratorFactory implements SolutionIteratorFactory {

    @Override
    public SolutionIterator get(FutoshikiGameProperties props) {
        return new AllSolutionIterator(props.getSize());
    }
}
