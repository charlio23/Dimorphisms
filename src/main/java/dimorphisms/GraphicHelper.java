package dimorphisms;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.util.HashMap;
import java.util.Map;

public class GraphicHelper {

    private LineChart materialGraphic;
    private Map<String,Integer> dataSet;
    private Integer curveNumber;

    public GraphicHelper() {
        curveNumber = 0;
        dataSet = new HashMap<>();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("T");
        yAxis.setLabel("P");
        materialGraphic = new LineChart<>(xAxis,yAxis);

    }
}
