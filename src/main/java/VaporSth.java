public class VaporSth {

    private double A;
    private double B;
    private boolean log;

    VaporSth(double A, double B, boolean log) {
        this.A = A;
        this.B = B;
        this.log = log;
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }
}
