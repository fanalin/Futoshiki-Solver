package de.fanalin.futoshiki.solver;

/**
 * A GameSolution describes a (not necessarily valid) solution of a Futoshiki game
 */
public class GameSolution {

    private int defaultValue = 0;

    private final int size;
    private Field[][] values;

    public GameSolution(int size) {
        this.size = size;
        this.defaultValue = 0;
        initValueField();
    }

    public GameSolution(int size, int defaultValue) {
        this.size = size;
        this.defaultValue = defaultValue;
        initValueField();
    }

    public int getValue(int x, int y) {
        return values[x][y].getValue();
    }

    public int getValue(final Coord coord) {
        return getValue(coord.getX(), coord.getY());
    }

    public Field getField(final Coord coord) {
        return values[coord.getX()][coord.getY()];
    }

    int getSize() {
        return size;
    }


    public void print() {
        int size = getSize();
        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                int value = getValue(x, y);
                System.out.print(value != 0 ? value : "-");
            }
            System.out.println("");
        }
    }

    private void initValueField() {
        values = new Field[size][];

        for (int i = 0; i < size; ++i) {
            values[i] = new Field[size];
            for (int j = 0; j < size; ++j) {
                Coord coord = new Coord(i, j);
                values[i][j] = new Field(coord, defaultValue);
            }
        }
    }
}
