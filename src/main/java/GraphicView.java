import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.Random;

public class GraphicView {

    private ViewController viewController;

    @FXML private LineChart chart;

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;


    }

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

    @FXML
    public void onBackPressed() throws IOException {
        viewController.returnDataEntry();
    }
}
