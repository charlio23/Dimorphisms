import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainMenu {

    @FXML private Label label;
    private ViewController viewController;

    private Scene scene;

    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @FXML
    public void onButtonPressed() throws IOException {
        viewController.call();
        viewController.changeStage();
    }

    @FXML
    public void onNewPressed() throws IOException {
        viewController.newMaterial();
    }
}
