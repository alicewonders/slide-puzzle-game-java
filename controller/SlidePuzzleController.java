package controller;

import model.BlockState;
import model.SlidePuzzleModel;
import view.SlidePuzzleView;

public class SlidePuzzleController {
    private static SlidePuzzleModel model = new SlidePuzzleModel(new BlockState());
    private static SlidePuzzleView view = new SlidePuzzleView();

    private final static int BLOCK_ROWS = 3;
    private final static int BLOCK_COLUMNS = 3;

    public void start() {
        view.createUI(model, this);
    }

    public void startNewGame() {
        BlockState blockState = model.getBlockState();
        blockState.generateNumbers();
    }

    public void moveBlock(int num) {
        model.nextStep(num);
    }

    public boolean checkWin() {
        boolean status = true;
        BlockState bs = model.getBlockState();
        for (int i = 0; i < BLOCK_COLUMNS; i++) {
            for (int j = 0; j < BLOCK_ROWS; j++) {
                if (i == BLOCK_COLUMNS - 1 && j > BLOCK_ROWS - 2)
                    break;
                if (bs.blocksNumbers[i][j] != i * BLOCK_COLUMNS + j + 1) {
                    status = false;
                }
            }
        }
        return status;
    }


}
