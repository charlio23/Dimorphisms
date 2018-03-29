import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;

public class DataEntry {
    @FXML private TitledPane materialPanel;
    private ViewController viewController;

    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    public void setMaterialName(String materialName) {
        this.materialPanel.setText(materialName);
    }
}
