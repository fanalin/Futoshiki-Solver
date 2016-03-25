package de.fanalin.futoshiki.solver;

import de.fanalin.futoshiki.solver.bruteforcesolver.AllSolutionIterator;
import de.fanalin.futoshiki.solver.bruteforcesolver.BruteForceSolver;
import de.fanalin.futoshiki.solver.bruteforcesolver.RowPermutationIteratorFactory;
import de.fanalin.futoshiki.solver.bruteforcesolver.ValidRowIterator;
import de.fanalin.futoshiki.solver.gamerepository.FutoshikiRepository;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class FutoshikiSolverCliRunner implements CommandLineRunner {

    @Resource(name = "futoshikiFromFileReader")
    private FutoshikiRepository repo;

    private static final Logger log = LoggerFactory.getLogger(FutoshikiSolverCliRunner.class);

    public static void main(String args[]) {
        SpringApplication.run(FutoshikiSolverCliRunner.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("Starting Futoshiki Solver");
        FutoshikiGameProperties props = new FutoshikiGameProperties(1, 4);
        FutoshikiGame game = repo.get(props);

        /*FutoshikiGameSolver solver = new BruteForceSolver(
            new AllSolutionIterator(
                props.getSize(),
                new GameSolution(props.getSize()),
                Coord.getCoordList(props.getSize())
            )
        );*/

        RowPermutationIteratorFactory factory = new RowPermutationIteratorFactory(
            RowPermutationIteratorFactory.createValidNumbersList(props.getSize())
        );
        FutoshikiGameSolver solver = new BruteForceSolver(
            new ValidRowIterator(
                props.getSize(),
                ValidRowIterator.getIteratorList(factory, props.getSize()),
                factory
            )
        );

        solver.solve(game);
    }


    @Bean
    public HttpClient getHttpClient() {
        return HttpClients.createDefault();
    }
}
