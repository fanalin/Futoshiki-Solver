package de.fanalin.futoshiki.solver;

import de.fanalin.futoshiki.solver.bruteforcesolver.AllSolutionIteratorSolverFactory;
import de.fanalin.futoshiki.solver.bruteforcesolver.ValidRowIteratorSolverFactory;
import de.fanalin.futoshiki.solver.gamerepository.FutoshikiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Parse CLI Arguments
 */
@Service
class CliArgumentParser {
    @Autowired
    private FutoshikiGameSolver lpSolver;

    @Resource(name = "futoshikiFromFileReader")
    private FutoshikiRepository directoryRepository;

    @Resource(name = "futoshikiOrgRepository")
    private FutoshikiRepository futoshikiOrgInterface;

    public FutoshikiGameProperties getGameProperties(String... strings) {
        return new FutoshikiGameProperties(1, 3);
    }

    public FutoshikiRepository getFutoshikoGameRepository(String... strings) {
        return directoryRepository;
    }

    public FutoshikiGameSolverFactory getSolverFromCliArguments(String... strings) {
        return getValidRowIteratorSolverFactory();
    }

    private FutoshikiGameSolverFactory getAllSolutionBruteForceSolverFactory() {
        return new AllSolutionIteratorSolverFactory();
    }

    private FutoshikiGameSolverFactory getValidRowIteratorSolverFactory() {
        return new ValidRowIteratorSolverFactory();
    }

    private FutoshikiGameSolverFactory getLpSolverFactory() {
        return new FutoshikiGameSolverFactory() {
            @Override
            public FutoshikiGameSolver get(FutoshikiGameProperties props) {
                return lpSolver;
            }
        };
    }
}
