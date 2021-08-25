import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardGui {
    //make variables
    public boolean visible = true;
    protected JButton[][] buttons = new JButton[3][3];
    protected Game game;
    protected Computer computer;
    protected JFrame frame;
    protected Boolean isSinglePlayer;

    public BoardGui(Game inGame, Boolean singlePLayer) {
        game = inGame;
        isSinglePlayer = singlePLayer;
        computer = new Computer();

        //initialize frame and panel
        frame = new JFrame(game.getPlayerOne() + " vs. " + game.getPlayerTwo() + "!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(3, 3));

        //create buttons
        ButtonListener buttonListener = new ButtonListener();
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                final JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 120));
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                button.setText("");
                button.addActionListener(buttonListener);
                buttons[i][j] = button;
                panel.add(button);
            }
        }

        //assemble and start gui
        frame.add(panel);
        frame.setVisible(true);
    }

    //handles result from game's turn method and updates buttons accordingly
    private void checkHandler(char ret, JButton button) {
        if(ret != 'e') {
            button.setText(Character.toString(game.getSymbol()));
            game.switchPlayer();
            if(ret == 'x') {
                JOptionPane.showMessageDialog(null, game.getPlayerOne() + " is the winner!");
                visible = false;
                frame.setVisible(false);
            }
            else if(ret == 'o') {
                JOptionPane.showMessageDialog(null, game.getPlayerTwo() + " is the winner!");
                visible = false;
                frame.setVisible(false);
            } 
            else if(ret == 't') {
                JOptionPane.showMessageDialog(null, "It's a tie!");
                visible = false;
                frame.setVisible(false);
            }
        }
    }

    //action listener
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton)e.getSource();
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (buttons[i][j].equals(source)) {
                        JButton button = buttons[i][j];
                        //if playing against the computer
                        if(isSinglePlayer) {
                            if(game.getSymbol() == 'x') {
                                checkHandler(game.turn(i, j), button);
                                int[] ret = computer.run(game.getBoard());
                                JButton computerButton = buttons[ret[0]][ret[1]];
                                checkHandler(game.turn(ret[0], ret[1]), computerButton);
                            }
                        //if there are two players
                        } else checkHandler(game.turn(i, j), button);
                    }
                }
            }
        }
    }
}