package view;

import controller.SlidePuzzleController;
import model.BlockState;
import model.IModelSubscriber;
import model.SlidePuzzleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements IModelSubscriber {
    private SlidePuzzleModel model;
    private SlidePuzzleController controller;

    private final static int BLOCK_ROWS = 3;
    private final static int BLOCK_COLUMNS = 3;

    public MainPanel(SlidePuzzleModel model_, SlidePuzzleController controller_) {
        model = model_;
        model.subscribe(this);
        controller = controller_;
        setLayout(new GridLayout(BLOCK_ROWS, BLOCK_COLUMNS, 2, 2));
        setDoubleBuffered(true);
        addBlocks();
    }

    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setVisible(false);
            String name = button.getText();
            controller.moveBlock(Integer.parseInt(name));
            addBlocks();
            if (controller.checkWin()) {
                JOptionPane.showMessageDialog(null, "YOU WIN!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                controller.startNewGame();
            }
            repaint();
        }
    }

    public void addBlocks() {
        this.removeAll();

        BlockState blockState = model.getBlockState();
        for (int i = 0; i < BLOCK_COLUMNS; i++) {
            for (int j = 0; j < BLOCK_ROWS; j++) {
                JButton block = new JButton(Integer.toString(blockState.blocksNumbers[i][j]));
                block.setFocusable(false);
                this.add(block);
                if (blockState.blocksNumbers[i][j] == 0) {
                    block.setVisible(false);
                }
                    block.addActionListener(new ClickListener());
            }
        }
        this.validate();
        this.repaint();
    }


    @Override
    public void modelChanged(SlidePuzzleModel model_) {
        model = model_;
    }


}
