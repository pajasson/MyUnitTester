import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Main class for the Graphics in the program
 *
 * Builds and sets up the GUIs panels and buttons
 */
public class View {

    private JFrame frame;
    private JTextField input = new JTextField("", 30);
    private JTextArea output = new JTextArea();
    private JButton start = new JButton("Start test");
    private JButton clear = new JButton("Clear");

    /**
     * The constructor method for the GUI
     *
     * @param title The title of the frame
     */
    public View(String title) {

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Build panels
        JPanel upperPanel = buildUpperPanel();
        JPanel middlePanel = buildMiddlePanel();
        JPanel lowerPanel = buildLowerPanel();

        //Add panels to the frame
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(lowerPanel, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * Executes the GUI window by displaying it on screen. This starts the
     * event dispatch thread.
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * This method is used by the constructor.
     * Builds the upperpanel of the GUI-window.
     * @return JPanel , The upper panel.
     */
    private JPanel buildUpperPanel() {

        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.setPreferredSize(new Dimension(500,30));

        upperPanel.add(input, BorderLayout.WEST);
        upperPanel.add(start, BorderLayout.EAST);

        return upperPanel;
    }

    /**
     * This method is used by the constructor.
     * Builds the middle panel of the GUI-window.
     * @return JPanel , The middle panel.
     */
    private JPanel buildMiddlePanel() {

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(500, 500));
        middlePanel.setLayout(new BorderLayout());

        output.setEditable(false);

        middlePanel.add(output, BorderLayout.CENTER);
        return middlePanel;
    }

    /**
     * This method is used by the constructor.
     * Builds the lower panel of the GUI-window.
     * @return JPanel , the lower panel.
     */
    private JPanel buildLowerPanel() {

        JPanel lowerPanel = new JPanel();
        lowerPanel.setPreferredSize(new Dimension(500,30));

        lowerPanel.add(clear);

        return lowerPanel;
    }

    /**
     * Getter method for the "start tests" button
     * @return JButton , "start test" button.
     */
    public JButton getStart(){
        return start;
    }

    /**
     * Getter method for the "clear" button
     * @return JButton , "clear" button.
     */
    public JButton getClear(){
        return clear;
    }

    /**
     * Getter method for the input field
     * @return JTextField , the input textfield.
     */
    public JTextField getInput(){
        return input;
    }

    /**
     * Getter method for the outputscreen
     * @return JTextArea , outputscreen.
     */
    public JTextArea getOutput(){
        return output;
    }

}
