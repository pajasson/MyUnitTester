import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class View {

    private JFrame frame;
    private JTextField input = new JTextField("", 30);
    private JTextArea output = new JTextArea();
    private JButton start = new JButton("Start test");
    private JButton clear = new JButton("Clear");

    //Should only be called on EDT
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

    //Should only be called on EDT
    public void show() {
        frame.setVisible(true);
    }

    private JPanel buildUpperPanel() {

        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.setPreferredSize(new Dimension(500,30));


        //start.addActionListener(actionHandler);

        upperPanel.add(input, BorderLayout.WEST);
        upperPanel.add(start, BorderLayout.EAST);

        return upperPanel;
    }

    private JPanel buildMiddlePanel() {

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(500, 500));
        middlePanel.setLayout(new BorderLayout());

        output.setEditable(false);

        middlePanel.add(output, BorderLayout.CENTER);
        return middlePanel;
    }

    private JPanel buildLowerPanel() {

        JPanel lowerPanel = new JPanel();
        lowerPanel.setPreferredSize(new Dimension(500,30));

        lowerPanel.add(clear);

        return lowerPanel;
    }
    public JButton getStart(){
        return start;
    }
    public JButton getClear(){
        return clear;
    }
    public JTextField getInput(){
        return input;
    }
    public JTextArea getOutput(){
        return output;
    }

}
