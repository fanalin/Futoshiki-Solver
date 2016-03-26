package de.fanalin.futoshiki.solver.lpsolver;

import de.fanalin.futoshiki.solver.Coord;
import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.FutoshikiGameSolver;
import de.fanalin.futoshiki.solver.GameSolution;
import net.sf.javailp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Solve games with a Linear Program (LP).
 * See documentation in doc/ subdirectory.
 */
@Service
public class LpSolver implements FutoshikiGameSolver {


    private static final Logger log = LoggerFactory.getLogger(LpSolver.class);

    private SolverFactory solverFactory;

    private ConstraintAdder constraintAdder;

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
        setVariableTypes(game, problem);

        log.info("adding constraints");
        constraintAdder.addConstraint(game, problem);

        Solver solver = solverFactory.get();

        log.info("start solving problem");

        Result result = solver.solve(problem);
        log.info("finished solving problem");

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

    private void setVariableTypes(FutoshikiGame game, Problem problem) {
        int size = game.getProps().getSize();

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                for (int k = 1; k <= size; ++k) {
                    problem.setVarType(getVarName(i,j,k), Integer.class);

                    Linear linearGt0 = new Linear();
                    linearGt0.add(1, getVarName(i,j,k));
                    problem.add(linearGt0, ">=", 0);

                    Linear linearSt1 = new Linear();
                    linearSt1.add(1, getVarName(i,j,k));
                    problem.add(linearSt1, "<=", 1);

                }
            }
        }

        problem.setVarType("y", Integer.class);
    }

    public static String getVarName(int i, int j, int k) {
        return "x[" + i + "," + j + "," + k + "]";
    }
}
