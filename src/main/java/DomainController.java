import java.util.ArrayList;

public class DomainController {

    private MaterialProperties actualMaterial;
    private ArrayList<MaterialProperties> materials;

    DomainController(){
        actualMaterial = null;
        materials = new ArrayList<>();
    }

    public ArrayList<String> listMaterialNames(){
        ArrayList<String> names = new ArrayList<>();
        for (MaterialProperties material : materials) {
            names.add(material.getName());
        }
        return names;
    }

    public Boolean createMaterial(String name) {
        for (MaterialProperties material: materials) {
            if (name.equals(material.getName())) return false;
        }
        actualMaterial = new MaterialProperties(name);
        return true;
    }

    public Boolean setActualMaterial(String name) {
        for (MaterialProperties material: materials) {
            if (name.equals(material.getName())) {
                actualMaterial = material;
                return true;
            }
        }
        return false;
    }

    public Boolean saveMaterial() {


        return true;
    }
}
