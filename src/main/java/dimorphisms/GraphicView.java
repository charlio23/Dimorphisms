package dimorphisms;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * The type Graphic view.
 */
public class GraphicView {

    private ViewController viewController;

    @FXML private BorderPane graphicPane;

    /**
     * Sets view controller.
     *
     * @param viewController the view controller
     */
    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
        graphicPane.setCenter(viewController.getGraphic());
    }

    /**
     * On press.
     */
    public void onPress(){

    }

    /**
     * On back pressed.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void onBackPressed() {
        viewController.returnDataEntry();
    }
}
