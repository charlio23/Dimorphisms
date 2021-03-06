package dimorphisms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
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
    @FXML private Pane liquidVaporPane;
    @FXML private Pane solid1VaporPane;
    @FXML private Pane solid2VaporPane;
    @FXML private Pane liquidSolid1Pane;
    @FXML private Pane liquidSolid2Pane;
    @FXML private Pane solid1Solid2Pane;
    @FXML private Button stableDiagramButton;

    private ArrayList<String> moreInformationText;





    private ViewController viewController;

    /**
     * Set view controller.
     *
     * @param viewController the view controller
     */
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
        moreInformationText = new ArrayList<>();
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

        double a = Double.parseDouble(liquidVaporA.getText());
        double b = Double.parseDouble(liquidVaporB.getText());
        double c = Double.parseDouble(liquidVaporC.getText());
        boolean isLog = liquidVaporLog.isSelected();
        updateInformation(viewController.addLiquidVapor(a, b, c, isLog));
        liquidVaporPane.getStyleClass().add("data-entered");

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

        double a = Double.parseDouble(solid1VaporA.getText());
        double b = Double.parseDouble(solid1VaporB.getText());
        double c = Double.parseDouble(solid1VaporC.getText());
        boolean isLog = solid1VaporLog.isSelected();
        updateInformation(viewController.addSolid1Vapor(a, b, c, isLog));
        solid1VaporPane.getStyleClass().add("data-entered");

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

        double a = Double.parseDouble(solid2VaporA.getText());
        double b = Double.parseDouble(solid2VaporB.getText());
        double c = Double.parseDouble(solid2VaporC.getText());
        boolean isLog = solid2VaporLog.isSelected();
        updateInformation(viewController.addSolid2Vapor(a, b, c, isLog));
        solid2VaporPane.getStyleClass().add("data-entered");
    }

    /**
     * On liquid solid 1 add pressed.
     */
    @FXML
    public void onLiquidSolid1AddPressed() {
        if (!checkTextIsCorrect(liquidSolid1dpdt.getText())) {
            showErrorDialogText(Utils.LIQUID_SOLID_1_ERR, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }

        double dpdt = Double.parseDouble(liquidSolid1dpdt.getText());
        updateInformation(viewController.addLiquidSolid1(dpdt));
        liquidSolid1Pane.getStyleClass().add("data-entered");

    }

    /**
     * On liquid solid 2 add pressed.
     */
    @FXML
    public void onLiquidSolid2AddPressed() {
        if (!checkTextIsCorrect(liquidSolid2dpdt.getText())) {
            showErrorDialogText(Utils.LIQUID_SOLID_2_ERR, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }

        double dpdt = Double.parseDouble(liquidSolid2dpdt.getText());
        updateInformation(viewController.addLiquidSolid2(dpdt));
        liquidSolid2Pane.getStyleClass().add("data-entered");

    }

    /**
     * On solid 1 solid 2 add pressed.
     */
    @FXML
    public void onSolid1Solid2AddPressed() {
        if (!checkTextIsCorrect(solid1Solid2dpdt.getText())) {
            showErrorDialogText(Utils.SOLID_1_SOLID_2_ERR, "dp/dT has to be a number.");
        } else {
            System.out.println("Call to view");
        }

        double dpdt = Double.parseDouble(solid1Solid2dpdt.getText());
        updateInformation(viewController.addSolid1Solid2(dpdt));
        solid1Solid2Pane.getStyleClass().add("data-entered");

    }

    @FXML
    public void onSolid1Solid2VaporTempAddPressed() {
        if (!checkTextIsCorrect(solid1Solid2VaporTemp.getText())) {
            showErrorDialogText(Utils.SOLID_1_SOLID_2_ERR,"Temperature has to be a number.");
        }
        double temp = Double.parseDouble(solid1Solid2VaporTemp.getText());
        updateInformation(viewController.addSolid1Solid2VaporTemp(temp));
        solid1Solid2Pane.getStyleClass().add("data-entered");

    }

    @FXML
    public void onLiquidSolid1VaporTempAddPressed() {
        if (!checkTextIsCorrect(liquidSolid1VaporTemp.getText())) {
            showErrorDialogText(Utils.LIQUID_SOLID_1_ERR,"Temperature has to be a number.");
        }
        double temp = Double.parseDouble(liquidSolid1VaporTemp.getText());
        updateInformation(viewController.addLiquidSolid1VaporTemp(temp));
        liquidSolid1Pane.getStyleClass().add("data-entered");

    }

    @FXML
    public void onLiquidSolid2VaporTempAddPressed() {
        if (!checkTextIsCorrect(liquidSolid2VaporTemp.getText())) {
            showErrorDialogText(Utils.LIQUID_SOLID_2_ERR,"Temperature has to be a number.");
        }
        double temp = Double.parseDouble(liquidSolid2VaporTemp.getText());
        updateInformation(viewController.addLiquidSolid2VaporTemp(temp));
        liquidSolid2Pane.getStyleClass().add("data-entered");

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
        viewController.deleteMaterial(materialName.getText());
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

    private void updateInformation(ArrayList<String> information) {
        moreInformationText = new ArrayList<>();
        for (String info: information) {
            String[] values = info.split(";");
            switch (values[0]) {
                case Utils.QUERY_PLV1:
                    moreInformationText.add("Found triple point LV1: " + values[1] + " K,  " + values[2] + " MPa");
                    break;
                case Utils.QUERY_PLV2:
                    moreInformationText.add("Found triple point LV2: " + values[1] + " K,  " + values[2] + " MPa");
                    break;
                case Utils.QUERY_PL12:
                    moreInformationText.add("Found triple point L12: " + values[1] + " K,  " + values[2] + " MPa");
                    break;
                case Utils.QUERY_PV12:
                    moreInformationText.add("Found triple point V12: " + values[1] + " K,  " + values[2] + " MPa");
                    break;

                case Utils.QUERY_LIQUID_VAPOR:
                    liquidVaporA.setText(values[1]);
                    liquidVaporB.setText(values[2]);
                    liquidVaporC.setText(values[3]);
                    liquidVaporLog.setSelected(Boolean.valueOf(values[4]));
                    liquidVaporLn.setSelected(!(Boolean.valueOf(values[4])));
                    liquidVaporPane.getStyleClass().add("data-calculated");
                    break;

                case Utils.QUERY_VAPOR_SOLID1:
                    solid1VaporA.setText(values[1]);
                    solid1VaporB.setText(values[2]);
                    solid1VaporC.setText(values[3]);
                    solid1VaporLog.setSelected(Boolean.valueOf(values[4]));
                    solid1VaporLn.setSelected(!(Boolean.valueOf(values[4])));
                    solid1VaporPane.getStyleClass().add("data-calculated");
                    break;

                case Utils.QUERY_VAPOR_SOLID2:
                    solid2VaporA.setText(values[1]);
                    solid2VaporB.setText(values[2]);
                    solid2VaporC.setText(values[3]);
                    solid1VaporLog.setSelected(Boolean.valueOf(values[4]));
                    solid1VaporLn.setSelected(!(Boolean.valueOf(values[4])));
                    solid2VaporPane.getStyleClass().add("data-calculated");
                    break;

                case Utils.QUERY_LIQUID_SOLID1:
                    liquidSolid1dpdt.setText(values[1]);
                    liquidSolid1Pane.getStyleClass().add("data-calculated");
                    break;

                case Utils.QUERY_LIQUID_SOLID2:
                    liquidSolid2dpdt.setText(values[1]);
                    liquidSolid2Pane.getStyleClass().add("data-calculated");
                    break;

                case Utils.QUERY_SOLID1_SOLID2:
                    solid1Solid2dpdt.setText(values[1]);
                    solid1Solid2Pane.getStyleClass().add("data-calculated");
                    break;

                case Utils.QUERY_TLV1:
                    liquidSolid1VaporTemp.setText(values[1]);
                    liquidSolid1Pane.getStyleClass().add("data-calculated");
                    break;

                case Utils.QUERY_TLV2:
                    liquidSolid2VaporTemp.setText(values[1]);
                    liquidSolid2Pane.getStyleClass().add("data-calculated");
                    break;

                case Utils.QUERY_TV12:
                    solid1Solid2VaporTemp.setText(values[1]);
                    solid1Solid2Pane.getStyleClass().add("data-calculated");
                    break;
                case Utils.QUERY_ERROR_CONFLICT:
                    return;

                case Utils.QUERY_ERROR_UNEXPECTED:
                    return;

                case Utils.ACTIVATE_STABLE_DIAGRAM:
                    stableDiagramButton.setDisable(false);
                    break;

                default:
                    break;

            }
        }
    }

    @FXML
    public void onViewMoreInformationPressed(){
        if (moreInformationText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("More Information");
            alert.setHeaderText(null);
            alert.setContentText("There is no additional information to show.");
            alert.showAndWait();

        } else {
            Dialog dialog = new Dialog();
            VBox vBox = new VBox(8);
            for (String info: moreInformationText) {
                vBox.getChildren().add(new Label(info));
            }
            dialog.getDialogPane().setContent(vBox);
            dialog.setTitle("More Information");
            dialog.setHeaderText("Information about triple points:");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.show();
        }

    }

    public void setMaterialInfo(ArrayList<String> information) {
        updateInformation(viewController.getUIInformation());
        stableDiagramButton.setDisable(!viewController.isMaterialComplete());
        for (String info : information) {
            String[] values = info.split(";");
            switch (values[0]) {
                case Utils.QUERY_LIQUID_VAPOR:
                    liquidVaporA.setText(values[1]);
                    liquidVaporB.setText(values[2]);
                    liquidVaporC.setText(values[3]);
                    liquidVaporLog.setSelected(Boolean.valueOf(values[4]));
                    liquidVaporLn.setSelected(!(Boolean.valueOf(values[4])));
                    liquidVaporPane.getStyleClass().add("data-entered");
                    break;

                case Utils.QUERY_VAPOR_SOLID1:
                    solid1VaporA.setText(values[1]);
                    solid1VaporB.setText(values[2]);
                    solid1VaporC.setText(values[3]);
                    solid1VaporLog.setSelected(Boolean.valueOf(values[4]));
                    solid1VaporLn.setSelected(!(Boolean.valueOf(values[4])));
                    solid1VaporPane.getStyleClass().add("data-entered");
                    break;

                case Utils.QUERY_VAPOR_SOLID2:
                    solid2VaporA.setText(values[1]);
                    solid2VaporB.setText(values[2]);
                    solid2VaporC.setText(values[3]);
                    solid2VaporLog.setSelected(Boolean.valueOf(values[4]));
                    solid2VaporLn.setSelected(!(Boolean.valueOf(values[4])));
                    solid2VaporPane.getStyleClass().add("data-entered");
                    break;

                case Utils.QUERY_LIQUID_SOLID1:
                    liquidSolid1dpdt.setText(values[1]);
                    liquidSolid1Pane.getStyleClass().add("data-entered");
                    break;

                case Utils.QUERY_LIQUID_SOLID2:
                    liquidSolid2dpdt.setText(values[1]);
                    liquidSolid2Pane.getStyleClass().add("data-entered");
                    break;

                case Utils.QUERY_SOLID1_SOLID2:
                    solid1Solid2dpdt.setText(values[1]);
                    solid1Solid2Pane.getStyleClass().add("data-entered");
                    break;

                case Utils.QUERY_TLV1:
                    liquidSolid1VaporTemp.setText(values[1]);
                    liquidSolid1Pane.getStyleClass().add("data-entered");
                    break;

                case Utils.QUERY_TLV2:
                    liquidSolid2VaporTemp.setText(values[1]);
                    liquidSolid2Pane.getStyleClass().add("data-entered");
                    break;

                case Utils.QUERY_TV12:
                    solid1Solid2VaporTemp.setText(values[1]);
                    solid1Solid2Pane.getStyleClass().add("data-entered");
                    break;
            }
        }
    }

}
