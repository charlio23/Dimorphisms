package dimorphisms;


import javafx.scene.chart.LineChart;

public class DiagramGenerator {

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
    private boolean stabilityLV2;
    private boolean stabilityV12;
    private boolean stabilityL12;

    private LineChart<Number,Number> stableDiagramLinear;
    private LineChart<Number,Number> stableDiagramLogPositive;
    private LineChart<Number,Number> stableDiagramLogNegative;
    /**
     * Pre: materialProperties has to be completed.
     * Post: Stable Diagram is generated.
     * @param materialProperties material information
     */
    public DiagramGenerator(MaterialProperties materialProperties){
        /* TODO
         -- declare diagram
         -- add curves 1 by 1 (stable, metastable)
         */
    }

    public int getTopologyCase() {
        return topologyCase;
    }

    public LineChart getStableDiagramLinear() {
        return stableDiagramLinear;
    }

    public LineChart[] getStableDiagramLog() {
        return new LineChart[]{stableDiagramLogPositive, stableDiagramLogNegative};
    }

    private void addCurve(float initTemp, float finalTemp, int stable){

    }

}
