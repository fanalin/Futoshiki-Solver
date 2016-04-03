package de.fanalin.futoshiki.solver.lpsolver;

import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import net.sf.javailp.Problem;

/**
 * An interface for various classes which might add constraints to the LP formulation of a Futoshiki game
 */
public interface ConstraintAdder {
    void addConstraint(FutoshikiGame game, Problem problem);
}
