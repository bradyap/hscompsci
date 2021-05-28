import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardGui {
    public boolean visible = true;
    public JButton[][] buttons = new JButton[3][3]

    public BoardGui(Game game) {
        //initialize frame and panel
        JFrame frame = new JFrame(game.getPlayerOne() + " vs. " + game.getPlayerTwo());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(3, 3));

        //create buttons
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                //BufferedImage buttonImage = ImageIO.read(new File("images/dash.jpg"));
                //final JButton button = new JButton(new ImageIcon(buttonImage));
                final JButton button = new JButton();
                int row = i;
                int col = j;
                String name = row + "," + col;
                button.setName(name);
                button.setFont(new Font("Arial", Font.BOLD, 120));
                button.setText("");
                button.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            button.setText(Character.toString(game.getSymbol()));
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

    private class ButtonListener implements ActionListener() {
        public void ActionPerformed(ActionEvent e) {
            JButton source = (Jbutton)e.getSource();
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (buttons[i][j].equals(source)) System.out.println("working")
                }
            }
        }
    }
}