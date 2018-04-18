package dimorphisms;

import static java.lang.StrictMath.exp;

public class FuncHelper {

    private MaterialProperties materialProperties;

    public FuncHelper(MaterialProperties materialProperties) {
        this.materialProperties = materialProperties;
    }
    public static double calculateTriplePointTemp(VaporSth curve1, VaporSth curve2) {
        return 0;
    }

    public static double calculateTriplePointPressure(VaporSth curve, double temp) {
        return 0;
    }

    public double calculateTL12() {
        double term1 = materialProperties.getLiquidSolid1()*materialProperties.getTempLV1();
        double term2 = materialProperties.getLiquidSolid2()*materialProperties.getTempLV2();
        double term3 = materialProperties.getPressLV2() - materialProperties.getPressLV1();
        double numerator = term1 - term2 + term3;
        double denominator = materialProperties.getLiquidSolid1() - materialProperties.getLiquidSolid2();
        return numerator/denominator;
    }

    public double calculatePL12() {
        double term1 = materialProperties.getLiquidSolid1()*materialProperties.getTempL12();
        double term2 = materialProperties.getLiquidSolid1()*materialProperties.getTempLV1();
        double term3 = materialProperties.getPressLV1();
        return term1 - term2 + term3;
    }

    public double calculateDpdtSolid1Solid2() {
        return 0;
    }

    public double calculateDpdtLiquidSolid1() {
        return 0;
    }

    public double calculateDpdtLiquidSolid2() {
        return 0;
    }

    public static double[] getArrayFromVaporCurve(VaporSth eqCurve) {
        double[] result = new double[10000];
        double temperature = Utils.TEMPERATURE_ORIGIN;
        for (int i = 0; i < 10000; ++i) {
            double value = eqCurve.getA() - eqCurve.getB()/(eqCurve.getC() + temperature);
            result[i] = exp(value);
            temperature += Utils.TEMPERATURE_STEP;
        }
        return result;
    }
}
