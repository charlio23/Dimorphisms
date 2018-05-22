package dimorphisms;

import javafx.scene.chart.LineChart;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * The type Domain controller.
 */
/* TODO
    Update DomainController functionality to what it is supposed to do.
    Remind: DomainController -> Interacts with DB and Domain to resolve View queries.
    Change all structure.
 */
public class DomainController {

    private ArrayList<String> materialNames;
    private DataController dataController;
    private QueryController queryController;
    private DiagramGenerator diagramGenerator;
    /**
     * Instantiates a new Domain controller.
     */
    DomainController(){
        materialNames = new ArrayList<>();
        this.dataController = new DataController();
        diagramGenerator = null;
    }

    /**
     * List material names array list.
     *
     * @return the array list
     */
    public ArrayList<String> listMaterialNames(){
        return materialNames;
    }

    /**
     * New material boolean.
     *
     * @param materialName the material name
     * @return the boolean
     */
    public Boolean newMaterial(String materialName) {
        for (String name: materialNames) {
            if (materialName.equals(name)) return false;
        }
        queryController = new QueryController(materialName);
        return true;
    }

    public Object getLinearGraphic(boolean graphic) {
        if (graphic) {
            return queryController.getLinearGraphic();
        } else {
            if (diagramGenerator == null) {
                diagramGenerator = new DiagramGenerator(queryController.getMaterialProperties());
            }
            return diagramGenerator.getStableDiagramLinear();
        }
    }

    public LineChart[] getLogGraphic() {
        return queryController.getLogGraphic();
    }

    /**
     * Load material boolean.
     *
     * @param materialName the material name
     * @return the boolean
     */
    public Boolean loadMaterial(String materialName) {
        MaterialProperties actualMaterial = dataController.loadMaterial(materialName);
        /* TODO
        implement load queries
         */
        queryController = new QueryController(actualMaterial,new ArrayList<>());
        return true;
    }

    /**
     * Save material boolean.
     *
     * @return the boolean
     */
    public Boolean saveMaterial() {
        return dataController.saveMaterial("");
    }

    /**
     * Load material names array list.
     *
     * @return the array list
     */
    public ArrayList<String> loadMaterialNames() {
        return dataController.loadMaterialNames();
    }

    public ArrayList<String> addLiquidVapor(float a, float b, float c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        unUpdateDiagram();
        return queryController.makeQueryVaporSth(Utils.QUERY_LIQUID_VAPOR,eqCurve);

    }

    public ArrayList<String> addVaporSolid1(float a, float b, float c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        unUpdateDiagram();
        return queryController.makeQueryVaporSth(Utils.QUERY_VAPOR_SOLID1,eqCurve);

    }

    public ArrayList<String> addVaporSolid2(float a, float b, float c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        unUpdateDiagram();
        return queryController.makeQueryVaporSth(Utils.QUERY_VAPOR_SOLID2,eqCurve);

    }

    public ArrayList<String> addTempLV1(float temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_TLV1,temp);

    }

    public ArrayList<String> addTempLV2(float temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_TLV2,temp);

    }

    public ArrayList<String> addTempV12(float temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_TV12,temp);

    }

    public ArrayList<String> addLiquidSolid1(float temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_LIQUID_SOLID1,temp);

    }

    public ArrayList<String> addLiquidSolid2(float temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_LIQUID_SOLID2,temp);

    }

    public ArrayList<String> addSolid1Solid2(float temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_SOLID1_SOLID2,temp);

    }

    public void changeScale(double xMin, double xMax, double yMin, double yMax, boolean graphic) {
        if (graphic) {
            queryController.changeScale(xMin, xMax, yMin, yMax);
        } else {
            diagramGenerator.setScale(xMin, xMax, yMin, yMax);
        }
    }

    public void autoScale(boolean graphic) {
        if (graphic) {
            queryController.autoScale();
        } else {
            diagramGenerator.autoScale();
        }
    }

    private void unUpdateDiagram() {
        if (diagramGenerator != null) diagramGenerator.setUnUpdated();
    }

    public int getTopology() {
        return diagramGenerator.getTopologyCase();
    }
}
