/**
 * Created by konig on 2016-11-15.
 */
public class Controller {
    private View gui;
    private Model model;

    public Controller(View gui, Model model) {
        this.gui = gui;
        this.model = model;
        ActionHandler actionHandler = new ActionHandler(model, gui);
        this.gui.getStart().addActionListener(actionHandler);
        this.gui.getClear().addActionListener(actionHandler);

    }
}