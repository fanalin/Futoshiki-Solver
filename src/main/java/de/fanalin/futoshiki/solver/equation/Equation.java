package de.fanalin.futoshiki.solver.equation;

import de.fanalin.futoshiki.solver.game.GameSolution;

/**
 * Created by matti on 26.09.2015.
 */
public interface Equation {
    boolean isValid(GameSolution solution);
}
