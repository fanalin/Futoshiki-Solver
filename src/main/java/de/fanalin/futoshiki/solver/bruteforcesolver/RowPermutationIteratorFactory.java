package de.fanalin.futoshiki.solver.bruteforcesolver;

import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by matti on 20.02.2016.
 */
public class RowPermutationIteratorFactory implements RowIteratorFactory {
    private final List<Integer> validNumbers;

    public RowPermutationIteratorFactory(final List<Integer> validNumbers) {
        this.validNumbers = validNumbers;
    }

    @Override
    public Iterator<List<Integer>> get() {
        return new PermutationIterator<>(validNumbers);
    }

    public static List<Integer> createValidNumbersList(int size) {
        List<Integer> ret = new ArrayList<>(size);
        for (int i = 1; i <= size; ++i) {
            ret.add(i);
        }
        return ret;
    }
}
