package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.Coord;
import de.fanalin.futoshiki.solver.FutoshikiGameProperties;
import de.fanalin.futoshiki.solver.GameSolution;
import org.springframework.stereotype.Service;

/**
 * Created by matti on 03.04.2016.
 */
@Service
public class AllSolutionIteratorFactory implements SolutionIteratorFactory {

    @Override
    public SolutionIterator get(FutoshikiGameProperties props) {
        return new AllSolutionIterator(
                props.getSize(),
                new GameSolution(props.getSize()),
                Coord.getCoordList(props.getSize())
        );
    }
}
