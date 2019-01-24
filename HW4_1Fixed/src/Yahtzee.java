/**
 * This class models a Yahtzee dice roll game
 * by providing a run function that will be called
 * in the Threadtet class and sets pictures of rolling
 * dice.
 */

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Yahtzee implements Runnable {
    private JLabel _label;
    private JFrame _frame = new JFrame();
    private JFrame frame;
    private Random rnd = new Random();
    private int sum;


    public Yahtzee() {
        frame = _frame;
    }

    /**
     * Initialize the Yahtzee object given a jlabel
     * @param label label that the die will appear on
     */
    public Yahtzee(JLabel label) {
        _label = label;

    }


    /**
     * This function generates a dice picture based off the given input.
     * The pictures are named 0, 1, 2,3,4,5 in the resources file
     * @param a The number of picture that will be used
     * @return the picture of the dice
     */
    public ImageIcon dicepic(int a) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL PIC = getClass().getResource("/resources/" + a + ".png");
        ImageIcon DiceIcon = new ImageIcon(PIC);
        Image image = DiceIcon.getImage();
        Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        DiceIcon = new ImageIcon(newimg);
        return DiceIcon;
    }

    /**
     * This function tells the thread to run for a random amount of time
     * keeps truck of the current amount for each, and changed the picture
     * of the dice each time it runs through.
     * @author Griffen Marler
     * @version 1.00, 23 January 2019
     */
    public void run() {

        Random setter = new Random();
        for (int i = 0; i < setter.nextInt(100); i++) {
            sum = 0;
            int a = rnd.nextInt(5);
            _label.setIcon(dicepic(a));
            sum = a + 1;
            ThreadTest.getFrame().revalidate();

        }


    }

    /**
     * Get the sum
     * @return sum
     */
    public int getSum() {
        return sum;
    }

}


