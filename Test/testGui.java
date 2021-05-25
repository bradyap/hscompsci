import javax.swing.*;

public class testGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button = new JButton("Test button.");
        frame.getContentPane().add(button);
        frame.setVisible(true);
    }
}
