package de.fanalin.futoshiki.solver;

/**
 * Created by matti on 15.09.2015.
 */
public class FutoshikiGameProperties {
    private int difficulty;

    private int size;

    public FutoshikiGameProperties(int difficulty, int size) {
        this.difficulty = difficulty;
        this.size = size;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getSize() {
        return size;
    }
}
