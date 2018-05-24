package dimorphisms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;

/**
 * The type Main menu.
 */
public class MainMenu {

    @FXML private Label label;
    private ViewController viewController;

    private Scene scene;

    /**
     * Set view controller.
     *
     * @param viewController the view controller
     */
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * On new pressed.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void onNewPressed() throws IOException {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("New Dimorphism");
        dialog.setHeaderText("Enter the dimorphism name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            if (result.get().matches(Utils.NAME_REG_EXP)) {
                Utils.logger.log(Level.INFO,"Name is " + result.get());
                if (viewController.checkForMaterial(result.get())) {
                    viewController.newMaterial(result.get());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Dimorphism name input ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Oops! There is already a material with exactly the same name in the database!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Dimorphism name input ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please, provide a name with at least 3 characters");
                alert.showAndWait();

            }
        } else {
            Utils.logger.log(Level.INFO,"Pressed cancel button");
        }
    }

    /**
     * On load pressed.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void onLoadPressed() throws IOException {
        ListView<String> list = new ListView<>();
        ArrayList<String> materialNames = viewController.getMaterialNames();
        if (materialNames.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No material to load");
            alert.setHeaderText(null);
            alert.setContentText("There are no materials loaded in the database.");
            alert.showAndWait();
        } else {
            ObservableList<String> data = FXCollections.observableArrayList(materialNames);
            list.setItems(data);
            Dialog<String> dialog = new Dialog<>();
            dialog.getDialogPane().setContent(list);
            dialog.setTitle("Load");
            dialog.setHeaderText("Please choose the material you want to load:");
            ButtonType load = new ButtonType("Load", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(load,ButtonType.CLOSE);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == load) {
                    String selected = list.getSelectionModel().getSelectedItem();
                    if (selected == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Load");
                        alert.setHeaderText(null);
                        alert.setContentText("Please choose a material to load.");
                        alert.showAndWait();
                    } else {
                        try {
                            viewController.loadMaterial(selected);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            });

            dialog.show();

        }
    }

    @FXML
    public void onErasePressed(){
        ListView<String> list = new ListView<>();
        ArrayList<String> materialNames = viewController.getMaterialNames();
        if (materialNames.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No material to erase");
            alert.setHeaderText(null);
            alert.setContentText("There are no materials loaded in the database.");
            alert.showAndWait();
        } else {
            ObservableList<String> data = FXCollections.observableArrayList(materialNames);
            list.setItems(data);
            Dialog<String> dialog = new Dialog<>();
            dialog.getDialogPane().setContent(list);
            dialog.setTitle("Erase");
            dialog.setHeaderText("Please choose the material you want to erase:");
            ButtonType erase = new ButtonType("Erase", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(erase,ButtonType.CLOSE);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == erase) {
                    String selected = list.getSelectionModel().getSelectedItem();
                    if (selected == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erase");
                        alert.setHeaderText(null);
                        alert.setContentText("Please choose a material to erase.");
                        alert.showAndWait();
                    } else {
                        viewController.deleteMaterial(selected);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Erase successful");
                        alert.setHeaderText(null);
                        alert.setContentText("The material has been erased from the database successfully!");
                        alert.showAndWait();
                    }
                }
                return null;
            });

            dialog.show();

        }
    }

}
