import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class GraphicView {

    private ViewController viewController;

    @FXML private LineChart chart;
    @FXML private Label label;

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



        label.setText("HOLA");
    }

    @FXML
    public void onPressBack() throws IOException {
        viewController.firstStage();
    }
}
