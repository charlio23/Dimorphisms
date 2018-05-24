package dimorphisms;


import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


import java.util.ArrayList;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.round;

public class DiagramGenerator {

    private MaterialProperties materialProperties;
    private ArrayList<Integer> stabilities;
    private boolean updated;

    /**
     * Topology of the dimorphism
     *  1: Enantiotropy
     *  -- 12V stable
     *  -- 12L stable
     *  2: Overall enantiotropy
     *  -- 12V stable
     *  -- 12L metastable
     *  3: Monotropy
     *  -- 12V metastable
     *  -- 12L stable
     *  4: Overall monotropy
     *  -- 12V metastable
     *  -- 12L metastable
     */

    private int topologyCase;

    /**
     * stability of triple points
     *  true if stable
     *  false if metastable
     */
    private boolean stabilityLV1;
    private boolean stabilityV12;
    private boolean stabilityL12;

    private LineChart<Number,Number> stableDiagramLinear;
    private ArrayList<XYChart.Series<Number,Number>> stableDiagramData;
    private LineChart<Number,Number> stableDiagramLogPositive;
    private LineChart<Number,Number> stableDiagramLogNegative;
    /**
     * Pre: materialProperties has to be completed.
     * Post: Stable Diagram is generated.
     * @param materialProperties material information
     */
    public DiagramGenerator(MaterialProperties materialProperties){
        this.materialProperties = materialProperties;
        this.updated = false;
    }

    public void setUnUpdated() {
        this.updated = false;
    }

    public int getTopologyCase() {
        return topologyCase;
    }

    public LineChart getStableDiagramLinear() {
        if (!updated) generateStableDiagram();
        return stableDiagramLinear;
    }

    public LineChart[] getStableDiagramLog() {
        return new LineChart[]{stableDiagramLogPositive, stableDiagramLogNegative};
    }

    private void generateStableDiagram(){
        stableDiagramData = new ArrayList<>();
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("T (K)");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("P (MPa)");
        stableDiagramLinear = new LineChart<>(xAxis,yAxis);
        stableDiagramLinear.setCreateSymbols(false);
        stableDiagramLinear.setLegendVisible(false);
        stableDiagramLinear.setAnimated(false);
        stabilities = new ArrayList<>();
        addLiquidVapor();
        addStabilityVapor();
        addStabilityLiquid();
        addTriplePoints();
        setTopology();
        stableDiagramLinear.getData().addAll(stableDiagramData);
        updateCss();
        updated = true;
    }

