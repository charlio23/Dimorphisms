package dimorphisms;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

import java.io.IOException;

/**
 * The type Graphic view.
 */
public class GraphicView {

    private ViewController viewController;

    @FXML private LineChart chart;

    /**
     * Sets view controller.
     *
     * @param viewController the view controller
     */
    public LineChart setViewController(ViewController viewController) {
        this.viewController = viewController;
        return chart;
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
