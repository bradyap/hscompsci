import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws Exception {
        //initialize frame and panel
        JFrame frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 150);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //name inputs
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));

        JPanel nameOne = new JPanel();
        JLabel labelOne = new JLabel("Player One:");
        JTextField textOne = new JTextField();
        textOne.setColumns(10);
        nameOne.add(labelOne);
        nameOne.add(textOne);

        JPanel nameTwo = new JPanel();
        JLabel labelTwo = new JLabel("Player Two:");
        JTextField textTwo = new JTextField();
        textTwo.setColumns(10);
        nameTwo.add(labelTwo);
        nameTwo.add(textTwo);

        namePanel.add(nameOne);
        namePanel.add(nameTwo);

        //add buttons
        JPanel buttonPanel = new JPanel();

        //add game button
        JButton buttonOne = new JButton("Play");
        buttonOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonOne.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Game game = new Game(textOne.getText(), textTwo.getText());
                    BoardGui boardGui = new BoardGui(game);
                }
            }
        );

        //add exit button
        JButton buttonTwo = new JButton("Exit");
        buttonTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonTwo.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }
        );

        buttonPanel.add(buttonOne);
        buttonPanel.add(buttonTwo);

        //assemble and start gui
        panel.add(namePanel);
        panel.add(buttonPanel);

        frame.add(panel);
        //frame.add(buttonPanel);
        frame.setVisible(true);
    }
}
