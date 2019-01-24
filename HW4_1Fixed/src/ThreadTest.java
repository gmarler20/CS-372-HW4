/**
 * This class is the thread manager for the Yahtzee game.
 * @author Griffen Marler
 * @version 1.00, 23 January 2019
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ThreadTest  {
    private JLabel Sumtrack = new JLabel();
    private static JFrame frame = new JFrame();
    private int finalsum;

    /**
     * Initialize the threadtest given no parameters.
     * Create 5 threads total and pass in a new JLabel to
     * each thread.
     */
    public ThreadTest() {

        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        frame.add(panel);
        JButton btn = new JButton();
        btn.setText("Roll");
        JButton sumbtn = new JButton();

        Yahtzee[] ys = new Yahtzee[5];
        for (int i = 0; i < ys.length; i++) {
            JLabel l = new JLabel();
            ys[i] = new Yahtzee(l);
            l.setIcon(dicepic(i));
            panel.add(l);
        }
        panel.add(btn);
        panel.add(sumbtn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread[] ts = new Thread[5];
                for (int j = 0; j < ys.length; j++) {
                    ts[j] = new Thread(ys[j]);
                    ts[j].start();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ;
                    }
                }

              for (int i = 0; i < ts.length; i++) {
                    try {

                        ts[i].join();
                    } catch (InterruptedException exa) {
                        ;
                    }
                    frame.revalidate();
                }
                for(int k = 0; k < ys.length; k++) {
                    finalsum = finalsum + ys[k].getSum();
                }
                sumbtn.setText(Integer.toString(finalsum));
                finalsum = 0;
            }


        });
        panel.setVisible(true);
        frame.pack();
        frame.setVisible(true);
        frame.revalidate();


    }

    /**
     * This function generates a dice picture based off the given input.
     * The pictures are named 0, 1, 2,3,4,5 in the resources file
     * @param a The number of picture that will be used
     * @return the picture of the dice
     */
    public ImageIcon dicepic(int a) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL PIC = getClass().getResource("/resources/"+a +".png");
        ImageIcon DiceIcon = new ImageIcon(PIC);
        Image image = DiceIcon.getImage();
        Image newimg = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        DiceIcon = new ImageIcon(newimg);
        return DiceIcon;
    }

    /**
     * Get the frame
     * @return frame
     */
    public static JFrame getFrame() {
        return frame;
    }

    public static void main (String[]args){
        ThreadTest a = new ThreadTest();
    }
}




