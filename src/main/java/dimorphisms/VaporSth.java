package dimorphisms;

/**
 * The type Vapor sth.
 */
public class VaporSth {

    private double A;
    private double B;
    private double C;
    private boolean log;

    /**
     * Instantiates a new Vapor sth.
     *
     * @param A   the a
     * @param B   the b
     * @param C   the c
     * @param log the log
     */
    VaporSth(double A, double B, double C, boolean log) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.log = log;
    }

    /**
     * Gets a.
     *
     * @return the a
     */
    public double getA() {
        return A;
    }

    /**
     * Sets a.
     *
     * @param a the a
     */
    public void setA(double a) {
        A = a;
    }

    /**
     * Gets b.
     *
     * @return the b
     */
    public double getB() {
        return B;
    }

    /**
     * Sets b.
     *
     * @param b the b
     */
    public void setB(double b) {
        B = b;
    }

    /**
     * Gets c.
     *
     * @return the c
     */
    public double getC() { return C; }

    /**
     * Sets c.
     *
     * @param c the c
     */
    public void setC(double c) { C = c;}

    /**
     * Is log boolean.
     *
     * @return the boolean
     */
    public boolean isLog() {
        return log;
    }

    /**
     * Sets log.
     *
     * @param log the log
     */
    public void setLog(boolean log) {
        this.log = log;
    }
}
