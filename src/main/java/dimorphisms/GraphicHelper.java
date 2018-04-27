package dimorphisms;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphicHelper {

    private LineChart<Number,Number> materialLinearGraphic;
    private LineChart<Number,Number> materialLogGraphicPositive;
    private LineChart<Number,Number> materialLogGraphicNegative;

    private double maxValue;

    public GraphicHelper() {
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
        seriesTotal.setName(name);
        seriesNegative.setName(name);
        seriesPositive.setName(name);
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

        //We find in what position the series is
        // (if it exists on the graphic)
        int positionLinear = getPositionLinear(name);
        int positionLogPositive = getPositionLogPositive(name);
        int positionLogNegative = getPositionLogNegative(name);
        if (positionLinear == -1) {
            materialLinearGraphic.getData().add(seriesTotal);
        } else {
            materialLinearGraphic.getData().set(positionLinear,seriesTotal);
        }

        if (positionLogPositive == -1) {
            materialLogGraphicPositive.getData().add(seriesPositive);
        } else {
            materialLogGraphicPositive.getData().set(positionLogPositive,seriesPositive);
        }

        if (positionLogNegative == -1) {
            materialLogGraphicNegative.getData().add(seriesNegative);
        } else {
            materialLogGraphicNegative.getData().set(positionLogNegative,seriesNegative);
        }
    }
    
    public void addPoint(String name, double temp, double press) {
        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        series.setName(name);
        series.getData().add(new XYChart.Data<>(temp,press));
        //We find in what position the series is
        // (if it exists on the graphic)
        int positionLinear = getPositionLinear(name);
        if (positionLinear == -1) {
            materialLinearGraphic.getData().add(series);
        } else {
            materialLinearGraphic.getData().set(positionLinear,series);
        }
        if (press > 0) {
            int positionLogPositive = getPositionLogPositive(name);
            if (positionLogPositive == -1) {
                materialLogGraphicPositive.getData().add(series);
            } else {
                materialLogGraphicPositive.getData().set(positionLogPositive, series);
            }
        } else {
            int positionLogNegative = getPositionLogNegative(name);
            if (positionLogNegative == -1) {
                materialLogGraphicNegative.getData().add(series);
            } else {
                materialLogGraphicNegative.getData().set(positionLogNegative, series);
            }
        }
    }

    public void removeInfo(String name) {
        int positionLinear = getPositionLinear(name);
        int positionLogPositive = getPositionLogPositive(name);
        int positionLogNegative = getPositionLogNegative(name);
        if (positionLinear != -1) {
            materialLinearGraphic.getData().remove(positionLinear);
        }
        if (positionLogPositive != -1) {
            materialLogGraphicPositive.getData().remove(positionLogPositive);
        }
        if (positionLogNegative != -1) {
            materialLogGraphicNegative.getData().remove(positionLogNegative);
        }

    }

    private int getPositionLinear(String word) {
        for (int i = 0; i < materialLinearGraphic.getData().size(); ++i) {
            if (materialLinearGraphic.getData().get(i).getName().equals(word)) {
                return i;
            }
        }
        return -1;
    }

    private int getPositionLogPositive(String word) {
        for (int i = 0; i < materialLogGraphicPositive.getData().size(); ++i) {
            if (materialLogGraphicPositive.getData().get(i).getName().equals(word)) {
                return i;
            }
        }
        return -1;
    }

    private int getPositionLogNegative(String word) {
        for (int i = 0; i < materialLogGraphicNegative.getData().size(); ++i) {
            if (materialLogGraphicNegative.getData().get(i).getName().equals(word)) {
                return i;
            }
        }
        return -1;
    }



    /* TODO
    Check the need of other methods such as:
        - series stroke and styling
        - etc
     */
}
