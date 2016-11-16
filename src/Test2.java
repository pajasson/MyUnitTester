/**
 * Created by konig on 2016-11-16.
 */
public class Test2 implements TestClass {
    private MyDouble myDouble;

    public Test2() {
    }

    public void setUp() {
        myDouble = new MyDouble();
    }

    public void tearDown() {
        myDouble=null;
    }

    //Test that should succeed
    public boolean testInitialisation() {
        return myDouble.value()==0;
    }

    //Test that should succeed
    public boolean testIncrement() {
        myDouble.increment();
        myDouble.increment();
        return myDouble.value()==0.2;

    }

    //Test that should succeed
    public boolean testDecrement() {
        myDouble.increment();
        myDouble.decrement();
        return myDouble.value() == 0;
    }

    //Test that should fail
    public boolean testFailingByException() {
        myDouble=null;
        myDouble.decrement();
        return true;

    }

    //Test that should fail
    public boolean testFailing() {
        return false;

    }
}
