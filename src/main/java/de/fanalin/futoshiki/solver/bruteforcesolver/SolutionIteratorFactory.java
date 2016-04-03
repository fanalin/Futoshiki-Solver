package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.FutoshikiGameProperties;

/**
 * Created by matti on 03.04.2016.
 */
public interface SolutionIteratorFactory {
    SolutionIterator get(FutoshikiGameProperties props);
}
