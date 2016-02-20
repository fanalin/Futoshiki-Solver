package de.fanalin.futoshiki.solver.Validator;

import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.GameSolution;

/**
 * Created by matti on 14.02.2016.
 */
public interface Check {
    boolean check(GameSolution solution);
}
