package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This class is a simple application that writes a random number on a file.
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class MiniGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final int PROPORTION = 5;
    private final Random randomGenerator = new Random();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Creates a new {@link MiniGUI}.
     */
    public MiniGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout()); // configurazione del canvas per utilizzare il layout manager BorderLayout
        final JButton write = new JButton("Print a random number on standard output");
        canvas.add(write, BorderLayout.CENTER); // aggiungo il buttone al pannello, ed è posizionato sempre al centro
        frame.setContentPane(canvas); // il frame utilizza come suo componente principale il JPanel canvas
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Part 1 
         */

        final JPanel canvas2 = new JPanel();
        canvas2.setLayout(new BoxLayout(canvas2, BoxLayout.LINE_AXIS));
        canvas.add(canvas2,BorderLayout.CENTER);
        canvas2.add(write);

        /*
         * Part 2
         */

        final TextField textIn = new TextField("Result");
        canvas.add(textIn, BorderLayout.NORTH);
        /*
         * Handlers
         */
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                int numberRandom = randomGenerator.nextInt();
                System.out.println(numberRandom);
                textIn.setText("Number generate: " + numberRandom);
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very
         * method is enough for a single screen setup. In case of multiple
         * monitors, the primary is selected. In order to deal coherently with
         * multimonitor setups, other facilities exist (see the Java
         * documentation about this issue). It is MUCH better than manually
         * specify the size of a window in pixel: it takes into account the
         * current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * Resize the frame to minimum size
         */
        frame.pack();
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new MiniGUI().display();
    }

}
