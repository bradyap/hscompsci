import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
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

        //add game buttons
        JButton buttonOne = new JButton("Play");
        buttonOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonOne.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String p1;
                    String p2;
                    //if text boxes are filled, use those values, if not default to p1 and p2
                    if(!textOne.getText().equals("")) p1 = textOne.getText();
                    else p1 = "Player One";
                    if(!textTwo.getText().equals("")) p2 = textTwo.getText();
                    else p2 = "Player Two";
                    Game game = new Game(p1, p2);
                    BoardGui boardGui = new BoardGui(game, false);
                }
            }
        );

        JButton buttonTwo = new JButton("Single Player");
        buttonTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonTwo.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Game game = new Game("You", "Computer");
                    BoardGui boardGui = new BoardGui(game, true);
                }
            }
        );

        //add exit button
        JButton buttonThree = new JButton("Exit");
        buttonThree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonThree.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }
        );

        buttonPanel.add(buttonOne);
        buttonPanel.add(buttonTwo);
        buttonPanel.add(buttonThree);

        //assemble and start gui
        panel.add(namePanel);
        panel.add(buttonPanel);
        frame.add(panel);
        frame.setVisible(true);
    }
}