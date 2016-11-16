
public class MyDouble {

    private double val;
    public MyDouble() {
        val=0.0;
    }

    public void increment() {
        val=val + 0.1;
    }

    public void decrement() {
        val=val - 0.1;
    }

    public double value() {
        return val;
    }

}