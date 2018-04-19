package dimorphisms;

import javafx.scene.chart.LineChart;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.log;

/* TODO
    New Structure:
     QueryController -> Father of the domain. Keeps track of program's state.
     This is the program nucleus, all main calls should be processed here.
 */

public class QueryController {

    private List<String> queries;
    private MaterialProperties materialProperties;
    private GraphicHelper graphic;
    private FuncHelper funcHelper;

    public QueryController(String name, LineChart graphic) {
        queries = new ArrayList<>();
        materialProperties = new MaterialProperties(name);
        this.graphic = new GraphicHelper(graphic);
        funcHelper = new FuncHelper(materialProperties);
    }

    public QueryController(MaterialProperties materialProperties, List<String> queries) {
        this.materialProperties = materialProperties;
        this.queries = queries;
        /* TODO
        Make queries to update graphic
         */
    }

    public String makeQueryVaporSth(String query, VaporSth eqCurve) {
        //Check if we have conflicts
        if (queries.contains("#" + query)) {
            return Utils.QUERY_ERROR_CONFLICT;
        }
        if (queries.contains(query)) {
            removeCalculatedQueries();
            queries.remove(query);
        }
        switch (query) {
            case Utils.QUERY_LIQUID_VAPOR:
                addLiquidVapor(eqCurve,true);
                return Utils.QUERY_SUCCESS;

            case Utils.QUERY_VAPOR_SOLID1:
                addVaporSolid1(eqCurve,true);
                return Utils.QUERY_SUCCESS;

            case Utils.QUERY_VAPOR_SOLID2:
                addVaporSolid2(eqCurve,true);
                return Utils.QUERY_SUCCESS;

            default:
                return Utils.QUERY_ERROR_UNEXPECTED;
        }

    }

    public String makeQueryOther(String query, double value) {
        //Check if we have conflicts
        if (queries.contains("#" + query)) {
            return Utils.QUERY_ERROR_CONFLICT;
        }
        if (queries.contains(query)) {
            removeCalculatedQueries();
            queries.remove(query);
        }
        switch (query) {
            case Utils.QUERY_LIQUID_SOLID1:
                addLiquidSolid1(value, true);
                return Utils.QUERY_SUCCESS;

            case Utils.QUERY_LIQUID_SOLID2:
                addLiquidSolid2(value, true);
                return Utils.QUERY_SUCCESS;

            case Utils.QUERY_SOLID1_SOLID2:
                addSolid1Solid2(value, true);
                return Utils.QUERY_SUCCESS;

            case Utils.QUERY_TLV1:
                addTLV1(value, true);
                return Utils.QUERY_SUCCESS;

            case Utils.QUERY_TLV2:
                addTLV2(value, true);
                return Utils.QUERY_SUCCESS;

            case Utils.QUERY_TV12:
                addTV12(value, true);
                return Utils.QUERY_SUCCESS;

            default:
                return Utils.QUERY_ERROR_UNEXPECTED;

        }
    }

    public LineChart getGraphic() {
        return graphic.getLinearGraphic();
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
            queries.add(Utils.QUERY_LIQUID_SOLID1);
        } else {
            queries.add("#" + Utils.QUERY_LIQUID_SOLID1);
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
        checkIfWeCanAdd();
    }

    private void addPLV1(double press) {
        queries.add("#" + Utils.QUERY_PLV1);
        materialProperties.setPressLV1(press);
        graphic.addPoint(Utils.QUERY_TLV1,materialProperties.getTempLV1(),press);
    }

    private void addPLV2(double press) {
        queries.add("#" + Utils.QUERY_PLV2);
        materialProperties.setPressLV2(press);
        graphic.addPoint(Utils.QUERY_TLV2,materialProperties.getTempLV2(),press);

    }

    private void addPV12(double press) {
        queries.add("#" + Utils.QUERY_PV12);
        materialProperties.setPressV12(press);
        graphic.addPoint(Utils.QUERY_TV12,materialProperties.getTempV12(),press);

    }

    private void addPL12(double press) {
        queries.add("#" + Utils.QUERY_PL12);
        materialProperties.setPressL12(press);
        graphic.addPoint(Utils.QUERY_TV12,materialProperties.getTempL12(),press);

    }

    private void addCurveLiquidSolid1() {
        queries.add("#" + Utils.QUERY_CURVE_LIQUID_SOLID1);
        /* TODO
        Add to graphic
         */
    }

