package de.fanalin.futoshiki.solver.cli;

import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;
import de.fanalin.futoshiki.solver.game.FutoshikiGameSolver;
import de.fanalin.futoshiki.solver.bruteforcesolver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * Parse CLI Arguments
 */
@Component
public class CliArguments {
    private FutoshikiGameSolver lpSolver;

    private AllSolutionIteratorFactory allSolutionIteratorFactory;

    private ValidRowIteratorFactory validRowIteratorFactory;

    private ApplicationArguments args;

    @Autowired
    public CliArguments(FutoshikiGameSolver lpSolver, AllSolutionIteratorFactory allSolutionIteratorFactory, ValidRowIteratorFactory validRowIteratorFactory, ApplicationArguments args) {
        this.lpSolver = lpSolver;
        this.allSolutionIteratorFactory = allSolutionIteratorFactory;
        this.validRowIteratorFactory = validRowIteratorFactory;
        this.args = args;
    }

    public FutoshikiGameProperties getGameProperties() {
        int size = 4;
        int difficulty = 3;

        if (args.containsOption("difficulty")) {
            difficulty = Integer.valueOf(args.getOptionValues("difficulty").get(0)).intValue();
        }

        if (args.containsOption("size")) {
            size = Integer.valueOf(args.getOptionValues("size").get(0)).intValue();
        }

        return new FutoshikiGameProperties(difficulty, size);
    }

    public FutoshikiGameSolver getSolver() {

        if (args.containsOption("lpsolver")) {
            return lpSolver;
        }

        if (args.containsOption("bruteforce")) {
            return new BruteForceSolver(allSolutionIteratorFactory);
        }

        if (args.containsOption("validrow")) {
            return new BruteForceSolver(validRowIteratorFactory);
        }

        return lpSolver;
    }

    public boolean showHelp() {
        return args.containsOption("help");
    }

    public String helpText() {
        StringBuilder sb = new StringBuilder();
        sb.append("lp Use LP solver (default)\n")
            .append("bf Use Brute Force solver 1 (iterates over all possibilities)\n")
            .append("vr use Brute Force solver 2 (iterates over valid rows\n")
            .append("\n")
            .append("d Set Difficulty (0-4)")
            .append("s Set Size (4-9)")
            .append("\n")
            .append("r Use remote game repo from futoshiki.org (default)")
            .append("l Use local game repo");
        return sb.toString();
    }
}
