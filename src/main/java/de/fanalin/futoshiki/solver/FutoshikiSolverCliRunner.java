package de.fanalin.futoshiki.solver;

import de.fanalin.futoshiki.solver.Validator.SolutionValidator;
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
    private CliArgumentParser cliArgumentParser;


    private Logger log = LoggerFactory.getLogger(FutoshikiSolverCliRunner.class);


    public static void main(String args[]) {
        SpringApplication.run(FutoshikiSolverCliRunner.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("Starting Futoshiki Solver");
        FutoshikiGameProperties props = cliArgumentParser.getGameProperties(strings);

        FutoshikiRepository repo = cliArgumentParser.getFutoshikoGameRepository(strings);
        FutoshikiGame game = repo.get(props);

        if (game == null) {
            log.info("No game found!");
            return;
        }

        FutoshikiGameSolverFactory solverFactory = cliArgumentParser.getSolverFromCliArguments(strings);
        FutoshikiGameSolver solver = solverFactory.get();

        GameSolution solution = solver.solve(game);
        solution.print();

        SolutionValidator validator = new SolutionValidator(game);
        log.info("checking if solution is valid: " + validator.isValid(solution));
    }
}

