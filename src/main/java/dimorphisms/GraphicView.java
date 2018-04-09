package dimorphisms;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.Random;

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
    public void setViewController(ViewController viewController) {
        this.viewController = viewController;


    }

    /**
     * On press.
     */
    public void onPress(){
        XYChart.Series series = new XYChart.Series();
        Random random = new Random();

        series.getData().add(new XYChart.Data(random.nextFloat(),random.nextFloat()));
        series.getData().add(new XYChart.Data(random.nextFloat(),random.nextFloat()));
        series.getData().add(new XYChart.Data(random.nextFloat(),random.nextFloat()));
        series.getData().add(new XYChart.Data(random.nextFloat(),random.nextFloat()));
        series.getData().add(new XYChart.Data(random.nextFloat(),random.nextFloat()));
        series.getData().add(new XYChart.Data(random.nextFloat(),random.nextFloat()));
        series.getData().add(new XYChart.Data(random.nextFloat(),random.nextFloat()));
        series.getData().add(new XYChart.Data(random.nextFloat(),random.nextFloat()));

        chart.getData().add(series);
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
