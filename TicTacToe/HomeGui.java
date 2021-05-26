import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeGui {

    public HomeGui() {
        //initialize frame and panel
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //add buttons
        JButton buttonOne = new JButton("One Player");
        buttonOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonOne.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("This will open a single player game later. Alpha.");
                }
            }
        );
        JButton buttonTwo = new JButton("Two Player");
        buttonTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonTwo.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Game game = new Game("p1", "p2");
                    BoardGui boardGui = new BoardGui(game);
                }
            }
        );

        panel.add(buttonOne);
        panel.add(buttonTwo);

        //assemble and start gui
        frame.add(panel);
        frame.setVisible(true);
    }
}