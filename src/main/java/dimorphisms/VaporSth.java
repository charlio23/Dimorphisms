package dimorphisms;

/**
 * The type Vapor sth.
 */
public class VaporSth {

    private double a;
    private double b;
    private double c;
    private boolean log;

    /**
     * Instantiates a new Vapor sth.
     *
     * @param a   the a
     * @param b   the b
     * @param c   the c
     * @param log the log
     */
    VaporSth(double a, double b, double c, boolean log) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.log = log;
    }

    /**
     * Gets a.
     *
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * Sets a.
     *
     * @param a the a
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * Gets b.
     *
     * @return the b
     */
    public double getB() {
        return b;
    }

    /**
     * Sets b.
     *
     * @param b the b
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     * Gets c.
     *
     * @return the c
     */
    public double getC() { return c; }

    /**
     * Sets c.
     *
     * @param c the c
     */
    public void setC(double c) { this.c = c;}

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

    @Override
    public String toString() {
        return String.format ("%.2f", a) + " " + String.format ("%.2f", b) + " " + String.format ("%.2f", c);
    }
}
