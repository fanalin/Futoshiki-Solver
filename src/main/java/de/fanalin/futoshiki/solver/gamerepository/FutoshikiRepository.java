package de.fanalin.futoshiki.solver.gamerepository;

import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;

/**
 * Created by matti on 19.09.2015.
 */
public interface FutoshikiRepository {
    FutoshikiGame get(FutoshikiGameProperties properties);
}
