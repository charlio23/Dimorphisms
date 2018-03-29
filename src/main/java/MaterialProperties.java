import java.util.Random;

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

    MaterialProperties(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public VaporSth getVaporLiquid() {
        return vaporLiquid;
    }

    public void setVaporLiquid(double A, double B, double C, boolean log) {
        this.vaporLiquid = new VaporSth(A, B, C, log);
    }

    public VaporSth getVaporSolid1() {
        return vaporSolid1;
    }

    public void setVaporSolid1(double A, double B, double C, boolean log) {
        this.vaporSolid1 = new VaporSth(A, B, C, log);
    }

    public VaporSth getVaporSolid2() {
        return vaporSolid2;
    }

    public void setVaporSolid2(double A, double B, double C, boolean log) {
        this.vaporSolid2 = new VaporSth(A, B, C, log);
    }

    public double getLiquidSolid1() {
        return liquidSolid1;
    }

    public void setLiquidSolid1(double liquidSolid1) {
        this.liquidSolid1 = liquidSolid1;
    }

    public double getLiquidSolid2() {
        return liquidSolid2;
    }

    public void setLiquidSolid2(double liquidSolid2) {
        this.liquidSolid2 = liquidSolid2;
    }

    public double getSolid1Solid2() {
        return solid1Solid2;
    }

    public void setSolid1Solid2(double solid1Solid2) {
        this.solid1Solid2 = solid1Solid2;
    }

    public double getTempLV1() {
        return tempLV1;
    }

    public void setTempLV1(double tempLV1) {
        this.tempLV1 = tempLV1;
    }

    public double getTempLV2() {
        return tempLV2;
    }

    public void setTempLV2(double tempLV2) {
        this.tempLV2 = tempLV2;
    }

    public double getTempV12() {
        return tempV12;
    }

    public void setTempV12(double tempV12) {
        this.tempV12 = tempV12;
    }

    public double getTempL12() {
        return tempL12;
    }

    public void setTempL12(double tempL12) {
        this.tempL12 = tempL12;
    }

    public String dataToString() {
        return this.name;
    }
}