    private void addCurveLiquidSolid2() {
        queries.add("#" + Utils.QUERY_CURVE_LIQUID_SOLID2);
        /* TODO
        Add to graphic
         */
    }

    private void addCurveSolid1Solid2() {
        queries.add("#" + Utils.QUERY_CURVE_SOLID1_SOLID2);
        /* TODO
        Add to graphic
         */
    }


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
            addLiquidSolid1(funcHelper.calculateDpdtLiquidSolid2(),false);
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
                queries.contains("#" + Utils.QUERY_CURVE_LIQUID_SOLID2));
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

    /* TODO
    check this method performance
     */
    private void removeCalculatedQueries() {
        int i = 0;
        while (i < queries.size() - 1) {
            if (queries.get(i).charAt(0) == '#') {
                graphic.removeInfo(queries.get(i).substring(1));
                queries.remove(i);

            }
            ++i;
        }
    }
/*
    void recalcula(){
        if (v1vA != 0 && v2vA != 0 && v12t == 0) {
            if ((v1log.isSelected() && v2log.isSelected()) || (v1ln.isSelected() && v2ln.isSelected())){
                v12t = (v1vB-v2vB)/(v1vA-v2vA);
                if (v1log.isSelected()) v12p = pow(10,v1vA - v1vB/v12t);
                else v12p = exp(v1vA - v1vB/v12t);
            }else if (v1log.isSelected() && v2ln.isSelected()){
                v12t = (v1vB*log(10)-v2vB)/(v1vA*log(10)-v2vA);
                v12p = exp(v2vA - v2vB/v12t);
            }else{
                v12t = (v1vB-v2vB*log(10))/(v1vA-v2vA*log(10));
                v12p = exp(v1vA - v1vB/v12t);
            }
            grafic.agregarPunt("v12", v12t, v12p);
            grafic.esPunt(num);
            ++num;
            info12v.setText("12V: T = " + String.valueOf(v12t) + " K, P = " + String.valueOf(v12p) + " Pa");
            recalcula();
        }
        else if (v1vA != 0 && vlvA != 0 && lv1t == 0) {
            if ((v1log.isSelected() && lvlog.isSelected()) || (v1ln.isSelected() && lvln.isSelected())){
                lv1t = (v1vB-vlvB)/(v1vA-vlvA);
                if (v1log.isSelected()) lv1p = pow(10,v1vA - v1vB/lv1t);
                else lv1p = exp(v1vA - v1vB/lv1t);
            }else if (v1log.isSelected() && lvln.isSelected()){
                lv1t = (v1vB*log(10)-vlvB)/(v1vA*log(10)-vlvA);
                lv1p = exp(vlvA - vlvB/lv1t);
            }else{
                lv1t = (v1vB-vlvB*log(10))/(v1vA-vlvA*log(10));
                lv1p = exp(v1vA - v1vB/lv1t);
            }
            grafic.agregarPunt("lv1", lv1t, lv1p);
            grafic.esPunt(num);
            ++num;
            info1lv.setText("1LV: T = " + String.valueOf(lv1t) + " K, P = " + String.valueOf(lv1p) + " Pa");
            recalcula();
        }
        else if (vlvA != 0 && v2vA != 0 && lv2t == 0) {
            if ((lvlog.isSelected() && v2log.isSelected()) || (lvln.isSelected() && v2ln.isSelected())){
                lv2t = (vlvB-v2vB)/(vlvA-v2vA);
                if (lvlog.isSelected()) lv2p = pow(10,vlvA - vlvB/lv2t);
                else lv2p = exp(vlvA - vlvB/lv2t);
            }else if (lvlog.isSelected() && v2ln.isSelected()){
                lv2t = (vlvB*log(10)-v2vB)/(vlvA*log(10)-v2vA);
                lv2p = exp(v2vA - v2vB/lv2t);
            }else{
                lv2t = (vlvB-v2vB*log(10))/(vlvA-v2vA*log(10));
                lv2p = exp(v2vA - v2vB/lv2t);
            }
            grafic.agregarPunt("lv2", lv2t, lv2p);
            grafic.esPunt(num);
            ++num;
            info2lv.setText("2LV: T = " + String.valueOf(lv2t) + " K, P = "+ String.valueOf(lv2p)+ " Pa");
            recalcula();
        }
        else if (lv1t != 0 && lv2t != 0 && vlvA == 0){
            lvln.setSelected(true);
            vlvB = (log(lv1p/lv2p))/((1/lv2t)-(1/lv1t));
            vlvA = log(lv1p) + vlvB/lv1t;
            lvA.setText(String.valueOf(vlvA));
            lvB.setText(String.valueOf(vlvB));
            String s = "e^(" + String.valueOf(vlvA) + " - " + String.valueOf(vlvB) + "/x)";
            Funcio f = new Funcio(s);
            double x[] = f.rang(4, 1000, 1);
            double y[] = f.eval(x);
            grafic.agregarGrafic("lv", x, y);
            grafic.esRecta(num);
            ++num;
            recalcula();
        }

        else if(v12t != 0 && lv1t != 0 && v1vA == 0){
            v1ln.setSelected(true);
            v1vB = (log(lv1p/v12p))/((1/v12t)-(1/lv1t));
            v1vA = log(lv1p) + v1vB/lv1t;
            v1A.setText(String.valueOf(v2vA));
            v1B.setText(String.valueOf(v2vB));
            String s = "e^(" + String.valueOf(v1vA) + " - " + String.valueOf(v1vB) + "/x)";
            Funcio f = new Funcio(s);
            double x[] = f.rang(4, 1000, 1);
            double y[] = f.eval(x);
            grafic.agregarGrafic("v1", x, y);
            grafic.esRecta(num);
            ++num;
            recalcula();
        }
        else if(v12t != 0 && lv2t != 0 && v2vA == 0){
            v2ln.setSelected(true);
            v2vB = (log(lv2p/v12p))/((1/v12t)-(1/lv2t));
            v2vA = log(lv2p) + v2vB/lv2t;
            v2A.setText(String.valueOf(v2vA));
            v2B.setText(String.valueOf(v2vB));
            String s = "e^(" + String.valueOf(v2vA) + " - " + String.valueOf(v2vB) + "/x)";
            Funcio f = new Funcio(s);
            double x[] = f.rang(4, 1000, 1);
            double y[] = f.eval(x);
            grafic.agregarGrafic("v2", x, y);
            grafic.esRecta(num);
            ++num;
            recalcula();
        }
        else if (pl1 != 0 && pl2 != 0 && l12t == 0){
            l12t = ((pl1*lv1t) - (pl2*lv2t) + lv2p - lv1p)/(pl1-pl2);
            l12p = pl1*l12t - pl1*lv1t + lv1p;
            grafic.agregarPunt("l12", l12t, l12p);
            grafic.esPunt(num);
            ++num;
            info12l.setText("12L: T = " + String.valueOf(l12t) + " K, P = " + String.valueOf(l12p) + " Pa");
            recalcula();
        }
        else if (l12t != 0 && v12t != 0 && p12 == 0){
            p12 = (l12p - v12p)/(l12t - v12t);
            String s = String.valueOf(p12) + "*x - " + String.valueOf(p12) +
                    "*" + String.valueOf(l12t) + " +" + String.valueOf(l12p);
            Funcio f = new Funcio(s);
            double x[] = f.rang(0,1000,100);
            double y[] = f.eval(x);
            grafic.agregarGrafic("12", x, y);
            grafic.esRecta(num);
            ++num;
            recalcula();
        }
        else if (l12t != 0 && lv1t != 0 && pl1 == 0){
            pl1 = (l12p - lv1p)/(l12t - lv1t);
            String s = String.valueOf(pl1) + "*x - " + String.valueOf(pl1) +
                    "*" + String.valueOf(l12t) + " +" + String.valueOf(l12p);
            Funcio f = new Funcio(s);
            double x[] = f.rang(0,1000,100);
            double y[] = f.eval(x);
            grafic.agregarGrafic("l1", x, y);
            grafic.esRecta(num);
            ++num;
            recalcula();
        }
        else if (l12t != 0 && lv2t != 0 && pl2 == 0){
            pl2 = (l12p - lv2p)/(l12t - lv2t);
            String s = String.valueOf(pl2) + "*x - " + String.valueOf(pl2) +
                    "*" + String.valueOf(l12t) + " +" + String.valueOf(l12p);
            Funcio f = new Funcio(s);
            double x[] = f.rang(0,1000,100);
            double y[] = f.eval(x);
            grafic.agregarGrafic("l2", x, y);
            grafic.esRecta(num);
            ++num;
            recalcula();
        }
    }
*/


}
