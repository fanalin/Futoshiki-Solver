package de.fanalin.futoshiki.solver.lpsolver.constraints;

import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.lpsolver.ConstraintAdder;
import net.sf.javailp.Problem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite of all ConstraintAdders for a Futoshiki Game
 */
@Service
public class FutoshikiConstraintAdder implements ConstraintAdder {
    private List<ConstraintAdder> constraintAdders = new ArrayList<>();

    public FutoshikiConstraintAdder() {
        constraintAdders.add(new FieldValueConstraints());
        constraintAdders.add(new OneValueOnFieldConstraint());
        constraintAdders.add(new ValueOnceInColumnConstraint());
        constraintAdders.add(new ValueOnceInRowConstraint());
        constraintAdders.add(new FutoshikiInequalityConstraints());
    }

    @Override
    public void addConstraint(FutoshikiGame game, Problem problem) {
        for (ConstraintAdder adder : constraintAdders) {
            adder.addConstraint(game, problem);
        }
    }
}
