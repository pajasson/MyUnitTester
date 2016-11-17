import javax.swing.*;

/**
 * Main class for the program MyUnitTester
 *
 * Starts up a new GUI with a controller and sets up the associated model.
 * The GUI will run on the event dispatch thread
 */
public class MyUnitTester {

    public static void main( String[] args ) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                View gui = new View("Tester");
                Model model = new Model(gui) ;
                Controller controller = new Controller(gui, model);
                gui.show();
            }

        });
    }
}
