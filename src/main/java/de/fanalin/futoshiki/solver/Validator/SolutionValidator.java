package de.fanalin.futoshiki.solver.Validator;

import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.GameSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matti on 13.02.2016.
 */
public class SolutionValidator {
    private final FutoshikiGame game;

    private List<Check> checks = new ArrayList<>();

    public SolutionValidator(final FutoshikiGame game) {
        this.game = game;

        checks.add(new DefinedValuesCheck(game));
        checks.add(new InequalityCheck(game));
        checks.add(new UniquenessCheck(game.getProps().getSize()));
    }

    public boolean isValid(final GameSolution solution) {
        for (Check check : checks) {
            if (! check.check(solution)) {
                return false;
            }
        }

        return true;
    }
}
