package dimorphisms;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.Map;


public class GraphicHelper {

    private LineChart<Number,Number> materialLinearGraphic;
    private LineChart<Number,Number> materialLogGraphicPositive;
    private LineChart<Number,Number> materialLogGraphicNegative;

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
        materialLinearGraphic.setCreateSymbols(false);

        this.materialLogGraphicPositive = new LineChart<>(xAxis,yAxisLog);
        this.materialLogGraphicNegative = new LineChart<>(xAxis,yAxisLog);
        materialLogGraphicPositive.setStyle(".chart-series-line {    \n" +
                "    -fx-stroke-width: 2px;\n" +
                "    -fx-effect: null;\n" +
                "}\n" +
                ".default-color0.chart-series-line { -fx-stroke: #e9967a; }\n");
        materialLogGraphicNegative.setStyle(".chart-series-line {    \n" +
                "    -fx-stroke-width: 2px;\n" +
                "    -fx-effect: null;\n" +
                "}\n" +
                ".default-color0.chart-series-line { -fx-stroke: #e9967a; }\n");
        materialLogGraphicNegative.setScaleY(-1);
        materialLogGraphicNegative.getXAxis().setVisible(false);

    }

    public LineChart getLinearGraphic(){
        return materialLinearGraphic;
    }

    public LineChart[] getLogGraphic() {
        return new LineChart[]{materialLogGraphicPositive, materialLogGraphicNegative};
    }

    public void addCurve(String name, double[] values) {
        XYChart.Series<Number,Number> seriesTotal = new XYChart.Series<>();
        XYChart.Series<Number,Number> seriesPositive = new XYChart.Series<>();
        XYChart.Series<Number,Number> seriesNegative = new XYChart.Series<>();
        double temperature = Utils.TEMPERATURE_ORIGIN;
        if (values[values.length-1] > maxValue) maxValue =values[values.length-1];
        for (double value: values) {
            XYChart.Data<Number,Number> point = new XYChart.Data<>(temperature, value);
            seriesTotal.getData().add(point);
            if (value > 0) {
                seriesPositive.getData().add(point);
            } else if (value < 0) {
                seriesNegative.getData().add(point);
            }
            temperature = temperature + Utils.TEMPERATURE_STEP;
        }


        //we substitute the value if it was there
        if (dataMapping.containsKey(name)) {
            materialLogGraphicNegative.getData().set(dataMapping.get(name),seriesNegative);
            materialLogGraphicPositive.getData().set(dataMapping.get(name),seriesPositive);
            materialLinearGraphic.getData().set(dataMapping.get(name),seriesTotal);

        } else {
            dataMapping.put(name,curveNumber);
            materialLogGraphicNegative.getData().add(seriesNegative);
            materialLogGraphicPositive.getData().add(seriesPositive);
            materialLinearGraphic.getData().add(seriesTotal);
            ++curveNumber;
        }
    }
    
    public void addPoint(String name, double temp, double press) {
        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(temp,press));
        if (dataMapping.containsKey(name)) {
            materialLinearGraphic.getData().set(dataMapping.get(name),series);
            if (press > 0) {
                materialLogGraphicPositive.getData().set(dataMapping.get(name),series);
            } else {
                materialLogGraphicNegative.getData().set(dataMapping.get(name),series);
            }
        } else {
            dataMapping.put(name,curveNumber);
            materialLinearGraphic.getData().add(series);
            if (press > 0) {
                materialLogGraphicPositive.getData().set(dataMapping.get(name),series);
            } else {
                materialLogGraphicNegative.getData().set(dataMapping.get(name),series);
            }

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
        - series stroke and styling
        - etc
     */
}
