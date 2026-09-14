package br.com.dio.ui.custom.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

public class MainFrame extends JFrame {

    public MainFrame(final Dimension dimension, final JPanel mainPanel) {
        super("Sudoku Java - Talita Gonçalves");

        mainPanel.setPreferredSize(dimension);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}