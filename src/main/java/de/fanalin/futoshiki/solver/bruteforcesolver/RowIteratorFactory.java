package de.fanalin.futoshiki.solver.bruteforcesolver;

import java.util.Iterator;
import java.util.List;

/**
 * Created by matti on 20.02.2016.
 */
public interface RowIteratorFactory {
    Iterator<List<Integer>> get();
}
