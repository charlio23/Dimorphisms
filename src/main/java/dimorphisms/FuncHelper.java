package dimorphisms;

import static java.lang.Math.log;
import static java.lang.StrictMath.exp;
import static java.lang.StrictMath.pow;

public class FuncHelper {

    private MaterialProperties materialProperties;

    public FuncHelper(MaterialProperties materialProperties) {
        this.materialProperties = materialProperties;
    }

    public static double calculateTriplePointTemp(VaporSth curve1, VaporSth curve2) {
        if ((curve1.isLog() && curve2.isLog()) || (!curve1.isLog() && !curve2.isLog())){
            return (curve1.getB()-curve2.getB())/(curve1.getA()-curve2.getA());
        }else if (curve1.isLog() && !curve2.isLog()){
            return (curve1.getB()*log(10)-curve2.getB())/(curve1.getA()*log(10)-curve2.getA());
        }else{
            return (curve1.getB()-curve2.getB()*log(10))/(curve1.getA()-curve2.getA()*log(10));
        }
    }

    public static double calculateTriplePointPressure(VaporSth curve, double temp) {
        if (curve.isLog()) {
            return pow(10,curve.getA() - curve.getB()/(temp + curve.getC()));
        } else {
            return exp(curve.getA() - curve.getB()/(temp + curve.getC()));
        }
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
        double term1 = materialProperties.getPressL12() - materialProperties.getPressV12();
        double term2 = materialProperties.getTempL12() - materialProperties.getTempV12();
        return term1/term2;
    }

    public double   calculateDpdtLiquidSolid1() {
        double term1 = materialProperties.getPressL12() - materialProperties.getPressLV1();
        double term2 = materialProperties.getTempL12() - materialProperties.getTempLV1();
        return term1/term2;
    }

    public double calculateDpdtLiquidSolid2() {
        double term1 = materialProperties.getPressL12() - materialProperties.getPressLV2();
        double term2 = materialProperties.getTempL12() - materialProperties.getTempLV2();
        return term1/term2;
    }

    public static double[] getArrayFromVaporCurve(VaporSth eqCurve) {
        double[] result = new double[Utils.TEMPERATURE_SIZE];
        double temperature = Utils.TEMPERATURE_ORIGIN;
        for (int i = 0; i < Utils.TEMPERATURE_SIZE; ++i) {
            double value = eqCurve.getA() - eqCurve.getB()/(eqCurve.getC() + temperature);
            result[i] = exp(value);
            temperature += Utils.TEMPERATURE_STEP;
        }
        return result;
    }

    public static double[] getArrayFromVaporCurve(double init, double fin, VaporSth eqCurve) {
        double[] result = new double[Utils.DATA_SIZE];
        double temperature = init;
        double step = (fin - init)/Utils.DATA_SIZE;
        for (int i = 0; i < Utils.DATA_SIZE; ++i) {
            double value = eqCurve.getA() - eqCurve.getB()/(eqCurve.getC() + temperature);
            result[i] = exp(value);
            temperature += step;
        }
        return result;
    }

    public static double[] getArrayFromLine(double pointTemp, double pointPress, double p) {
        double[] result = new double[Utils.TEMPERATURE_SIZE];
        double temperature = Utils.TEMPERATURE_ORIGIN;
        for (int i = 0; i < Utils.TEMPERATURE_SIZE; ++i) {
            double value = p*(temperature - pointTemp) + pointPress;
            result[i] = value;
            temperature += Utils.TEMPERATURE_STEP;
        }
        return result;
    }

    public static double[] getArrayFromLine(double init, double fin, double pressIni ,double curve) {
        double[] result = new double[Utils.DATA_SIZE];
        double temperature = init;
        double step = (fin - init)/Utils.DATA_SIZE;
        for (int i = 0; i < Utils.DATA_SIZE; ++i) {
            double value = curve*(temperature - init) + pressIni;
            result[i] = value;
            temperature += step;
        }
        return result;
    }
}
