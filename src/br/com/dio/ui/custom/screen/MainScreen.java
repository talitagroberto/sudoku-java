package br.com.dio.ui.custom.screen;

import br.com.dio.model.Space;
import br.com.dio.service.BoardService;
import br.com.dio.service.NotifierService;
import br.com.dio.ui.custom.button.CheckGameStatusButton;
import br.com.dio.ui.custom.button.FinishGameButton;
import br.com.dio.ui.custom.button.InstructionsButton;
import br.com.dio.ui.custom.button.ResetButton;
import br.com.dio.ui.custom.frame.MainFrame;
import br.com.dio.ui.custom.input.NumberText;
import br.com.dio.ui.custom.panel.MainPanel;
import br.com.dio.ui.custom.panel.SudokuSector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.dio.service.EventEnum.CLEAR_SPACE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainScreen {

    private static final Dimension SCREEN_DIMENSION = new Dimension(600, 600);

    private final BoardService boardService;
    private final NotifierService notifierService;

    private JButton checkGameStatusButton;
    private JButton finishGameButton;
    private JButton resetButton;

    public MainScreen(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(SCREEN_DIMENSION);
        JFrame mainFrame = new MainFrame(SCREEN_DIMENSION, mainPanel);

        for (int row = 0; row < 9; row += 3) {
            var endRow = row + 2;

            for (int column = 0; column < 9; column += 3) {
                var endColumn = column + 2;
                var spaces = getSpacesFromSector(
                        boardService.getSpaces(),
                        column,
                        endColumn,
                        row,
                        endRow
                );

                JPanel sector = generateSection(spaces);
                mainPanel.add(sector);
            }
        }

        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        addInstructionsButton(mainPanel);

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Space> getSpacesFromSector(
            final List<List<Space>> spaces,
            final int initialColumn,
            final int finalColumn,
            final int initialRow,
            final int finalRow
    ) {
        List<Space> sectorSpaces = new ArrayList<>();

        for (int row = initialRow; row <= finalRow; row++) {
            for (int column = initialColumn; column <= finalColumn; column++) {
                sectorSpaces.add(spaces.get(column).get(row));
            }
        }

        return sectorSpaces;
    }

    private JPanel generateSection(final List<Space> spaces) {
        List<NumberText> fields = new ArrayList<>(
                spaces.stream().map(NumberText::new).toList()
        );

        fields.forEach(field ->
                notifierService.subscribe(CLEAR_SPACE, field)
        );

        return new SudokuSector(fields);
    }

    private void addFinishGameButton(final JPanel mainPanel) {
        finishGameButton = new FinishGameButton(event -> {
            if (boardService.gameIsFinished()) {
                showMessageDialog(
                        null,
                        "Parabéns! Você concluiu o jogo corretamente.",
                        "Jogo concluído",
                        JOptionPane.INFORMATION_MESSAGE
                );

                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            } else {
                showMessageDialog(
                        null,
                        "O jogo possui alguma inconsistência. Revise os números e tente novamente.",
                        "Não foi possível finalizar",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatusButton(final JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatusButton(event -> {
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();

            var message = switch (gameStatus) {
                case NON_STARTED -> "O jogo ainda não foi iniciado.";
                case INCOMPLETE -> hasErrors
                        ? "O jogo está incompleto e contém erros."
                        : "O jogo está incompleto e não contém erros.";
                case COMPLETE -> hasErrors
                        ? "O jogo está completo, mas contém erros."
                        : "O jogo está completo e não contém erros.";
            };

            showMessageDialog(
                    null,
                    message,
                    "Status do jogo",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(final JPanel mainPanel) {
        resetButton = new ResetButton(event -> {
            var dialogResult = showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo?",
                    "Reiniciar jogo",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );

            if (dialogResult == JOptionPane.YES_OPTION) {
                boardService.reset();
                notifierService.notify(CLEAR_SPACE);
            }
        });

        mainPanel.add(resetButton);
    }

    private void addInstructionsButton(final JPanel mainPanel) {
        var instructionsButton = new InstructionsButton(event -> {
            var instructions = """
                    Objetivo:
                    Preencha as células vazias com números de 1 a 9.

                    Regras:
                    - Não repita números na mesma linha.
                    - Não repita números na mesma coluna.
                    - Não repita números no mesmo bloco 3x3.
                    - Os números iniciais do tabuleiro não podem ser alterados.

                    Utilize os botões para verificar o status,
                    reiniciar ou finalizar o jogo.
                    """;

            showMessageDialog(
                    null,
                    instructions,
                    "Como jogar",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        mainPanel.add(instructionsButton);
    }

}