package dimorphisms;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

/**
 * The type Data entry.
 */
public class DataEntry {

    @FXML private Label materialName;
    @FXML private TextField liquidVaporA;
    @FXML private TextField liquidVaporB;
    @FXML private TextField liquidVaporC;
    @FXML private TextField solid1VaporA;
    @FXML private TextField solid1VaporB;
    @FXML private TextField solid1VaporC;
    @FXML private TextField solid2VaporA;
    @FXML private TextField solid2VaporB;
    @FXML private TextField solid2VaporC;
    @FXML private RadioButton liquidVaporLog;
    @FXML private RadioButton liquidVaporLn;
    @FXML private RadioButton solid1VaporLog;
    @FXML private RadioButton solid1VaporLn;
    @FXML private RadioButton solid2VaporLog;
    @FXML private RadioButton solid2VaporLn;
    @FXML private TextField liquidSolid1VaporTemp;
    @FXML private TextField liquidSolid2VaporTemp;
    @FXML private TextField solid1Solid2VaporTemp;
    @FXML private TextField liquidSolid1dpdt;
    @FXML private TextField liquidSolid2dpdt;
    @FXML private TextField solid1Solid2dpdt;




    private ViewController viewController;

    /**
     * Set view controller.
     *
     * @param viewController the view controller
     */
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Sets material name.
     *
     * @param materialName the material name
     */
    public void setMaterialName(String materialName) {
        this.materialName.setText(materialName);
    }

    /**
     * On liquid vapor log pressed.
     */
    @FXML
    public void onLiquidVaporLogPressed() {
        if (liquidVaporLn.isSelected()) {
            liquidVaporLn.setSelected(false);
        }
    }

    /**
     * On liquid vapor ln pressed.
     */
    @FXML
    public void onLiquidVaporLnPressed() {
        if (liquidVaporLog.isSelected()) {
            liquidVaporLog.setSelected(false);
        }
    }

    /**
     * On solid 1 vapor log pressed.
     */
    @FXML
    public void onSolid1VaporLogPressed() {
        if (solid1VaporLn.isSelected()) {
            solid1VaporLn.setSelected(false);
        }
    }

    /**
     * On solid 1 vapor ln pressed.
     */
    @FXML
    public void onSolid1VaporLnPressed() {
        if (solid1VaporLog.isSelected()) {
            solid1VaporLog.setSelected(false);
        }
    }

    /**
     * On solid 2 vapor log pressed.
     */
    @FXML
    public void onSolid2VaporLogPressed() {
        if (solid2VaporLn.isSelected()) {
            solid2VaporLn.setSelected(false);
        }
    }

    /**
     * On solid 2 vapor ln pressed.
     */
    @FXML
    public void onSolid2VaporLnPressed() {
        if (solid2VaporLog.isSelected()) {
            solid2VaporLog.setSelected(false);
        }
    }

    /**
     * On liquid vapor add pressed.
     */
    @FXML
    public void onLiquidVaporAddPressed() {
        if (!liquidVaporLn.isSelected() && !liquidVaporLog.isSelected()) {
            showErrorDialogText(Utils.LIQUID_VAPOR_ERR,
                    "You must choose whether it is a ln or a log curve type.");
        } else if (!checkTextIsCorrect(liquidVaporA.getText())){
            showErrorDialogText(Utils.LIQUID_VAPOR_ERR,"A field has to be a number.");
        } else if (!checkTextIsCorrect(liquidVaporB.getText())){
            showErrorDialogText(Utils.LIQUID_VAPOR_ERR,"B field has to be a number.");
        } else if (!checkTextIsCorrect(liquidVaporC.getText())){
            showErrorDialogText(Utils.LIQUID_VAPOR_ERR,"C field has to be a number.");
        } else {
            System.out.println("Call to view");

        }

        float a = Float.parseFloat(liquidVaporA.getText());
        float b = Float.parseFloat(liquidVaporB.getText());
        float c = Float.parseFloat(liquidVaporC.getText());
        boolean isLog = liquidVaporLog.isSelected();
        viewController.addLiquidVapor(a, b, c, isLog);
    }

    /**
     * On solid 1 vapor add pressed.
     */
    @FXML
    public void onSolid1VaporAddPressed() {
        if (!solid1VaporLn.isSelected() && !solid1VaporLog.isSelected()) {
            showErrorDialogText(Utils.SOLID_1_VAPOR_ERR,
                    "You must choose whether it is a ln or a log curve type.");
        } else if (!checkTextIsCorrect(solid1VaporA.getText())){
            showErrorDialogText(Utils.SOLID_1_VAPOR_ERR,"A field has to be a number.");
        } else if (!checkTextIsCorrect(solid1VaporB.getText())){
            showErrorDialogText(Utils.SOLID_1_VAPOR_ERR,"B field has to be a number.");
        } else if (!checkTextIsCorrect(solid1VaporC.getText())){
            showErrorDialogText(Utils.SOLID_1_VAPOR_ERR,"C field has to be a number.");
        } else {
            System.out.println("Call to view");

        }

        float a = Float.parseFloat(solid1VaporA.getText());
        float b = Float.parseFloat(solid1VaporB.getText());
        float c = Float.parseFloat(solid1VaporC.getText());
        boolean isLog = solid1VaporLog.isSelected();
        viewController.addSolid1Vapor(a, b, c, isLog);
    }