    private void addLiquidVapor() {
        //two cases
        if (materialProperties.getTempLV1() > materialProperties.getTempLV2()) {
            stabilityLV1 = true;
            addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporLiquid(), Utils.STABLE);
            addCurve(materialProperties.getTempLV2(),materialProperties.getTempLV1(),materialProperties.getVaporLiquid(), Utils.META_STABLE);
            addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempLV2(),materialProperties.getVaporLiquid(), Utils.SUPER_META_STABLE);
        } else {
            stabilityLV1 = false;
            addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporLiquid(), Utils.STABLE);
            addCurve(materialProperties.getTempLV1(),materialProperties.getTempLV2(),materialProperties.getVaporLiquid(), Utils.META_STABLE);
            addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempLV1(),materialProperties.getVaporLiquid(), Utils.SUPER_META_STABLE);
        }
    }

    private void addStabilityVapor() {
        if (stabilityLV1) {
            if (materialProperties.getTempLV1() > materialProperties.getTempV12()) {
                //V12 stable
                stabilityV12 = true;
                //add S1-V
                addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporSolid1(),Utils.META_STABLE);
                addCurve(materialProperties.getTempV12(),materialProperties.getTempLV1(),materialProperties.getVaporSolid1(),Utils.STABLE);
                addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempV12(),materialProperties.getVaporSolid1(),Utils.META_STABLE);
                //add S2-V
                addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporSolid2(),Utils.SUPER_META_STABLE);
                addCurve(materialProperties.getTempV12(),materialProperties.getTempLV2(),materialProperties.getVaporSolid2(),Utils.META_STABLE);
                addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempV12(),materialProperties.getVaporSolid2(),Utils.STABLE);
            } else {
                //V12 metastable
                stabilityV12 = false;
                //add S1-V
                addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempLV1(),materialProperties.getVaporSolid1(),Utils.STABLE);
                addCurve(materialProperties.getTempLV1(),materialProperties.getTempV12(),materialProperties.getVaporSolid1(),Utils.META_STABLE);
                addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporSolid1(),Utils.SUPER_META_STABLE);
                //add S2-V
                addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempLV2(),materialProperties.getVaporSolid2(),Utils.META_STABLE);
                addCurve(materialProperties.getTempLV2(),materialProperties.getTempV12(),materialProperties.getVaporSolid2(),Utils.SUPER_META_STABLE);
                addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporSolid2(),Utils.META_STABLE);
            }
        } else {
            if (materialProperties.getTempLV2() > materialProperties.getTempV12()) {
                //V12 stable
                stabilityV12 = true;
                //add S2-V
                addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporSolid2(),Utils.META_STABLE);
                addCurve(materialProperties.getTempV12(),materialProperties.getTempLV2(),materialProperties.getVaporSolid2(),Utils.STABLE);
                addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempV12(),materialProperties.getVaporSolid2(),Utils.META_STABLE);
                //add S1-V
                addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporSolid1(),Utils.SUPER_META_STABLE);
                addCurve(materialProperties.getTempV12(),materialProperties.getTempLV1(),materialProperties.getVaporSolid1(),Utils.META_STABLE);
                addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempV12(),materialProperties.getVaporSolid1(),Utils.STABLE);
            } else {
                //V12 metastable
                stabilityV12 = false;
                //add S2-V
                addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempLV2(),materialProperties.getVaporSolid2(),Utils.STABLE);
                addCurve(materialProperties.getTempLV2(),materialProperties.getTempV12(),materialProperties.getVaporSolid2(),Utils.META_STABLE);
                addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporSolid2(),Utils.SUPER_META_STABLE);
                //add S1-V
                addCurve(Utils.TEMPERATURE_ORIGIN,materialProperties.getTempLV1(),materialProperties.getVaporSolid1(),Utils.META_STABLE);
                addCurve(materialProperties.getTempLV1(),materialProperties.getTempV12(),materialProperties.getVaporSolid1(),Utils.SUPER_META_STABLE);
                addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_FINAL,materialProperties.getVaporSolid1(),Utils.META_STABLE);
            }
        }
    }

    private void addStabilityLiquid() {
        if (materialProperties.getPressL12() > 0) {
            stabilityL12 = true;
            if (stabilityV12) {
                //case 1
                //paint S1-S2
                addCurve(materialProperties.getTempV12(),materialProperties.getTempL12(), materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.STABLE);
                if (materialProperties.getTempV12() < materialProperties.getTempL12()) {
                    addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                    addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                } else {
                    addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                    addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                }
                if (stabilityLV1) {
                    //paint L-S1
                    addCurve(materialProperties.getTempLV1(),materialProperties.getTempL12(), materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                    if (materialProperties.getTempLV1() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    }
                    //paint L-S2
                    addCurve(materialProperties.getTempLV2(),materialProperties.getTempL12(), materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    if (materialProperties.getTempLV2() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                    }
                } else {
                    //paint L-S1
                    addCurve(materialProperties.getTempLV1(),materialProperties.getTempL12(), materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    if (materialProperties.getTempLV1() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                    }
                    //paint L-S2
                    addCurve(materialProperties.getTempLV2(),materialProperties.getTempL12(), materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                    if (materialProperties.getTempLV2() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    }
                }
            } else {
                //case 3
                // paint S1 - S2
                addCurve(materialProperties.getTempV12(),materialProperties.getTempL12(), materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                if (materialProperties.getTempV12() < materialProperties.getTempL12()) {
                    addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.SUPER_META_STABLE);
                    addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getSolid1Solid2(),Utils.STABLE);
                } else {
                    addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.SUPER_META_STABLE);
                    addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getSolid1Solid2(),Utils.STABLE);
                }
                if (stabilityLV1) {
                    //paint L-S1
                    addCurve(materialProperties.getTempLV1(),materialProperties.getTempL12(), materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                    if (materialProperties.getTempLV1() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    }
                    //paint L-S2
                    addCurve(materialProperties.getTempLV2(),materialProperties.getTempL12(), materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    if (materialProperties.getTempLV2() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                    }
                } else {
                    //paint L-S1
                    addCurve(materialProperties.getTempLV1(),materialProperties.getTempL12(), materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    if (materialProperties.getTempLV1() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                    }
                    //paint L-S2
                    addCurve(materialProperties.getTempLV2(),materialProperties.getTempL12(), materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                    if (materialProperties.getTempLV2() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    }
                }
            }
        } else {
            stabilityL12 = false;
            if (stabilityV12) {
                //case 2
                //paint S1-S2
                addCurve(materialProperties.getTempV12(),materialProperties.getTempL12(), materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                if (materialProperties.getTempV12() < materialProperties.getTempL12()) {
                    addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.STABLE);
                    addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getSolid1Solid2(),Utils.SUPER_META_STABLE);
                } else {
                    addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.STABLE);
                    addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getSolid1Solid2(),Utils.SUPER_META_STABLE);
                }
                if (stabilityLV1) {
                    //paint L-S1
                    addCurve(materialProperties.getTempLV1(),materialProperties.getTempL12(), materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    if (materialProperties.getTempLV1() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                    }
                    //paint L-S2
                    addCurve(materialProperties.getTempLV2(),materialProperties.getTempL12(), materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                    if (materialProperties.getTempLV2() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    }
                } else {
                    //paint L-S1
                    addCurve(materialProperties.getTempLV1(),materialProperties.getTempL12(), materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                    if (materialProperties.getTempLV1() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    }
                    //paint L-S2
                    addCurve(materialProperties.getTempLV2(),materialProperties.getTempL12(), materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    if (materialProperties.getTempLV2() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                    }
                }
            } else {
                //case 4
                // paint S1 - S2
                addCurve(materialProperties.getTempV12(),materialProperties.getTempL12(), materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.SUPER_META_STABLE);
                if (materialProperties.getTempV12() < materialProperties.getTempL12()) {
                    addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                    addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                } else {
                    addCurve(materialProperties.getTempV12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressV12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                    addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getSolid1Solid2(),Utils.META_STABLE);
                }
                if (stabilityLV1) {
                    //paint L-S1
                    addCurve(materialProperties.getTempLV1(),materialProperties.getTempL12(), materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    if (materialProperties.getTempLV1() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                    }
                    //paint L-S2
                    addCurve(materialProperties.getTempLV2(),materialProperties.getTempL12(), materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                    if (materialProperties.getTempLV2() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    }
                } else {
                    //paint L-S1
                    addCurve(materialProperties.getTempLV1(),materialProperties.getTempL12(), materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.SUPER_META_STABLE);
                    if (materialProperties.getTempLV1() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV1(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV1(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid1(),Utils.META_STABLE);
                    }
                    //paint L-S2
                    addCurve(materialProperties.getTempLV2(),materialProperties.getTempL12(), materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.META_STABLE);
                    if (materialProperties.getTempLV2() < materialProperties.getTempL12()) {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_FINAL, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                    } else {
                        addCurve(materialProperties.getTempLV2(),Utils.TEMPERATURE_FINAL, materialProperties.getPressLV2(),materialProperties.getLiquidSolid2(),Utils.STABLE);
                        addCurve(materialProperties.getTempL12(),Utils.TEMPERATURE_ORIGIN, materialProperties.getPressL12(),materialProperties.getLiquidSolid2(),Utils.SUPER_META_STABLE);
                    }
                }
            }
        }
    }

    private void addTriplePoints(){
        addPoint(materialProperties.getTempLV1(),materialProperties.getPressLV1());
        addPoint(materialProperties.getTempLV2(),materialProperties.getPressLV2());
        addPoint(materialProperties.getTempV12(),materialProperties.getPressV12());
        addPoint(materialProperties.getTempL12(),materialProperties.getPressL12());
    }

    private void addCurve(double tempIni, double tempFin, VaporSth curve, Integer stability) {
        double[] values = FuncHelper.getArrayFromVaporCurve(tempIni, tempFin, curve);
        XYChart.Series<Number,Number> seriesTotal = new XYChart.Series<>();
        double temperature = tempIni;
        int size = Utils.DATA_SIZE;
        double step;
        while (abs((tempFin - tempIni)/size) < Utils.MINIMUM_STEP) size = size/2;
        step = (tempFin - tempIni)/size;
        for (int i = 1; i < values.length-1; ++i) {
            if (values[i] > 1e6) break;
            temperature += step;
            XYChart.Data<Number, Number> point = new XYChart.Data<>(temperature, values[i]);
            seriesTotal.getData().add(point);
        }
        stableDiagramData.add(seriesTotal);
        stabilities.add(stability);
    }

    private void addCurve(double tempIni, double tempFin, double pressIni , double curve, Integer stability) {
        double[] values = FuncHelper.getArrayFromLine(tempIni, tempFin, pressIni, curve);
        XYChart.Series<Number,Number> seriesTotal = new XYChart.Series<>();
        double temperature = tempIni;
        int size = Utils.DATA_SIZE;
        double step;
        while (abs((tempFin - tempIni)/size) < Utils.MINIMUM_STEP) size = size/2;
        step = (tempFin - tempIni)/size;
        for (int i = 1; i < values.length-1; ++i) {
            temperature += step;
            XYChart.Data<Number, Number> point = new XYChart.Data<>(temperature, values[i]);
            seriesTotal.getData().add(point);

        }
        stableDiagramData.add(seriesTotal);
        stabilities.add(stability);
    }

    private void addPoint(double temp, double press) {
        XYChart.Series<Number,Number> seriesLinear = new XYChart.Series<>();
        seriesLinear.getData().add(new XYChart.Data<>(temp, press));
        stableDiagramData.add(seriesLinear);
    }

    private void setTopology() {
        if (stabilityV12) {
            if (stabilityL12) {
                topologyCase = 1;
            } else {
                topologyCase = 2;
            }
        } else {
            if (stabilityL12) {
                topologyCase = 3;
            } else {
                topologyCase = 4;
            }
        }
    }

    private void updateCss() {
        int i;
        for (i = 0; i < stabilities.size(); ++i) {
            int stability = stabilities.get(i);
            if (Utils.STABLE == stability) {
                stableDiagramLinear.getData().get(i).getNode().getStyleClass().add("stable");
            } else if (Utils.META_STABLE == stability) {
                stableDiagramLinear.getData().get(i).getNode().getStyleClass().add("meta-stable");
            } else if (Utils.SUPER_META_STABLE == stability) {
                stableDiagramLinear.getData().get(i).getNode().getStyleClass().add("super-meta-stable");
            }
        }
        while (i < stableDiagramLinear.getData().size()) {
            stableDiagramLinear.getData().get(i).getNode().getStyleClass().add("triple-point");
            ++i;
        }
    }

    public void setScale(double xMin, double xMax, double yMin, double yMax){
/*
        NumberAxis xAxis = (NumberAxis) stableDiagramLinear.getXAxis();
        NumberAxis yAxis = (NumberAxis) stableDiagramLinear.getYAxis();
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        xAxis.setLowerBound(xMin);
        xAxis.setUpperBound(xMax);
        yAxis.setLowerBound(yMin);
        yAxis.setUpperBound(yMax);
*/
        NumberAxis xAxis = new NumberAxis("T (K)",xMin,xMax,round((xMax - xMin)/10));
        NumberAxis yAxis = new NumberAxis("P (MPa)",yMin,yMax,round((yMax - yMin)/10));
        stableDiagramLinear = new LineChart<>(xAxis,yAxis);
        stableDiagramLinear.setCreateSymbols(false);
        stableDiagramLinear.setLegendVisible(false);
        stableDiagramLinear.setAnimated(false);
        stableDiagramLinear.getData().addAll(stableDiagramData);
        updateCss();


    }

    public void autoScale() {
        NumberAxis xAxis = (NumberAxis) stableDiagramLinear.getXAxis();
        NumberAxis yAxis = (NumberAxis) stableDiagramLinear.getYAxis();
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
    }
}
