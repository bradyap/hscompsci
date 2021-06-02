import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardGui {
    public boolean visible = true;
    public JButton[][] buttons = new JButton[3][3];
    protected Game game;
    protected JFrame frame;

    public BoardGui(Game inGame) {
        game = inGame;

        //initialize frame and panel
        frame = new JFrame(game.getPlayerOne() + " vs. " + game.getPlayerTwo());
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

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton)e.getSource();
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (buttons[i][j].equals(source)) {
                        JButton button = buttons[i][j];
                        char ret = game.turn(i, j);
                        if(ret != 'e') {
                            button.setText(Character.toString(game.getSymbol()));
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
                }
            }
        }
    }
}