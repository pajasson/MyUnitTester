import javax.swing.*;

public class MyUnitTester
{

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
