import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DataEntry {

    private static String floatRegExp = "([0-9]*[.])?[0-9]+";

    private static String liquidVaporErr = "Liquid-Vapor input ERROR";
    private static String solid1VaporErr = "Solid1-Vapor input ERROR";
    private static String solid2VaporErr = "Solid2-Vapor input ERROR";
    private static String liquidSolid1Err = "Liquid-Solid1 input ERROR";
    private static String liquidSolid2Err = "Liquid-Solid2 input ERROR";
    private static String solid1Solid2Err = "Solid1-Solid2 input ERROR";


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

    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    public void setMaterialName(String materialName) {
        this.materialName.setText(materialName);
    }

    @FXML
    public void onLiquidVaporLogPressed() {
        if (liquidVaporLn.isSelected()) {
            liquidVaporLn.setSelected(false);
        }
    }

    @FXML
    public void onLiquidVaporLnPressed() {
        if (liquidVaporLog.isSelected()) {
            liquidVaporLog.setSelected(false);
        }
    }

    @FXML
    public void onSolid1VaporLogPressed() {
        if (solid1VaporLn.isSelected()) {
            solid1VaporLn.setSelected(false);
        }
    }

    @FXML
    public void onSolid1VaporLnPressed() {
        if (solid1VaporLog.isSelected()) {
            solid1VaporLog.setSelected(false);
        }
    }

    @FXML
    public void onSolid2VaporLogPressed() {
        if (solid2VaporLn.isSelected()) {
            solid2VaporLn.setSelected(false);
        }
    }

    @FXML
    public void onSolid2VaporLnPressed() {
        if (solid2VaporLog.isSelected()) {
            solid2VaporLog.setSelected(false);
        }
    }

    @FXML
    public void onLiquidVaporAddPressed() {
        if (!liquidVaporLn.isSelected() && !liquidVaporLog.isSelected()) {
            showErrorDialogText(liquidVaporErr,
                    "You must choose whether it is a ln or a log curve type.");
        } else if (!checkTextIsCorrect(liquidVaporA.getText())){
            showErrorDialogText(liquidVaporErr,"A field has to be a number.");
        } else if (!checkTextIsCorrect(liquidVaporB.getText())){
            showErrorDialogText(liquidVaporErr,"B field has to be a number.");
        } else if (!checkTextIsCorrect(liquidVaporC.getText())){
            showErrorDialogText(liquidVaporErr,"C field has to be a number.");
        } else {
            System.out.println("Call to view");

        }
        /* TODO
        * call view controller in order to add the curve
        *
        * */
    }

    @FXML
    public void onSolid1VaporAddPressed() {
        if (!solid1VaporLn.isSelected() && !solid1VaporLog.isSelected()) {
            showErrorDialogText(solid1VaporErr,
                    "You must choose whether it is a ln or a log curve type.");
        } else if (!checkTextIsCorrect(solid1VaporA.getText())){
            showErrorDialogText(solid1VaporErr,"A field has to be a number.");
        } else if (!checkTextIsCorrect(solid1VaporB.getText())){
            showErrorDialogText(solid1VaporErr,"B field has to be a number.");
        } else if (!checkTextIsCorrect(solid1VaporC.getText())){
            showErrorDialogText(solid1VaporErr,"C field has to be a number.");
        } else {
            System.out.println("Call to view");

        }
        /* TODO
        * call view controller in order to add the curve
        *
        * */
    }

    @FXML
    public void onSolid2VaporAddPressed() {
        if (!solid2VaporLn.isSelected() && !solid2VaporLog.isSelected()) {
            showErrorDialogText(solid2VaporErr,
                    "You must choose whether it is a ln or a log curve type.");
        } else if (!checkTextIsCorrect(solid2VaporA.getText())){
            showErrorDialogText(solid2VaporErr,"A field has to be a number.");
        } else if (!checkTextIsCorrect(solid2VaporB.getText())){
            showErrorDialogText(solid2VaporErr,"B field has to be a number.");
        } else if (!checkTextIsCorrect(solid2VaporC.getText())){
            showErrorDialogText(solid2VaporErr,"C field has to be a number.");
        } else {
            System.out.println("Call to view");
        }
        /* TODO
        * call view controller in order to add the curve
        *
        * */
    }

    @FXML
    public void onLiquidSolid1AddPressed() {
        if (!checkTextIsCorrect(liquidSolid1VaporTemp.getText())) {
            showErrorDialogText(liquidSolid1Err,"Temperature has to be a number.");
        }
        else if (!checkTextIsCorrect(liquidSolid1dpdt.getText())) {
            showErrorDialogText(liquidSolid1Err, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }
        /* TODO
        * call view controller in order to add the curve
        *
        * */
    }

    @FXML
    public void onLiquidSolid2AddPressed() {
        if (!checkTextIsCorrect(liquidSolid2VaporTemp.getText())) {
            showErrorDialogText(liquidSolid2Err,"Temperature has to be a number.");
        }
        else if (!checkTextIsCorrect(liquidSolid2dpdt.getText())) {
            showErrorDialogText(liquidSolid2Err, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }
        /* TODO
        * call view controller in order to add the curve
        *
        * */
    }

    @FXML
    public void onSolid1Solid2AddPressed() {
        if (!checkTextIsCorrect(solid1Solid2VaporTemp.getText())) {
            showErrorDialogText(solid1Solid2Err,"Temperature has to be a number.");
        }
        else if (!checkTextIsCorrect(solid1Solid2dpdt.getText())) {
            showErrorDialogText(solid1Solid2Err, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }
        /* TODO
        * call view controller in order to add the curve
        *
        * */
    }

    @FXML
    public void onViewGraphicPressed(){
        /* TODO
        * call view controller in order to view the graphic
        *
        * */
    }

    @FXML
    public void onStableDiagramPressed(){
        /* TODO
        * call view controller in order to view the diagram
        *
        * */
    }

    @FXML
    public void onSavePressed(){
        /* TODO
        * call view controller in order to save
        *
        * */
    }

    @FXML
    public void onExitPressed(){
        /* TODO
        * call view controller in order to exit
        *
        * */
    }

    private boolean checkTextIsCorrect(String word) {
        return word.matches(floatRegExp);
    }

    private void showErrorDialogText(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
