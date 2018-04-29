package dimorphisms;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

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
        graphicPane.setCenter(viewController.getLinearGraphic());
    }

    @FXML
    public void onLinearScalePress(){
        graphicPane.setCenter(viewController.getLinearGraphic());
    }

    @FXML
    public void onLogScalePress() {
        LineChart[] lineCharts = viewController.getLogGraphic();
        graphicPane.setCenter(lineCharts[0]);

    }

    @FXML
    public void onAutoScalePress(){
        viewController.autoScale();
    }

    @FXML
    public void onChangeScalePress(){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Change Scale");
        dialog.setHeaderText("Set the bounds that you want to apply");
        ButtonType set = new ButtonType("Set", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(set,ButtonType.CANCEL);
        TextField textFieldXMin = new TextField();
        textFieldXMin.setPromptText("x min");
        TextField textFieldXMax = new TextField();
        textFieldXMax.setPromptText("x max");
        TextField textFieldYMin = new TextField();
        textFieldYMin.setPromptText("y min");
        TextField textFieldYMax = new TextField();
        textFieldYMax.setPromptText("y max");
        dialog.getDialogPane().setContent(new VBox(8,textFieldXMin,textFieldXMax,textFieldYMin,textFieldYMax));
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == set) {
                return textFieldXMin.getText() + " " + textFieldXMax.getText() + " " + textFieldYMin.getText() + " " + textFieldYMax.getText();
            }
            return null;
        });
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String[] data = result.get().split(" ");
            double xMin = Double.parseDouble(data[0]);
            double xMax = Double.parseDouble(data[1]);
            double yMin = Double.parseDouble(data[2]);
            double yMax = Double.parseDouble(data[3]);
            viewController.changeScale(xMin, xMax, yMin, yMax);
        }
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
