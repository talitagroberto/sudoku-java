package br.com.dio.ui.custom.button;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class InstructionsButton extends JButton {

    public InstructionsButton(final ActionListener actionListener) {
        super("Como jogar");
        this.addActionListener(actionListener);
    }

}