package dimorphisms;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.Map;

import static java.lang.StrictMath.log;


public class GraphicHelper {

    private LineChart<Number,Number> materialLinearGraphic;
    private LineChart<Number,Number> materialLogGraphic;

    private Map<String,Integer> dataMapping;
    private Integer curveNumber;
    private double maxValue;

    public GraphicHelper() {
        curveNumber = 0;
        dataMapping = new HashMap<>();
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("T");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("P");
        LogarithmicNumberAxis yAxisLog = new LogarithmicNumberAxis(2e-2,1e10);
        yAxisLog.setLabel("P");
        this.materialLinearGraphic = new LineChart<>(xAxis,yAxis);
        materialLinearGraphic.setCreateSymbols(false);
        materialLinearGraphic.setStyle(".chart-series-line {    \n" +
                "    -fx-stroke-width: 2px;\n" +
                "    -fx-effect: null;\n" +
                "}\n" +
                ".default-color0.chart-series-line { -fx-stroke: #e9967a; }\n");
        this.materialLogGraphic = new LineChart<>(xAxis,yAxisLog);
        this.materialLogGraphic = new LineChart<>(xAxis,yAxisLog);
        materialLogGraphic.setCreateSymbols(false);
        materialLogGraphic.setStyle(".chart-series-line {    \n" +
                "    -fx-stroke-width: 2px;\n" +
                "    -fx-effect: null;\n" +
                "}\n" +
                ".default-color0.chart-series-line { -fx-stroke: #e9967a; }\n");

    }

    public LineChart getLinearGraphic(){
        return materialLinearGraphic;
    }

    public LineChart getLogGraphic() {
        return materialLogGraphic;
    }

    public void addCurve(String name, double[] values) {
        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        double temperature = Utils.TEMPERATURE_ORIGIN;
        if (values[values.length-1] > maxValue) maxValue =values[values.length-1];
        for (double value: values) {
            XYChart.Data<Number,Number> point = new XYChart.Data<>(temperature, value);
            series.getData().add(point);
            temperature = temperature + Utils.TEMPERATURE_STEP;
        }
        //we substitute the value if it was there
        if (dataMapping.containsKey(name)) {
            materialLinearGraphic.getData().set(dataMapping.get(name),series);
        } else {
            dataMapping.put(name,curveNumber);
            materialLinearGraphic.getData().add(series);
            ++curveNumber;
        }
    }
    
    public void addPoint(String name, double temp, double press) {
        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(temp,press));
        if (dataMapping.containsKey(name)) {
            materialLinearGraphic.getData().set(dataMapping.get(name),series);
        } else {
            dataMapping.put(name,curveNumber);
            materialLinearGraphic.getData().add(series);
            ++curveNumber;
        }
    }
    /* TODO
    we will reimplement this methon in the future
     */
    public void removeInfo(String name) {
        Integer position = dataMapping.get(name);
        if (position == null) return;
        materialLinearGraphic.getData().set(position,null);
    }



    /* TODO
    Check the need of other methods such as:
        - change to log scale (how?)
        - change axis labels
        - series stroke and styling
        - etc
     */
}
