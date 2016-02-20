package de.fanalin.futoshiki.solver.gamerepository;

import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.FutoshikiGameProperties;

/**
 * Created by matti on 19.09.2015.
 */
public interface FutoshikiRepository {
    FutoshikiGame get(FutoshikiGameProperties properties);
}
