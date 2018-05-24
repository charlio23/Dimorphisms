package dimorphisms;

import javafx.scene.chart.LineChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.log;

public class QueryController {

    private List<String> queries;
    private MaterialProperties materialProperties;
    private GraphicHelper graphic;
    private FuncHelper funcHelper;

    public QueryController(String name) {
        queries = new ArrayList<>();
        materialProperties = new MaterialProperties(name);
        this.graphic = new GraphicHelper();
        funcHelper = new FuncHelper(materialProperties);
    }

    public MaterialProperties getMaterialProperties() {
        return materialProperties;
    }

    public ArrayList<String> makeQueryVaporSth(String query, VaporSth eqCurve) {
        //Check if we have conflicts
        ArrayList<String> result = new ArrayList<>();
        if (queries.contains("#" + query)) {
            result.add(Utils.QUERY_ERROR_CONFLICT);
            return result;
        }
        if (queries.contains(query)) {
            removeCalculatedQueries();
            queries.remove(query);
        }
        switch (query) {
            case Utils.QUERY_LIQUID_VAPOR:
                addLiquidVapor(eqCurve,true);
                break;

            case Utils.QUERY_VAPOR_SOLID1:
                addVaporSolid1(eqCurve,true);
                break;

            case Utils.QUERY_VAPOR_SOLID2:
                addVaporSolid2(eqCurve,true);
                break;

            default:
                result.add(Utils.QUERY_ERROR_UNEXPECTED);
                return result;
        }

        result.add(Utils.QUERY_SUCCESS);
        result.addAll(getCalculatedElements());
        if (queries.size() == 17) result.add(Utils.ACTIVATE_STABLE_DIAGRAM);
        return result;
    }

    public ArrayList<String> makeQueryOther(String query, double value) {
        //Check if we have conflicts
        ArrayList<String> result = new ArrayList<>();
        if (queries.contains("#" + query)) {
            result.add(Utils.QUERY_ERROR_CONFLICT);
            /* TODO
            determine conflicts
             */
            return result;
        }
        if (queries.contains(query)) {
            removeCalculatedQueries();
            queries.remove(query);
        }
        switch (query) {
            case Utils.QUERY_LIQUID_SOLID1:
                addLiquidSolid1(value, true);
                break;

            case Utils.QUERY_LIQUID_SOLID2:
                addLiquidSolid2(value, true);
                break;

            case Utils.QUERY_SOLID1_SOLID2:
                addSolid1Solid2(value, true);
                break;

            case Utils.QUERY_TLV1:
                addTLV1(value, true);
                break;

            case Utils.QUERY_TLV2:
                addTLV2(value, true);
                break;

            case Utils.QUERY_TV12:
                addTV12(value, true);
                break;

            default:
                result.add(Utils.QUERY_ERROR_UNEXPECTED);
                return result;

        }
        result.add(Utils.QUERY_SUCCESS);
        result.addAll(getCalculatedElements());
        if (queries.size() == 17) result.add(Utils.ACTIVATE_STABLE_DIAGRAM);
        return result;
    }

    public LineChart getLinearGraphic() {
        return graphic.getLinearGraphic();
    }

    public LineChart[] getLogGraphic() {
        return graphic.getLogGraphic();
    }

