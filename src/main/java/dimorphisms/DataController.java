package dimorphisms;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * The type Data controller.
 */
public class DataController {

    private static String path = "database.txt";

    /**
     * Save material boolean.
     *
     * @param queryController the material
     * @return the boolean
     */
    public boolean saveMaterial(QueryController queryController) {
        Writer output;

        File f = new File(path);
        try {
            if(f.createNewFile()) {
                Utils.logger.log(Level.CONFIG, "database file not found, creating new one...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try(FileWriter fileWriter = new FileWriter(path,true)){
            output = new BufferedWriter(fileWriter);
            output.append(queryController.dataToString()).append("\n");
            output.close();
            return true;

        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * Load material material properties.
     *
     * @param name the name
     * @return the material properties
     */
    public String[] loadMaterial(String name) {
        File f = new File(path);
        try {
            if(f.createNewFile()) {
                Utils.logger.log(Level.CONFIG, "database file not found, creating new one...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] material = line.split(";");
                if (material[0].equals(name)) {
                    return material;
                }
            }
            return null;
        } catch (Exception e) {
            Utils.logger.log(Level.SEVERE,"The file 'users' could not be opened");
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
            if(f.createNewFile()) {
                Utils.logger.log(Level.CONFIG, "database file not found, creating new one...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] material = line.split(";");
                names.add(material[0]);
            }
            return names;
        } catch (Exception e) {
            Utils.logger.log(Level.SEVERE,"The file 'database' could not be opened");
            return null;
        }
    }

    public void deleteMaterial(String name) {
        File f = new File(path);
        try {
            if(f.createNewFile()) {
                Utils.logger.log(Level.CONFIG, "database file not found, creating new one...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            File tempFile = new File(f.getAbsolutePath() + ".tmp");
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] material = line.split(";");
                if (!material[0].equals(name)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            if (!f.delete()) {
                System.out.println("Could not delete file");

            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(f))
                System.out.println("Could not rename file");

        } catch (Exception e) {
            Utils.logger.log(Level.SEVERE,"The file 'database' could not be opened");
        }
    }
}
