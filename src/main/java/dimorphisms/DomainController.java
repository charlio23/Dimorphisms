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

    public LineChart getLinearGraphic(boolean graphic) {
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

    public void addLiquidVapor(float a, float b, float c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        String s = queryController.makeQueryVaporSth(Utils.QUERY_LIQUID_VAPOR,eqCurve);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
    }

    public void addVaporSolid1(float a, float b, float c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        String s = queryController.makeQueryVaporSth(Utils.QUERY_VAPOR_SOLID1,eqCurve);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
    }

    public void addVaporSolid2(float a, float b, float c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        String s = queryController.makeQueryVaporSth(Utils.QUERY_VAPOR_SOLID2,eqCurve);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
    }

    public void addTempLV1(float temp) {
        String s = queryController.makeQueryOther(Utils.QUERY_TLV1,temp);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
    }

    public void addTempLV2(float temp) {
        String s = queryController.makeQueryOther(Utils.QUERY_TLV2,temp);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
    }

    public void addTempV12(float temp) {
        String s = queryController.makeQueryOther(Utils.QUERY_TV12,temp);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
    }

    public void addLiquidSolid1(float temp) {
        String s = queryController.makeQueryOther(Utils.QUERY_LIQUID_SOLID1,temp);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
    }

    public void addLiquidSolid2(float temp) {
        String s = queryController.makeQueryOther(Utils.QUERY_LIQUID_SOLID2,temp);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
    }

    public void addSolid1Solid2(float temp) {
        String s = queryController.makeQueryOther(Utils.QUERY_SOLID1_SOLID2,temp);
        Utils.logger.log(Level.INFO,s);
        unUpdateDiagram();
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
