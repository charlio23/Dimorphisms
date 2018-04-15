package dimorphisms;

/**
 * The type Material properties.
 */
public class MaterialProperties {

    private String name;
    private VaporSth vaporLiquid;
    private VaporSth vaporSolid1;
    private VaporSth vaporSolid2;
    private double liquidSolid1;
    private double liquidSolid2;
    private double solid1Solid2;
    private double tempLV1;
    private double tempLV2;
    private double tempV12;
    private double tempL12;
    private double pressLV1;
    private double pressLV2;
    private double pressV12;
    private double pressL12;

    /**
     * Instantiates a new Material properties.
     *
     * @param name the name
     */
    MaterialProperties(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets vapor liquid.
     *
     * @return the vapor liquid
     */
    public VaporSth getVaporLiquid() {
        return vaporLiquid;
    }

    /**
     * Sets vapor liquid.
     *
     * @param a   the a
     * @param b   the b
     * @param c   the c
     * @param log the log
     */
    public void setVaporLiquid(double a, double b, double c, boolean log) {
        this.vaporLiquid = new VaporSth(a, b, c, log);
    }

    /**
     * Gets vapor solid 1.
     *
     * @return the vapor solid 1
     */
    public VaporSth getVaporSolid1() {
        return vaporSolid1;
    }

    /**
     * Sets vapor solid 1.
     *
     * @param a   the a
     * @param b   the b
     * @param c   the c
     * @param log the log
     */
    public void setVaporSolid1(double a, double b, double c, boolean log) {
        this.vaporSolid1 = new VaporSth(a, b, c, log);
    }

    /**
     * Gets vapor solid 2.
     *
     * @return the vapor solid 2
     */
    public VaporSth getVaporSolid2() {
        return vaporSolid2;
    }

    /**
     * Sets vapor solid 2.
     *
     * @param a   the a
     * @param b   the b
     * @param c   the c
     * @param log the log
     */
    public void setVaporSolid2(double a, double b, double c, boolean log) {
        this.vaporSolid2 = new VaporSth(a, b, c, log);
    }

    /**
     * Gets liquid solid 1.
     *
     * @return the liquid solid 1
     */
    public double getLiquidSolid1() {
        return liquidSolid1;
    }

    /**
     * Sets liquid solid 1.
     *
     * @param liquidSolid1 the liquid solid 1
     */
    public void setLiquidSolid1(double liquidSolid1) {
        this.liquidSolid1 = liquidSolid1;
    }

    /**
     * Gets liquid solid 2.
     *
     * @return the liquid solid 2
     */
    public double getLiquidSolid2() {
        return liquidSolid2;
    }

    /**
     * Sets liquid solid 2.
     *
     * @param liquidSolid2 the liquid solid 2
     */
    public void setLiquidSolid2(double liquidSolid2) {
        this.liquidSolid2 = liquidSolid2;
    }

    /**
     * Gets solid 1 solid 2.
     *
     * @return the solid 1 solid 2
     */
    public double getSolid1Solid2() {
        return solid1Solid2;
    }

    /**
     * Sets solid 1 solid 2.
     *
     * @param solid1Solid2 the solid 1 solid 2
     */
    public void setSolid1Solid2(double solid1Solid2) {
        this.solid1Solid2 = solid1Solid2;
    }

    /**
     * Gets temp lv 1.
     *
     * @return the temp lv 1
     */
    public double getTempLV1() {
        return tempLV1;
    }

    /**
     * Sets temp lv 1.
     *
     * @param tempLV1 the temp lv 1
     */
    public void setTempLV1(double tempLV1) {
        this.tempLV1 = tempLV1;
    }

    /**
     * Gets temp lv 2.
     *
     * @return the temp lv 2
     */
    public double getTempLV2() {
        return tempLV2;
    }

    /**
     * Sets temp lv 2.
     *
     * @param tempLV2 the temp lv 2
     */
    public void setTempLV2(double tempLV2) {
        this.tempLV2 = tempLV2;
    }

    /**
     * Gets temp v 12.
     *
     * @return the temp v 12
     */
    public double getTempV12() {
        return tempV12;
    }

    /**
     * Sets temp v 12.
     *
     * @param tempV12 the temp v 12
     */
    public void setTempV12(double tempV12) {
        this.tempV12 = tempV12;
    }

    /**
     * Gets temp l 12.
     *
     * @return the temp l 12
     */
    public double getTempL12() {
        return tempL12;
    }

    /**
     * Sets temp l 12.
     *
     * @param tempL12 the temp l 12
     */
    public void setTempL12(double tempL12) {
        this.tempL12 = tempL12;
    }

    /**
     * Data to string string.
     *
     * @return the string
     */
    public String dataToString() {
        return this.name;
    }

    public double getPressLV1() {
        return pressLV1;
    }

    public void setPressLV1(double pressLV1) {
        this.pressLV1 = pressLV1;
    }

    public double getPressLV2() {
        return pressLV2;
    }

    public void setPressLV2(double pressLV2) {
        this.pressLV2 = pressLV2;
    }

    public double getPressV12() {
        return pressV12;
    }

    public void setPressV12(double pressV12) {
        this.pressV12 = pressV12;
    }

    public double getPressL12() {
        return pressL12;
    }

    public void setPressL12(double pressL12) {
        this.pressL12 = pressL12;
    }
}
