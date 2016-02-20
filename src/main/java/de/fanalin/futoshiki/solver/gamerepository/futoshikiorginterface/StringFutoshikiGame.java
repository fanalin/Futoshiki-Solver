package de.fanalin.futoshiki.solver.gamerepository.futoshikiorginterface;

import de.fanalin.futoshiki.solver.Coord;
import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.FutoshikiGameProperties;

/**
 * Created by matti on 15.09.2015.
 */
public class StringFutoshikiGame implements FutoshikiGame {

    private final String gameContent;

    private final FutoshikiGameProperties props;

    @Override public FutoshikiGameProperties getProps() {
        return props;
    }

    public StringFutoshikiGame(final String gameContent, final FutoshikiGameProperties props) {
        this.gameContent = gameContent;
        this.props = props;
    }

    @Override public int getValue(final Coord coord) {
        Character c = gameContent.charAt(getContentStringIndex(coord));
        int value = c.equals('.') ? 0 : Integer.valueOf(c.toString()).intValue();

        if (value == 0) {
            return NO_VALUE;
        }

        return value;
    }

    private int getContentStringIndex(final Coord coord) {
        int row = 2* coord.getY();
        int column = 2*coord.getX();

        return row * getRowLength() + column;
    }

    private int getRowLength() {
        return 2*props.getSize() -  1;
    }

    @Override public int getEquation(final Coord coord1, final Coord coord2) {
        if (coord1.isLowerNeighborOf(coord2)) {
            // we get the equation sign for coord2 to coord1 and then inverse
            return changeInequality(getSignForLowerNeighbor(coord2));
        } else if (coord2.isLowerNeighborOf(coord1)) {
            return getSignForLowerNeighbor(coord1);
        } else if (coord1.isRightNeighborOf(coord2)) {
            // we get the equation sign for coord2 to coord1 and then inverse
            return changeInequality(getSignForRightNeighbor(coord2));
        } else if (coord2.isRightNeighborOf(coord1)) {
            return getSignForRightNeighbor(coord1);
        }

        return NO_EQUATION;
    }

    private int getSignForRightNeighbor(final Coord coord) {
        int index = getContentStringIndex(coord) + 1;
        Character c = gameContent.charAt(index);
        if (c.equals('(')) {
            return SMALLER_THAN;
        } else if (c.equals(')')) {
            return GREATER_THAN;
        }

        return NO_EQUATION;
    }

    private int getSignForLowerNeighbor(final Coord coord) {
        int index = getContentStringIndex(coord) + getRowLength();
        Character c = gameContent.charAt(index);
        if (c.equals('^')) {
            return SMALLER_THAN;
        } else if (c.equals('v')) {
            return GREATER_THAN;
        }

        return NO_EQUATION;
    }

    private int changeInequality(int eq) {
        if (eq == SMALLER_THAN) {
            return GREATER_THAN;
        } else if (eq == GREATER_THAN) {
            return SMALLER_THAN;
        }

        return eq;
    }
}
