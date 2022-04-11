
/*
CLASS: Game
DESCRIPTION:  Game is mostly in the paint method.
NOTE: This class is the metaphorical "main method" of your program, 
    it is your control center.
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.ArrayList;

public class Game extends Canvas implements KeyListener {
    protected boolean on = true;
    protected int width, height;
    protected Image buffer;

    // ! game settings
    final static int INIT_ASTEROIDS = 15;
    final static int ASTEROID_SPEED = 3;
    final static int SHIP_SPEED = 8;
    final static int ROTATION_SPEED = 5;
    final static int MAX_LIVES = 3;
    final static int FREE_LIFE_THRESHOLD = 3;
    final static int SCREEN_WIDTH = 1000;
    final static int SCREEN_HEIGHT = 750;

    private Ship ship;
    private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    private int collisionCt = 0;
    private int newCollisionWith = -1;
    private BufferStrategy strategy;

    // * keyboard input
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;
    private boolean space = false;

    public Game() {
        JFrame frame = new JFrame("ASTEROIDS BY ATARI");
        frame.add(this);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.getContentPane().setBackground(Color.BLACK);

        frame.setVisible(true);
        frame.setResizable(false);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Create the ship - these points make a triangular ship shape
        Point[] shipShape = { new Point(10, 10), new Point(0, 25), new Point(0, 35), new Point(20, 35), new Point(20, 25) };
        Point center = new Point(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        ship = new Ship(shipShape, center);

        // Create some asteroids
        Point[] asteroidShape;
        for (int i = 0; i < INIT_ASTEROIDS; i++) {
            // Set up asteroid shapes - four different shapes randomly chosen.
            int numPts = (int) (Math.random() * 4) + 5;
            if (numPts == 5) {
                Point[] tempShape = { new Point(10, 10), new Point(5, 15), new Point(12, 20), new Point(19, 14), new Point(12, 13) };
                asteroidShape = tempShape;
            } else if (numPts == 6) {
                Point[] tempShape = { new Point(10, 10), new Point(22, 4), new Point(24, 18), new Point(33, 23), new Point(7, 38), new Point(15, 25) };
                asteroidShape = tempShape;
            } else if (numPts == 7) {
                Point[] tempShape = { new Point(3, 17), new Point(12, 2), new Point(22, 4), new Point(18, 13), new Point(25, 15), new Point(11, 31), new Point(12, 18) };
                asteroidShape = tempShape;
            } else {
                Point[] tempShape = { new Point(15, 5), new Point(24, 15), new Point(35, 10), new Point(28, 27), new Point(23, 45), new Point(17, 32), new Point(6, 30),
                        new Point(14, 21) };
                asteroidShape = tempShape;
            }

            // Start the asteroid at a random location, but not within 100 of the center
            int x = ((int) (Math.random() * (SCREEN_WIDTH / 2 - 109)) + 10) + ((SCREEN_WIDTH / 2 + 110) * ((int) (Math.random() * 2)));
            int y = ((int) (Math.random() * (SCREEN_HEIGHT / 2 - 109)) + 10) + ((SCREEN_HEIGHT / 2 + 110) * ((int) (Math.random() * 2)));

            asteroids.add(new Asteroid(asteroidShape, x, y));
        }

        render();
        addKeyListener(this);

        int delay = 8; // game framerate
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                render();
            }
        };
        new Timer(delay, taskPerformer).start();
        requestFocusInWindow();
    }

    public void paint(Graphics g) {
        if (ship != null) {
            ship.paint(g);
        }

        for (Asteroid a : asteroids) {

            ArrayList<Missile> missiles = ship.getMissiles();
            for (Missile m : missiles) {
                if (m.collides(a)) {
                    missiles.remove(m);
                    asteroids.remove(a);
                    return;
                }
            }

            //randomly pick new rotation direction
            a.rotate(Math.random() > 0.5 ? ROTATION_SPEED : -ROTATION_SPEED);

            a.move();
            a.paint(g);
        }

        if (up) {
            ship.move(SHIP_SPEED);
        }

        if (left) {
            ship.rotate(-ROTATION_SPEED);
        } else if (right) {
            ship.rotate(ROTATION_SPEED);
        }

    }

    public void render() {
        // buffer and display frames
        int bufferSize = 2;
        createBufferStrategy(bufferSize);
        strategy = getBufferStrategy();
        Graphics g = null;

        do {
            try {
                g = strategy.getDrawGraphics();
            } finally {
                paint(g);
            }
            strategy.show();
            g.dispose();
        } while (strategy.contentsLost());
        Toolkit.getDefaultToolkit().sync();
    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stubd
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
        }
    }
}