    /**
     * On solid 2 vapor add pressed.
     */
    @FXML
    public void onSolid2VaporAddPressed() {
        if (!solid2VaporLn.isSelected() && !solid2VaporLog.isSelected()) {
            showErrorDialogText(Utils.SOLID_2_VAPOR_ERR,
                    "You must choose whether it is a ln or a log curve type.");
        } else if (!checkTextIsCorrect(solid2VaporA.getText())){
            showErrorDialogText(Utils.SOLID_2_VAPOR_ERR,"A field has to be a number.");
        } else if (!checkTextIsCorrect(solid2VaporB.getText())){
            showErrorDialogText(Utils.SOLID_2_VAPOR_ERR,"B field has to be a number.");
        } else if (!checkTextIsCorrect(solid2VaporC.getText())){
            showErrorDialogText(Utils.SOLID_2_VAPOR_ERR,"C field has to be a number.");
        } else {
            System.out.println("Call to view");
        }

        float A = Float.parseFloat(solid2VaporA.getText());
        float B = Float.parseFloat(solid2VaporB.getText());
        float C = Float.parseFloat(solid2VaporC.getText());
        boolean isLog = solid2VaporLog.isSelected();
        viewController.addSolid2Vapor(A, B, C, isLog);
    }

    /**
     * On liquid solid 1 add pressed.
     */
    @FXML
    public void onLiquidSolid1AddPressed() {
        if (!checkTextIsCorrect(liquidSolid1VaporTemp.getText())) {
            showErrorDialogText(Utils.LIQUID_SOLID_1_ERR,"Temperature has to be a number.");
        }
        else if (!checkTextIsCorrect(liquidSolid1dpdt.getText())) {
            showErrorDialogText(Utils.LIQUID_SOLID_1_ERR, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }

        float temp = Float.parseFloat(liquidSolid1VaporTemp.getText());
        float dpdt = Float.parseFloat(liquidSolid1dpdt.getText());
        viewController.addLiquidSolid1(temp, dpdt);
    }

    /**
     * On liquid solid 2 add pressed.
     */
    @FXML
    public void onLiquidSolid2AddPressed() {
        if (!checkTextIsCorrect(liquidSolid2VaporTemp.getText())) {
            showErrorDialogText(Utils.LIQUID_SOLID_2_ERR,"Temperature has to be a number.");
        }
        else if (!checkTextIsCorrect(liquidSolid2dpdt.getText())) {
            showErrorDialogText(Utils.LIQUID_SOLID_2_ERR, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }

        float temp = Float.parseFloat(liquidSolid2VaporTemp.getText());
        float dpdt = Float.parseFloat(liquidSolid2dpdt.getText());
        viewController.addLiquidSolid2(temp, dpdt);
    }

    /**
     * On solid 1 solid 2 add pressed.
     */
    @FXML
    public void onSolid1Solid2AddPressed() {
        if (!checkTextIsCorrect(solid1Solid2VaporTemp.getText())) {
            showErrorDialogText(Utils.SOLID_1_SOLID_2_ERR,"Temperature has to be a number.");
        }
        else if (!checkTextIsCorrect(solid1Solid2dpdt.getText())) {
            showErrorDialogText(Utils.SOLID_1_SOLID_2_ERR, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }

        float temp = Float.parseFloat(solid1Solid2VaporTemp.getText());
        float dpdt = Float.parseFloat(solid1Solid2dpdt.getText());
        viewController.addSolid1Solid2(temp, dpdt);
    }

    /**
     * On view graphic pressed.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void onViewGraphicPressed() throws IOException {

        try {
            viewController.viewGraphic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * On stable diagram pressed.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void onStableDiagramPressed() throws IOException{

        try {
            viewController.viewStableDiagram();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * On save pressed.
     */
    @FXML
    public void onSavePressed(){
        if (viewController.saveMaterial()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save successful");
            alert.setHeaderText(null);
            alert.setContentText("The material has been saved successfully!");
            alert.showAndWait();
        } else {
            showErrorDialogText("Error saving data", "The material could not be saved!");
        }
    }

    /**
     * On exit pressed.
     */
    @FXML
    public void onExitPressed(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exiting...");
        alert.setHeaderText("Would you like to save your data?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonTypeYes) {
                // ... user chose "Yes"
                onSavePressed();
                viewController.returnMainMenu();
            } else if (result.get() == buttonTypeNo) {
                // ... user chose "No"
                viewController.returnMainMenu();
            }
        }

    }

    private boolean checkTextIsCorrect(String word) {
        return word.matches(Utils.FLOAT_REG_EXP);
    }

    private void showErrorDialogText(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
