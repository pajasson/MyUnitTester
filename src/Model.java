import java.lang.Runnable;
import java.lang.NullPointerException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Model implements Runnable {

    private View gui;
    public String classToRun = null;

    private Method setup;
    private Method tearDown;

    private ArrayList<String> result = new ArrayList<>();


    Model(View gui) {
        this.gui = gui;
    }

    @Override
    @SuppressWarnings("TryWithIdenticalCatches")
    public void run() {
        result.clear();
        Method[] methods;
        Object classInstance = null;
        Class<?> testClass;

        int successes = 0;
        int failExceps = 0;
        int fails = 0;

        if(classToRun != null) {
            try {
                testClass = Class.forName(classToRun);
            } catch (ClassNotFoundException e) {
                gui.getOutput().append("Class \"" + this.classToRun + "\" not found\n");
                return;
            }
            try {
                classInstance = testClass.newInstance();
            } catch (InstantiationException e) {
                System.out.println(e.getCause().toString());
            } catch (IllegalAccessException e) {
                System.out.println(e.getCause().toString());
            }
            methods = testClass.getDeclaredMethods();

            for (Method m : methods) {

                if (m.getName().equals("setUp")) {
                    setup = m;
                }
                if (m.getName().equals("tearDown")) {
                    tearDown = m;
                }
            }

            for (Method m : methods) {

                try {

                    if (m.getName().startsWith("test")) {

                        setup.invoke(classInstance);

                        boolean test = (boolean) m.invoke(classInstance);
                        if (test) {
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
                    result.add(e.getCause().getStackTrace()[0].getMethodName() +
                            ": FAIL Generated a " +
                            e.getCause().toString() + "\n");
                    failExceps++;
                }


            }

            for (String s : result) {
                gui.getOutput().append(s);
            }
            gui.getOutput().append("\n" + successes + " tests succeded\n");
            gui.getOutput().append(fails + " tests failed\n");
            gui.getOutput().append(failExceps + " tests failed because of an exception\n\n");
            classToRun = null;
        }
    }
}
