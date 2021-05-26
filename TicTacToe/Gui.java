import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    public boolean visible = true;

    public Gui(Game game) {
        //initialize frame and panel
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(3, 3));

        //create buttons
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                final JButton button = new JButton();
                int row = i;
                int col = j;
                String name = row + "," + col;
                button.setName(name);
                button.setText("");
                button.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            button.setText(Character.toString(game.getSymbol()));
                            System.out.println(name);
                            char ret = game.turn(row, col);
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
                            game.printGame();
                        }
                    });
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(button);
            }
        }

        //assemble and start gui
        frame.add(panel);
        frame.setVisible(true);
    }
}