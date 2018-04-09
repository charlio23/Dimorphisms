package dimorphisms;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
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
                viewController.newMaterial(result.get());
            } else {Alert alert = new Alert(Alert.AlertType.ERROR);
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
        /* TODO
        onLoadPressed should charge a view with a list of materials.
         */
    }

}
