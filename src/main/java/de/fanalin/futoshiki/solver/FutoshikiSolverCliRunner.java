package de.fanalin.futoshiki.solver;

import de.fanalin.futoshiki.solver.Validator.SolutionValidator;
import de.fanalin.futoshiki.solver.cli.CliArguments;
import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;
import de.fanalin.futoshiki.solver.game.FutoshikiGameSolver;
import de.fanalin.futoshiki.solver.game.GameSolution;
import de.fanalin.futoshiki.solver.gamerepository.FutoshikiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FutoshikiSolverCliRunner implements CommandLineRunner {

    @Autowired
    private CliArguments args;

    @Autowired
    private FutoshikiRepository gameRepo;

    private Logger log = LoggerFactory.getLogger(FutoshikiSolverCliRunner.class);

    public static void main(String args[]) {
        SpringApplication.run(FutoshikiSolverCliRunner.class);
    }

    @Override
    public void run(String[] strings) throws Exception {
        log.info("Starting Futoshiki Solver");

        if (args.showHelp()) {
            System.out.println(args.helpText());
            return;
        }

        log.info("parsing CLI arguments");

        FutoshikiGameProperties props = args.getGameProperties();

        log.info("requesting game from repository");
        FutoshikiGame game = gameRepo.get(props);
        if (game == null) {
            log.info("No game found!");
            return;
        }

        log.info("solving game");
        FutoshikiGameSolver solver = args.getSolver();
        GameSolution solution = solver.solve(game);

        log.info("game solved");
        if (solution == null) {
            log.error("No solution found!");
            return;
        }

        solution.print();

        SolutionValidator validator = new SolutionValidator(game);
        log.info("checking if solution is valid: " + validator.isValid(solution));
    }
}

