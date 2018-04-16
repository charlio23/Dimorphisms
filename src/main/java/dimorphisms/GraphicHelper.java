package dimorphisms;

import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.Map;

public class GraphicHelper {

    private LineChart materialGraphic;
    private Map<String,Integer> dataMapping;
    private Integer curveNumber;

    public GraphicHelper() {
        curveNumber = 0;
        dataMapping = new HashMap<>();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("T");
        yAxis.setLabel("P");
        materialGraphic = new LineChart<>(xAxis,yAxis);

    }

    public LineChart getLinearGraphic(){
        return materialGraphic;
    }
    
    public void addCurve(String name, double[] values) {
        XYChart.Series<Double,Double> series = new XYChart.Series<>();
        double temperature = Utils.TEMPERATURE_ORIGIN;
        for (double value: values) {
            series.getData().add(new XYChart.Data<>(temperature, value));
            temperature = temperature + Utils.TEMPERATURE_STEP;
        }
        //we substitute the value if it was there
        if (dataMapping.containsKey(name)) {
            materialGraphic.getData().set(dataMapping.get(name),series);
        } else {
            dataMapping.put(name,curveNumber);
            materialGraphic.getData().add(series);
            ++curveNumber;
        }
    }
    
    public void addPoint(String name, double temp, double press) {
        XYChart.Series<Double,Double> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(temp,press));
        if (dataMapping.containsKey(name)) {
            materialGraphic.getData().set(dataMapping.get(name),series);
        } else {
            dataMapping.put(name,curveNumber);
            materialGraphic.getData().add(series);
            ++curveNumber;
        }
    }

    /* TODO
    Check the need of other methods such as:
        - change to log scale (how?)
        - change axis labels
        - series stroke and styling
        - etc
     */
}
