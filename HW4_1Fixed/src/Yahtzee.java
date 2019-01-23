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

    public Yahtzee(JLabel label) {
        _label = label;

    }

    public JFrame getframe() {
        return frame;
    }

    public ImageIcon dicepic(int a) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL PIC = getClass().getResource("/resources/" + a + ".png");
        ImageIcon DiceIcon = new ImageIcon(PIC);
        Image image = DiceIcon.getImage();
        Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        DiceIcon = new ImageIcon(newimg);
        return DiceIcon;
    }


    public void run() {

        Random setter = new Random();
        for (int i = 0; i < setter.nextInt(100); i++) {
            sum = 0;
            int a = rnd.nextInt(5);
            _label.setIcon(dicepic(a));
            sum = a + 1;
            ThreadTest.getFrame().revalidate();
            System.out.println(sum);

        }


    }

    public int getSum() {
        return sum;
    }

}


