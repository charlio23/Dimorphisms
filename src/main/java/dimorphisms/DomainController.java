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
        this.dataController = new DataController();
        this.materialNames = dataController.loadMaterialNames();
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

    public boolean checkMaterialName (String materialName) {
        for (String name: materialNames) {
            if (materialName.equals(name)) return false;
        }
        return true;
    }
    /**
     * New material boolean.
     *
     * @param materialName the material name
     * @return the boolean
     */
    public void newMaterial(String materialName) {
        queryController = new QueryController(materialName);
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
    public ArrayList<String> loadMaterial(String materialName) {
        String[] loadedData = dataController.loadMaterial(materialName);
        queryController = new QueryController(materialName);
        return parseQueryController(loadedData);
    }

    private ArrayList<String> parseQueryController(String[] loadedData) {
        int i = 1;
        ArrayList<String> result = new ArrayList<>();
        while (i < loadedData.length) {
            //always look at the object once ( that's why i++ ...)
            double a,b,c;
            boolean isLog;
            String data;
            switch (loadedData[i++]) {
                case Utils.QUERY_LIQUID_VAPOR:
                    data = Utils.QUERY_LIQUID_VAPOR + ";" + loadedData[i]
                            + ";" + loadedData[i+1] + ";" + loadedData[i+2]
                            + ";" + loadedData[i+3];
                    result.add(data);
                    a = Double.parseDouble(loadedData[i++]);
                    b = Double.parseDouble(loadedData[i++]);
                    c = Double.parseDouble(loadedData[i++]);
                    isLog = Boolean.valueOf(loadedData[i++]);
                    addLiquidVapor(a,b,c,isLog);
                    break;

                case Utils.QUERY_VAPOR_SOLID1:
                    data = Utils.QUERY_VAPOR_SOLID1 + ";" + loadedData[i]
                            + ";" + loadedData[i+1] + ";" + loadedData[i+2]
                            + ";" + loadedData[i+3];
                    result.add(data);
                    a = Double.parseDouble(loadedData[i++]);
                    b = Double.parseDouble(loadedData[i++]);
                    c = Double.parseDouble(loadedData[i++]);
                    isLog = Boolean.valueOf(loadedData[i++]);
                    addVaporSolid1(a,b,c,isLog);
                    break;

                case Utils.QUERY_VAPOR_SOLID2:
                    data = Utils.QUERY_VAPOR_SOLID2 + ";" + loadedData[i]
                            + ";" + loadedData[i+1] + ";" + loadedData[i+2]
                            + ";" + loadedData[i+3];
                    result.add(data);
                    a = Double.parseDouble(loadedData[i++]);
                    b = Double.parseDouble(loadedData[i++]);
                    c = Double.parseDouble(loadedData[i++]);
                    isLog = Boolean.valueOf(loadedData[i++]);
                    addVaporSolid2(a,b,c,isLog);
                    break;

                case Utils.QUERY_LIQUID_SOLID1:
                    data = Utils.QUERY_LIQUID_SOLID1 + ";" + loadedData[i];
                    result.add(data);
                    addLiquidSolid1(Double.parseDouble(loadedData[i++]));
                    break;

                case Utils.QUERY_LIQUID_SOLID2:
                    data = Utils.QUERY_LIQUID_SOLID2 + ";" + loadedData[i];
                    result.add(data);
                    addLiquidSolid2(Double.parseDouble(loadedData[i++]));
                    break;

                case Utils.QUERY_SOLID1_SOLID2:
                    data = Utils.QUERY_SOLID1_SOLID2 + ";" + loadedData[i];
                    result.add(data);
                    addSolid1Solid2(Double.parseDouble(loadedData[i++]));
                    break;

                case Utils.QUERY_TLV1:
                    data = Utils.QUERY_TLV1 + ";" + loadedData[i];
                    result.add(data);
                    addTempLV1(Double.parseDouble(loadedData[i++]));
                    break;

                case Utils.QUERY_TLV2:
                    data = Utils.QUERY_TLV2 + ";" + loadedData[i];
                    result.add(data);
                    addTempLV2(Double.parseDouble(loadedData[i++]));
                    break;

                case Utils.QUERY_TV12:
                    data = Utils.QUERY_TV12 + ";" + loadedData[i];
                    result.add(data);
                    addTempV12(Double.parseDouble(loadedData[i++]));
                    break;
            }
        }
        return result;
    }

    /**
     * Save material boolean.
     *
     * @return the boolean
     */
    public Boolean saveMaterial() {
        if (dataController.saveMaterial(queryController)) {
            if (checkMaterialName(queryController.getMaterialProperties().getName())) {
                materialNames.add(queryController.getMaterialProperties().getName());
            }
            return true;
        } else return false;
    }

    public ArrayList<String> addLiquidVapor(double a, double b, double c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        unUpdateDiagram();
        return queryController.makeQueryVaporSth(Utils.QUERY_LIQUID_VAPOR,eqCurve);

    }

    public ArrayList<String> addVaporSolid1(double a, double b, double c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        unUpdateDiagram();
        return queryController.makeQueryVaporSth(Utils.QUERY_VAPOR_SOLID1,eqCurve);

    }

    public ArrayList<String> addVaporSolid2(double a, double b, double c, boolean isLog) {
        VaporSth eqCurve = new VaporSth(a,b,c,isLog);
        unUpdateDiagram();
        return queryController.makeQueryVaporSth(Utils.QUERY_VAPOR_SOLID2,eqCurve);

    }

    public ArrayList<String> addTempLV1(double temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_TLV1,temp);

    }

    public ArrayList<String> addTempLV2(double temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_TLV2,temp);

    }

    public ArrayList<String> addTempV12(double temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_TV12,temp);

    }

    public ArrayList<String> addLiquidSolid1(double temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_LIQUID_SOLID1,temp);

    }

    public ArrayList<String> addLiquidSolid2(double temp) {
        unUpdateDiagram();
        return queryController.makeQueryOther(Utils.QUERY_LIQUID_SOLID2,temp);

    }

    public ArrayList<String> addSolid1Solid2(double temp) {
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

    public void deleteMaterial(String name) {
        dataController.deleteMaterial(name);
        materialNames.remove(name);
    }

    public ArrayList<String> getUIInformation() {
        return queryController.getCalculatedElements();
    }

    public boolean isMaterialComplete() {
        return queryController.isMaterialComplete();
    }
}
