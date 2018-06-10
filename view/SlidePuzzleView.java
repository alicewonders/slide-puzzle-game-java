package view;

import controller.SlidePuzzleController;
import model.SlidePuzzleModel;

import javax.swing.*;

public class SlidePuzzleView {
    public void createUI(SlidePuzzleModel model_, SlidePuzzleController controller_) {
        JFrame obj = new JFrame();
        MainPanel mainPanel = new MainPanel(model_, controller_);
        obj.setSize(400, 400);
        obj.setTitle("SlidePuzzle 0.1");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(mainPanel);
    }
}
