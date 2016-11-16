import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by konig on 2016-11-15.
 */
public class ActionHandler implements ActionListener {

    public Model model;
    public View gui;
    public ActionHandler(Model model, View gui)
    {
        this.model = model;
        this.gui = gui;
    }
    public void actionPerformed(ActionEvent e) {
        //tester.runTests();
        switch(e.getActionCommand())
        {
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
