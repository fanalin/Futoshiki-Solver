package de.fanalin.futoshiki.solver.bruteforcesolver;

import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;

/**
 * Created by matti on 03.04.2016.
 */
interface SolutionIteratorFactory {
    SolutionIterator get(FutoshikiGameProperties props);
}