    private void addLiquidVapor(VaporSth eqCurve, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_LIQUID_VAPOR);
        } else {
            queries.add("#" + Utils.QUERY_LIQUID_VAPOR);
        }
        materialProperties.setVaporLiquid(eqCurve.getA(),eqCurve.getB(),eqCurve.getC(),eqCurve.isLog());
        graphic.addCurve(Utils.QUERY_LIQUID_VAPOR,FuncHelper.getArrayFromVaporCurve(eqCurve));
        checkIfWeCanAdd();
    }

    private void addVaporSolid1(VaporSth eqCurve, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_VAPOR_SOLID1);
        } else {
            queries.add("#" + Utils.QUERY_VAPOR_SOLID1);
        }
        materialProperties.setVaporSolid1(eqCurve.getA(),eqCurve.getB(),eqCurve.getC(),eqCurve.isLog());
        graphic.addCurve(Utils.QUERY_VAPOR_SOLID1,FuncHelper.getArrayFromVaporCurve(eqCurve));
        checkIfWeCanAdd();
    }

    private void addVaporSolid2(VaporSth eqCurve, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_VAPOR_SOLID2);
        } else {
            queries.add("#" + Utils.QUERY_VAPOR_SOLID2);
        }
        materialProperties.setVaporSolid2(eqCurve.getA(),eqCurve.getB(),eqCurve.getC(),eqCurve.isLog());
        graphic.addCurve(Utils.QUERY_VAPOR_SOLID2,FuncHelper.getArrayFromVaporCurve(eqCurve));
        checkIfWeCanAdd();
    }

    private void addLiquidSolid1(double dpdt, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_LIQUID_SOLID1);
        } else {
            queries.add("#" + Utils.QUERY_LIQUID_SOLID1);
        }
        materialProperties.setLiquidSolid1(dpdt);
        checkIfWeCanAdd();

    }

    private void addLiquidSolid2(double dpdt, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_LIQUID_SOLID2);
        } else {
            queries.add("#" + Utils.QUERY_LIQUID_SOLID2);
        }
        materialProperties.setLiquidSolid2(dpdt);
        checkIfWeCanAdd();

    }

    private void addSolid1Solid2(double dpdt, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_SOLID1_SOLID2);
        } else {
            queries.add("#" + Utils.QUERY_SOLID1_SOLID2);
        }
        materialProperties.setSolid1Solid2(dpdt);
        checkIfWeCanAdd();

    }


    private void addTLV1(double temp, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_TLV1);
        } else {
            queries.add("#" + Utils.QUERY_TLV1);
        }
        materialProperties.setTempLV1(temp);
        checkIfWeCanAdd();
    }


    private void addTLV2(double temp, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_TLV2);
        } else {
            queries.add("#" + Utils.QUERY_TLV2);
        }
        materialProperties.setTempLV2(temp);
        checkIfWeCanAdd();
    }


    private void addTV12(double temp, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_TV12);
        } else {
            queries.add("#" + Utils.QUERY_TV12);
        }
        materialProperties.setTempV12(temp);
        checkIfWeCanAdd();
    }

    private void addTL12(double temp, boolean original) {
        if (original) {
            queries.add(Utils.QUERY_TL12);
        } else {
            queries.add("#" + Utils.QUERY_TL12);
        }
        materialProperties.setTempL12(temp);
        addPL12(funcHelper.calculatePL12());
        checkIfWeCanAdd();
    }

    private void addPLV1(double press) {
        queries.add("#" + Utils.QUERY_PLV1);
        materialProperties.setPressLV1(press);
        graphic.addPoint(Utils.QUERY_TLV1,materialProperties.getTempLV1(),press);
        checkIfWeCanAdd();
    }

    private void addPLV2(double press) {
        queries.add("#" + Utils.QUERY_PLV2);
        materialProperties.setPressLV2(press);
        graphic.addPoint(Utils.QUERY_TLV2,materialProperties.getTempLV2(),press);
        checkIfWeCanAdd();
    }

    private void addPV12(double press) {
        queries.add("#" + Utils.QUERY_PV12);
        materialProperties.setPressV12(press);
        graphic.addPoint(Utils.QUERY_TV12,materialProperties.getTempV12(),press);
        checkIfWeCanAdd();
    }

    private void addPL12(double press) {
        queries.add("#" + Utils.QUERY_PL12);
        materialProperties.setPressL12(press);
        graphic.addPoint(Utils.QUERY_TL12,materialProperties.getTempL12(),press);
        checkIfWeCanAdd();

    }

    private void addCurveLiquidSolid1() {
        queries.add("#" + Utils.QUERY_CURVE_LIQUID_SOLID1);
        graphic.addCurve(Utils.QUERY_CURVE_LIQUID_SOLID1,FuncHelper.getArrayFromLine(materialProperties.getTempLV1(),materialProperties.getPressLV1(),materialProperties.getLiquidSolid1()));
        checkIfWeCanAdd();
    }

    private void addCurveLiquidSolid2() {
        queries.add("#" + Utils.QUERY_CURVE_LIQUID_SOLID2);
        graphic.addCurve(Utils.QUERY_CURVE_LIQUID_SOLID2,FuncHelper.getArrayFromLine(materialProperties.getTempLV2(),materialProperties.getPressLV2(),materialProperties.getLiquidSolid2()));
        checkIfWeCanAdd();
    }

    private void addCurveSolid1Solid2() {
        queries.add("#" + Utils.QUERY_CURVE_SOLID1_SOLID2);
        graphic.addCurve(Utils.QUERY_CURVE_SOLID1_SOLID2,FuncHelper.getArrayFromLine(materialProperties.getTempV12(),materialProperties.getPressV12(),materialProperties.getSolid1Solid2()));
        checkIfWeCanAdd();
    }

    /* TODO
    we need to add tryAddCurveETC...
     */
    private void checkIfWeCanAdd(){
        //afegir PressLV1
        if(tryAddPressLV1()) return;

        //afegir PressLV2
        if(tryAddPressLV2()) return;
        //afegir Press V12
        if (tryAddPressV12()) return;
        //afegir LV
        if (canAddLiquidVapor()) {
            double valueB = (log(materialProperties.getPressLV1()/materialProperties.getPressLV2()))/((1/materialProperties.getTempLV2())-(1/materialProperties.getTempLV1()));
            double valueA = log(materialProperties.getPressLV1()) + valueB/materialProperties.getTempLV1();
            double valueC = 0;
            addLiquidVapor(new VaporSth(valueA,valueB,valueC,Utils.ISLOG_DEFAULT_VALUE),false);
        }
        //afegir V1
        else if (canAddVaporSolid1()) {
            double valueB = (log(materialProperties.getPressLV1()/materialProperties.getPressV12()))/((1/materialProperties.getTempV12())-(1/materialProperties.getTempLV1()));
            double valueA = log(materialProperties.getPressLV1()) + valueB/materialProperties.getTempLV1();
            double valueC = 0;
            addVaporSolid1(new VaporSth(valueA,valueB,valueC,Utils.ISLOG_DEFAULT_VALUE),false);
        }
        //afegir V2
        else if (canAddVaporSolid2()) {
            double valueB = (log(materialProperties.getPressLV2()/materialProperties.getPressV12()))/((1/materialProperties.getTempV12())-(1/materialProperties.getTempLV2()));
            double valueA = log(materialProperties.getPressLV2()) + valueB/materialProperties.getTempLV2();
            double valueC = 0;
            addVaporSolid2(new VaporSth(valueA,valueB,valueC,Utils.ISLOG_DEFAULT_VALUE),false);
        }

        //afegir tempLV1
        else if (canAddTLV1()) {
            addTLV1(FuncHelper.calculateTriplePointTemp(materialProperties.getVaporLiquid(),materialProperties.getVaporSolid1()),false);
        }
        //afegir tempLV2
        else if (canAddTLV2()) {
            addTLV2(FuncHelper.calculateTriplePointTemp(materialProperties.getVaporLiquid(),materialProperties.getVaporSolid2()),false);
        }
        //afegir tempV12
        else if (canAddTV12()) {
            addTV12(FuncHelper.calculateTriplePointTemp(materialProperties.getVaporSolid1(),materialProperties.getVaporSolid2()),false);
        }
        //afegir tempL12
        else if (canAddTL12()) {
            addTL12(funcHelper.calculateTL12(),false);
        }
        //afegir 12
        else if (canAddSolid1Solid2()) {
            addSolid1Solid2(funcHelper.calculateDpdtSolid1Solid2(),false);
        }
        //afegir L1
        else if (canAddLiquidSolid1()) {
            addLiquidSolid1(funcHelper.calculateDpdtLiquidSolid1(),false);

        }
        //afegir L2
        else if (canAddLiquidSolid2()) {
            addLiquidSolid2(funcHelper.calculateDpdtLiquidSolid2(),false);
        }
        //paint L1
        else if(canPaintLiquidSolid1()){
            addCurveLiquidSolid1();
        }
        //paint L2
        else if(canPaintLiquidSolid2()){
            addCurveLiquidSolid2();
        }
        //paint 12
        else if(canPaintSolid1Solid2()){
            addCurveSolid1Solid2();
        }

    }

    private boolean tryAddPressLV1() {
        if((queries.contains(Utils.QUERY_TLV1) || queries.contains("#" + Utils.QUERY_TLV1)) && !queries.contains("#" + Utils.QUERY_PLV1)) {
            if (queries.contains(Utils.QUERY_LIQUID_VAPOR) ||
                    queries.contains("#" + Utils.QUERY_LIQUID_VAPOR)) {
                double pointPressure = FuncHelper.calculateTriplePointPressure(materialProperties.getVaporLiquid(),materialProperties.getTempLV1());
                addPLV1(pointPressure);
                return true;
            } else if (queries.contains(Utils.QUERY_VAPOR_SOLID1) ||
                    queries.contains("#" + Utils.QUERY_VAPOR_SOLID1)) {
                double pointPressure = FuncHelper.calculateTriplePointPressure(materialProperties.getVaporSolid1(),materialProperties.getTempLV1());
                addPLV1(pointPressure);
                return true;
            }
        }
        return false;
    }

    private boolean tryAddPressLV2() {
        if((queries.contains(Utils.QUERY_TLV2) || queries.contains("#" + Utils.QUERY_TLV2)) && !queries.contains("#" + Utils.QUERY_PLV2)) {
            if (queries.contains(Utils.QUERY_LIQUID_VAPOR) ||
                    queries.contains("#" + Utils.QUERY_LIQUID_VAPOR)) {
                double pointPressure = FuncHelper.calculateTriplePointPressure(materialProperties.getVaporLiquid(),materialProperties.getTempLV2());
                addPLV2(pointPressure);
                return true;
            } else if (queries.contains(Utils.QUERY_VAPOR_SOLID2) ||
                    queries.contains("#" + Utils.QUERY_VAPOR_SOLID2)) {
                double pointPressure = FuncHelper.calculateTriplePointPressure(materialProperties.getVaporSolid2(),materialProperties.getTempLV2());
                addPLV2(pointPressure);
                return true;
            }
        }
        return false;
    }

    private boolean tryAddPressV12() {
        if((queries.contains(Utils.QUERY_TV12) || queries.contains("#" + Utils.QUERY_TV12)) && !queries.contains("#" + Utils.QUERY_PV12)) {
            if (queries.contains(Utils.QUERY_VAPOR_SOLID1) ||
                    queries.contains("#" + Utils.QUERY_VAPOR_SOLID1)) {
                double pointPressure = FuncHelper.calculateTriplePointPressure(materialProperties.getVaporSolid1(),materialProperties.getTempV12());
                addPV12(pointPressure);
                return true;
            } else if (queries.contains(Utils.QUERY_VAPOR_SOLID2) ||
                    queries.contains("#" + Utils.QUERY_VAPOR_SOLID2)) {
                double pointPressure = FuncHelper.calculateTriplePointPressure(materialProperties.getVaporSolid2(),materialProperties.getTempV12());
                addPV12(pointPressure);
                return true;
            }
        }
        return false;
    }

    private boolean canAddLiquidVapor() {
        return ((queries.contains(Utils.QUERY_VAPOR_SOLID1) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID1)) &&
                (queries.contains(Utils.QUERY_VAPOR_SOLID2) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID2)) &&
                (queries.contains(Utils.QUERY_TLV1) || queries.contains("#" + Utils.QUERY_TLV1)) &&
                (queries.contains(Utils.QUERY_TLV2) || queries.contains("#" + Utils.QUERY_TLV2)) &&
                (!(queries.contains(Utils.QUERY_LIQUID_VAPOR) || queries.contains("#" + Utils.QUERY_LIQUID_VAPOR))));

    }

    private boolean canAddVaporSolid1() {
        return ((queries.contains(Utils.QUERY_LIQUID_VAPOR) || queries.contains("#" + Utils.QUERY_LIQUID_VAPOR)) &&
                (queries.contains(Utils.QUERY_VAPOR_SOLID2) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID2)) &&
                (queries.contains(Utils.QUERY_TLV1) || queries.contains("#" + Utils.QUERY_TLV1)) &&
                (queries.contains(Utils.QUERY_TV12) || queries.contains("#" + Utils.QUERY_TV12)) &&
                (!(queries.contains(Utils.QUERY_VAPOR_SOLID1) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID1))));

    }

    private boolean canAddVaporSolid2() {
        return ((queries.contains(Utils.QUERY_LIQUID_VAPOR) || queries.contains("#" + Utils.QUERY_LIQUID_VAPOR)) &&
                (queries.contains(Utils.QUERY_VAPOR_SOLID1) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID1)) &&
                (queries.contains(Utils.QUERY_TLV2) || queries.contains("#" + Utils.QUERY_TLV2)) &&
                (queries.contains(Utils.QUERY_TV12) || queries.contains("#" + Utils.QUERY_TV12)) &&
                (!(queries.contains(Utils.QUERY_VAPOR_SOLID2) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID2))));
    }

    private boolean canAddTLV1() {
        return ((queries.contains(Utils.QUERY_LIQUID_VAPOR) || queries.contains("#" + Utils.QUERY_LIQUID_VAPOR)) &&
                (queries.contains(Utils.QUERY_VAPOR_SOLID1) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID1)) &&
                (!(queries.contains(Utils.QUERY_TLV1) || queries.contains("#" + Utils.QUERY_TLV1))));

    }

    private boolean canAddTLV2() {
        return ((queries.contains(Utils.QUERY_LIQUID_VAPOR) || queries.contains("#" + Utils.QUERY_LIQUID_VAPOR)) &&
                (queries.contains(Utils.QUERY_VAPOR_SOLID2) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID2)) &&
                (!(queries.contains(Utils.QUERY_TLV2) || queries.contains("#" + Utils.QUERY_TLV2))));

    }

    private boolean canAddTV12() {
        return ((queries.contains(Utils.QUERY_VAPOR_SOLID1) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID1)) &&
                (queries.contains(Utils.QUERY_VAPOR_SOLID2) || queries.contains("#" + Utils.QUERY_VAPOR_SOLID2)) &&
                (!(queries.contains(Utils.QUERY_TV12) || queries.contains("#" + Utils.QUERY_TV12))));

    }

    private boolean canAddTL12() {
        return (queries.contains("#" + Utils.QUERY_CURVE_LIQUID_SOLID1) &&
                queries.contains("#" + Utils.QUERY_CURVE_LIQUID_SOLID2) &&
                (!queries.contains("#" + Utils.QUERY_TL12)));

    }

    private boolean canAddSolid1Solid2() {
        return (queries.contains("#" + Utils.QUERY_PL12) &&
                queries.contains("#" + Utils.QUERY_PV12) &&
                (!(queries.contains(Utils.QUERY_SOLID1_SOLID2) || queries.contains("#" + Utils.QUERY_SOLID1_SOLID2))));
    }

    private boolean canAddLiquidSolid1() {
        return (queries.contains("#" + Utils.QUERY_PL12) &&
                queries.contains("#" + Utils.QUERY_PLV1) &&
                (!(queries.contains(Utils.QUERY_LIQUID_SOLID1) || queries.contains("#" + Utils.QUERY_LIQUID_SOLID1))));
    }

    private boolean canAddLiquidSolid2() {
        return (queries.contains("#" + Utils.QUERY_PL12) &&
                queries.contains("#" + Utils.QUERY_PLV2) &&
                (!(queries.contains(Utils.QUERY_LIQUID_SOLID2) || queries.contains("#" + Utils.QUERY_LIQUID_SOLID2))));
    }


    private boolean canPaintLiquidSolid1() {
        return (queries.contains("#" + Utils.QUERY_PLV1) &&
                (queries.contains(Utils.QUERY_LIQUID_SOLID1) || queries.contains("#" + Utils.QUERY_LIQUID_SOLID1)) &&
                (!queries.contains("#" + Utils.QUERY_CURVE_LIQUID_SOLID1)));
    }


    private boolean canPaintLiquidSolid2() {
        return (queries.contains("#" + Utils.QUERY_PLV2) &&
                (queries.contains(Utils.QUERY_LIQUID_SOLID2) || queries.contains("#" + Utils.QUERY_LIQUID_SOLID2)) &&
                (!queries.contains("#" + Utils.QUERY_CURVE_LIQUID_SOLID2)));
    }


    private boolean canPaintSolid1Solid2() {
        return (queries.contains("#" + Utils.QUERY_PV12) &&
                (queries.contains(Utils.QUERY_SOLID1_SOLID2) || queries.contains("#" + Utils.QUERY_SOLID1_SOLID2)) &&
                (!queries.contains("#" + Utils.QUERY_CURVE_SOLID1_SOLID2)));
    }

    private void removeCalculatedQueries() {
        int i = 0;
        while (i < queries.size()) {
            if (queries.get(i).charAt(0) == '#') {
                graphic.removeInfo(queries.get(i).substring(1));
                queries.remove(i);

            }
            else ++i;
        }
    }

    public void changeScale(double xMin, double xMax, double yMin, double yMax) {
        graphic.setScale(xMin, xMax, yMin, yMax);
    }

    public void autoScale() {
        graphic.autoScale();
    }

    public ArrayList<String> getCalculatedElements() {
        ArrayList<String> result = new ArrayList<>();
        for (String query: queries) {
            if (query.charAt(0) == '#') {
                switch (query.substring(1)) {
                    case Utils.QUERY_PLV1:
                        result.add(Utils.QUERY_PLV1 + ";" + String.format (Locale.ROOT,"%.2f",materialProperties.getTempLV1())
                               + ";" + String.format(Locale.ROOT,"%6.0e",materialProperties.getPressLV1()));
                        break;
                    case Utils.QUERY_PLV2:
                        result.add(Utils.QUERY_PLV2 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempLV2())
                               + ";"  + String.format(Locale.ROOT,"%6.0e",materialProperties.getPressLV2()));
                        break;
                    case Utils.QUERY_PL12:
                        result.add(Utils.QUERY_PL12 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempL12())
                               + ";"  + String.format(Locale.ROOT,"%6.0e",materialProperties.getPressL12()));
                        break;
                    case Utils.QUERY_PV12:
                        result.add(Utils.QUERY_PV12 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempV12())
                               + ";" + String.format(Locale.ROOT,"%6.0e",materialProperties.getPressV12()));
                        break;

                    case Utils.QUERY_LIQUID_VAPOR:
                        result.add(Utils.QUERY_LIQUID_VAPOR + ";" + materialProperties.getVaporLiquid().toString());
                        break;

                    case Utils.QUERY_VAPOR_SOLID1:
                        result.add(Utils.QUERY_VAPOR_SOLID1 + ";" + materialProperties.getVaporSolid1().toString());
                        break;

                    case Utils.QUERY_VAPOR_SOLID2:
                        result.add(Utils.QUERY_VAPOR_SOLID2 + ";" + materialProperties.getVaporSolid2().toString());
                        break;

                    case Utils.QUERY_LIQUID_SOLID1:
                        result.add(Utils.QUERY_LIQUID_SOLID1 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getLiquidSolid1()));
                        break;

                    case Utils.QUERY_LIQUID_SOLID2:
                        result.add(Utils.QUERY_LIQUID_SOLID2 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getLiquidSolid2()));
                        break;

                    case Utils.QUERY_SOLID1_SOLID2:
                        result.add(Utils.QUERY_SOLID1_SOLID2 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getSolid1Solid2()));
                        break;

                    case Utils.QUERY_TLV1:
                        result.add(Utils.QUERY_TLV1 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempLV1()));
                        break;

                    case Utils.QUERY_TLV2:
                        result.add(Utils.QUERY_TLV2 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempLV2()));
                        break;

                    case Utils.QUERY_TV12:
                        result.add(Utils.QUERY_TV12 + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempV12()));
                        break;
                    default:
                        break;

                }
            }
        }
        return result;
    }

    public String dataToString() {
        String data = materialProperties.getName();
        for(String query: queries) {
            if (query.charAt(0) != '#') {
                switch (query) {
                    case Utils.QUERY_LIQUID_VAPOR:
                        data += ";" + query + ";" + materialProperties.getVaporLiquid().toString();
                        break;

                    case Utils.QUERY_VAPOR_SOLID1:
                        data += ";" + query + ";" + materialProperties.getVaporSolid1().toString();
                        break;

                    case Utils.QUERY_VAPOR_SOLID2:
                        data += ";" + query + ";" + materialProperties.getVaporSolid2().toString();
                        break;
                    case Utils.QUERY_LIQUID_SOLID1:
                        data += ";" + query + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getLiquidSolid1());
                        break;

                    case Utils.QUERY_LIQUID_SOLID2:
                        data += ";" + query + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getLiquidSolid2());
                        break;

                    case Utils.QUERY_SOLID1_SOLID2:
                        data += ";" + query + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getSolid1Solid2());
                        break;
                    case Utils.QUERY_TLV1:
                        data += ";" + query + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempLV1());
                        break;

                    case Utils.QUERY_TLV2:
                        data += ";" + query + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempLV2());
                        break;

                    case Utils.QUERY_TV12:
                        data += ";" + query + ";" + String.format (Locale.ROOT,"%.2f", materialProperties.getTempV12());
                        break;

                }
            }
        }
        return data;
    }

    public boolean isMaterialComplete() {
        return (queries.size() == 17);
    }
}
