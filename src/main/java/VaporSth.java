public class VaporSth {

    private double A;
    private double B;
    private double C;
    private boolean log;

    VaporSth(double A, double B, double C, boolean log) {
        this.A = A;
        this.B = B;
        this.C = C;
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

    public double getC() { return C; }

    public void setC(double c) { C = c;}

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }
}
