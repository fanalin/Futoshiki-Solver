package de.fanalin.futoshiki.solver.lpsolver;

import com.github.vbauer.herald.annotation.Log;
import de.fanalin.futoshiki.solver.game.Coord;
import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.game.FutoshikiGameSolver;
import de.fanalin.futoshiki.solver.game.GameSolution;
import net.sf.javailp.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Solve games with a Linear Program (LP).
 * See documentation in doc/ subdirectory.
 */
@Service
public class LpSolver implements FutoshikiGameSolver {

    @Log
    private Logger log;

    private SolverFactory solverFactory;

    private ConstraintAdder constraintAdder;

    @Log
    private Logger logger;

    @Autowired
    public LpSolver(SolverFactory solverFactory, ConstraintAdder constraintAdder) {
        this.solverFactory = solverFactory;
        this.constraintAdder = constraintAdder;
    }

    @Override
    public GameSolution solve(FutoshikiGame game) {

        log.info("Starting LP-solver");
        Problem problem = new Problem();

        addObjective(problem);

        log.info("adding constraints");
        constraintAdder.addConstraint(game, problem);

        Solver solver = solverFactory.get();

        log.info("start solving problem");

        Result result = solver.solve(problem);
        log.info("finished solving problem");

        if (result == null) {
            log.error("LP-Solver did not found a result!");
            return null;
        }

        return createSolutionFromLpResult(game, result);
    }

    private GameSolution createSolutionFromLpResult(FutoshikiGame game, Result result) {
        int size = game.getProps().getSize();
        GameSolution sol = new GameSolution(size);


        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                for (int k = 1; k <= size; ++k) {
                    Number num = result.getPrimalValue(getVarName(i, j, k));
                    if (num.intValue() == 1) {
                        sol.getField(new Coord(i, j)).setValue(k);
                    }
                }
            }
        }

        return sol;
    }

    private void addObjective(Problem problem) {
        Linear linear = new Linear();
        problem.setObjective(linear, OptType.MAX);
    }

    public static String getVarName(int i, int j, int k) {
        return "x[" + i + "," + j + "," + k + "]";
    }
}
