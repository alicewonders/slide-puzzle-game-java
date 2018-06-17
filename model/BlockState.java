package model;

import java.util.Random;

public class BlockState {
    private final static int BLOCK_ROWS = 3;
    private final static int BLOCK_COLUMNS = 3;

    public int[][] blocksNumbers;

    public BlockState() {
        generateNumbers();
    }

    public void generateNumbers() {
        blocksNumbers = new int[BLOCK_ROWS][BLOCK_COLUMNS];
        int[] invariants = new int[BLOCK_ROWS * BLOCK_COLUMNS];

        Random random = new Random();

        do {
            for (int i = 0; i < BLOCK_COLUMNS; i++) {
                for (int j = 0; j < BLOCK_ROWS; j++) {
                    blocksNumbers[i][j] = 0;
                    invariants[i * BLOCK_COLUMNS + j] = 0;
                }
            }

            for (int i = 1; i < BLOCK_ROWS * BLOCK_COLUMNS; i++) {
                int k, l;
                do {
                    k = random.nextInt(BLOCK_COLUMNS);
                    l = random.nextInt(BLOCK_ROWS);
                }
                while (blocksNumbers[k][l] != 0);
                blocksNumbers[k][l] = i;
                invariants[k * BLOCK_COLUMNS + l] = i;
            }
        }
        while (!canBeSolved(invariants));
    }

    private boolean canBeSolved(int[] invariants) {
        int sum = 0;
        for (int i = 0; i < BLOCK_COLUMNS * BLOCK_ROWS; i++) {
            if (invariants[i] == 0) {
                sum += i / BLOCK_COLUMNS;
                continue;
            }

            for (int j = i + 1; j < BLOCK_ROWS * BLOCK_COLUMNS; j++) {
                if (invariants[j] < invariants[i])
                    sum ++;
            }
        }
        return sum % 2 == 0;
    }

    public void moveBlock(int num) {
        int i = 0, j = 0;
        for (int k = 0; k < BLOCK_COLUMNS; k++) {
            for (int l = 0; l < BLOCK_ROWS; l++) {
                if (blocksNumbers[k][l] == num) {
                    i = k;
                    j = l;
                }
            }
        }
        if (i > 0) {
            if (blocksNumbers[i - 1][j] == 0) {
                blocksNumbers[i - 1][j] = num;
                blocksNumbers[i][j] = 0;
            }
        }
        if (i < BLOCK_COLUMNS - 1) {
            if (blocksNumbers[i + 1][j] == 0) {
                blocksNumbers[i + 1][j] = num;
                blocksNumbers[i][j] = 0;
            }
        }
        if (j > 0) {
            if (blocksNumbers[i][j - 1] == 0) {
                blocksNumbers[i][j - 1] = num;
                blocksNumbers[i][j] = 0;
            }
        }
        if (j < BLOCK_ROWS - 1) {
            if (blocksNumbers[i][j + 1] == 0) {
                blocksNumbers[i][j + 1] = num;
                blocksNumbers[i][j] = 0;
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof BlockState)) return false;
        BlockState otherMyClass = (BlockState) other;
        return otherMyClass.blocksNumbers == this.blocksNumbers;
    }
}
