package de.fanalin.futoshiki.solver;

import de.fanalin.futoshiki.solver.bruteforcesolver.*;
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

    @Autowired
    private AllSolutionIteratorFactory allSolutionIteratorFactory;

    @Autowired
    private ValidRowIteratorFactory validRowIteratorFactory;

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
        return getAllSolutionBruteForceSolverFactory();
    }

    private FutoshikiGameSolverFactory getAllSolutionBruteForceSolverFactory() {
        return () -> new BruteForceSolver(allSolutionIteratorFactory);
    }

    private FutoshikiGameSolverFactory getValidRowIteratorSolverFactory() {
        return () -> new BruteForceSolver(validRowIteratorFactory);
    }

    private FutoshikiGameSolverFactory getLpSolverFactory() {
        return () -> lpSolver;
    }
}
