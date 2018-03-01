import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenu {

    private ViewController viewController;

    private Scene scene;

    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @FXML
    public void onButtonPressed(){
        viewController.call();
    }

    @FXML
    public void onViewChange() throws IOException {
        viewController.changeStage();
    }
}
