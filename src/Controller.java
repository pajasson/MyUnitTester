/**
 * The controller class. This class sets up listeners for buttons to
 * manage user actions in the program.
 */
public class Controller {
    private View gui;
    private Model model;

    /**
     * The constructor method for controller.
     * @param gui The gui window
     * @param model The model of the program
     */
    public Controller(View gui, Model model) {
        this.gui = gui;
        this.model = model;
        ActionHandler actionHandler = new ActionHandler(model, gui);
        this.gui.getStart().addActionListener(actionHandler);
        this.gui.getClear().addActionListener(actionHandler);

    }
}