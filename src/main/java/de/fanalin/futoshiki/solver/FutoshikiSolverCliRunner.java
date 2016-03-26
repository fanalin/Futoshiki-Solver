package de.fanalin.futoshiki.solver;

import de.fanalin.futoshiki.solver.Validator.SolutionValidator;
import de.fanalin.futoshiki.solver.gamerepository.FutoshikiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


@SpringBootApplication
public class FutoshikiSolverCliRunner implements CommandLineRunner {

    @Resource(name = "futoshikiFromFileReader")
    private FutoshikiRepository repo;

    @Autowired
    private FutoshikiGameSolver solver;

    private static final Logger log = LoggerFactory.getLogger(FutoshikiSolverCliRunner.class);

    public static void main(String args[]) {
        SpringApplication.run(FutoshikiSolverCliRunner.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("Starting Futoshiki Solver");
        FutoshikiGameProperties props = new FutoshikiGameProperties(1, 5);
        FutoshikiGame game = repo.get(props);

        /*FutoshikiGameSolver solver = new BruteForceSolver(
            new AllSolutionIterator(
                props.getSize(),
                new GameSolution(props.getSize()),
                Coord.getCoordList(props.getSize())
            )
        );*/

        /*
        RowPermutationIteratorFactory factory = new RowPermutationIteratorFactory(
            RowPermutationIteratorFactory.createValidNumbersList(props.getSize())
        );
        FutoshikiGameSolver solver = new BruteForceSolver(
            new ValidRowIterator(
                props.getSize(),
                ValidRowIterator.getIteratorList(factory, props.getSize()),
                factory
            )
        );*/

        GameSolution solution = solver.solve(game);
        solution.print();

        SolutionValidator validator = new SolutionValidator(game);
        log.info("checking if solution is valid: " + validator.isValid(solution));
    }
}
