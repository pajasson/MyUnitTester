import java.lang.Runnable;
import java.lang.NullPointerException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * The model class. This class contains the logic of the program. It implements
 * Runnable for ActionHandler to be able to create a new thread and execute its
 * run-method outside Event dispatch thread. Model updates the gui after running
 * the code.
 *
 * The model contains a String which represents the outer class to open and
 * test.
 */
public class Model implements Runnable {

    private View gui;
    private Method setup;
    private Method tearDown;
    private ArrayList<String> result = new ArrayList<>();

    public String classToRun = null;

    /**
     * Constructor method for Model.
     * @param gui The gui of the program
     */
    Model(View gui) {
        this.gui = gui;
    }

    /**
     * The definition of Runnables "run" method. This method opens a class file
     * currently specified in the "classToRun" String. This String is changed
     * by the ActionHandler. The class is opened and its methods are run by
     * using reflection.
     */
    @Override
    @SuppressWarnings("TryWithIdenticalCatches")
    public void run() {
        //clear all eventual information beforehand
        result.clear();
        Method[] methods;
        Object classInstance = null;
        Class<?> testClass;

        int successes = 0;
        int failExceps = 0;
        int fails = 0;

        if(classToRun != null) {
            //open the class in the class-variable
            try {
                testClass = Class.forName(classToRun);
            } catch (ClassNotFoundException e) {
                gui.getOutput().append("Class \"" + this.classToRun + "\" not found\n");
                return;
            }
            //create an instance-object of the class
            try {
                classInstance = testClass.newInstance();
            } catch (InstantiationException e) {
                System.out.println(e.getCause().toString());
            } catch (IllegalAccessException e) {
                System.out.println(e.getCause().toString());
            }
            //Save the objects methods in an array
            methods = testClass.getDeclaredMethods();

            //Locate and save the objects setUp and tearDown methods
            for (Method m : methods) {

                if (m.getName().equals("setUp")) {
                    setup = m;
                }
                if (m.getName().equals("tearDown")) {
                    tearDown = m;
                }
            }
            //For each method that begins with "test", run it with startUp
            //beforehand and tearDown after.
            for (Method m : methods) {

                try {

                    if (m.getName().startsWith("test")) {

                        setup.invoke(classInstance);

                        boolean test = (boolean) m.invoke(classInstance);
                        if (test) {
                            //information about the tests are saved in an
                            //arraylist of Strings
                            result.add(m.getName() + ": SUCCES\n");
                            successes++;
                        } else {
                            result.add(m.getName() + ": FAIL\n");
                            fails++;
                        }

                        tearDown.invoke(classInstance);
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e.getCause().toString());
                } catch (NullPointerException e){
                    System.out.println(e.getCause().toString());
                } catch (InvocationTargetException e) {
                    //Saving Information about a test exception.
                    result.add(e.getCause().getStackTrace()[0].getMethodName() +
                            ": FAIL Generated a " +
                            e.getCause().toString() + "\n");
                    failExceps++;
                }


            }
            //update the Gui with the information from the tests
            for (String s : result) {
                gui.getOutput().append(s);
            }
            //Summarize the testresults in the output
            gui.getOutput().append("\n" + successes + " tests succeded\n");
            gui.getOutput().append(fails + " tests failed\n");
            gui.getOutput().append(failExceps + " tests failed because of an exception\n\n");
            classToRun = null;
        }
    }
}
