package dimorphisms;

import java.util.ArrayList;

/**
 * The type Domain controller.
 */
public class DomainController {

    private MaterialProperties actualMaterial;
    private ArrayList<String> materialNames;
    private DataController dataController;

    /**
     * Instantiates a new Domain controller.
     */
    DomainController(){
        actualMaterial = null;
        materialNames = new ArrayList<>();
        this.dataController = new DataController();
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
        actualMaterial = new MaterialProperties(materialName);
        return true;
    }

    /**
     * Load material boolean.
     *
     * @param materialName the material name
     * @return the boolean
     */
    public Boolean loadMaterial(String materialName) {
        actualMaterial = dataController.loadMaterial(materialName);
        return true;
    }

    /**
     * Save material boolean.
     *
     * @return the boolean
     */
    public Boolean saveMaterial() {
        return dataController.saveMaterial(actualMaterial.dataToString());
    }

    /**
     * Load material names array list.
     *
     * @return the array list
     */
    public ArrayList<String> loadMaterialNames() {
        return dataController.loadMaterialNames();
    }

}
