package dimorphisms;

import java.io.*;
import java.util.ArrayList;

/**
 * The type Data controller.
 */
public class DataController {

    private static String path = "materialList.txt";

    /**
     * Save material boolean.
     *
     * @param material the material
     * @return the boolean
     */
    public boolean saveMaterial(String material) {
        Writer output;

        File f = new File(path);
        try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try{
            output = new BufferedWriter(new FileWriter(path, true));
            output.append(material).append("\n");
            output.close();
            return true;

        }
        catch(Throwable t) {
            System.out.println("no s'ha pogut guardar l'usuari");
            return false;
        } finally {

        }
    }

    /**
     * Load material material properties.
     *
     * @param name the name
     * @return the material properties
     */
    public MaterialProperties loadMaterial(String name) {
        File f = new File(path);
        try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] material = line.split(" ");
                if (material[0].equals(name)) {
                    return stringToMaterial(material);
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("The file 'users' could not be opened");
            return null;
        }
    }

    /**
     * Load material names array list.
     *
     * @return the array list
     */
    public ArrayList<String> loadMaterialNames() {
        ArrayList<String> names = new ArrayList<>();
        File f = new File(path);
        try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] material = line.split(" ");
                names.add(material[0]);
            }
            return null;
        } catch (Exception e) {
            System.out.println("The file 'users' could not be opened");
            return null;
        }
    }

    private MaterialProperties stringToMaterial(String[] material) {
        return new MaterialProperties("FOUND");
    }
}
