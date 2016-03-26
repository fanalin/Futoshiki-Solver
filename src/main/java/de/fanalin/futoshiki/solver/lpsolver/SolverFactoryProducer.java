package de.fanalin.futoshiki.solver.lpsolver;

import net.sf.javailp.Solver;
import net.sf.javailp.SolverFactory;
import net.sf.javailp.SolverFactoryGLPK;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Produces a SolverFactory
 */
@Configuration
public class SolverFactoryProducer {

    @Bean
    public SolverFactory create() {
        SolverFactory factory = new SolverFactoryGLPK();
        factory.setParameter(Solver.VERBOSE, 0);
        factory.setParameter(Solver.TIMEOUT, 100); // set timeout to 100 seconds

        return factory;
    }
}
