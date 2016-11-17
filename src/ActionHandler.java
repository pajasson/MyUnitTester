import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class implements the interface ActionListener. It contains a Model
 * And a View and it controls the events that executes on different
 * buttonactions.
 */
public class ActionHandler implements ActionListener {

    private Model model;
    private View gui;

    /**
     * The constructormethod for actionhandler.
     * @param model The class containing the programs logic
     * @param gui The class containing the programs view.
     */
    public ActionHandler(Model model, View gui) {
        this.model = model;
        this.gui = gui;
    }

    /**
     * The defenition of ActionListeners actionPerformed method.
     * Depending on the buttons title, different actions are performed
     * in a switch case.
     * @param e The actionevent.
     */
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()) {

            case "Start test":
                model.classToRun = gui.getInput().getText();
                Thread t = new Thread(model);
                t.start();
                break;

            case "Clear":
                gui.getOutput().setText("");
                break;

            default:
                break;
        }
    }
}
