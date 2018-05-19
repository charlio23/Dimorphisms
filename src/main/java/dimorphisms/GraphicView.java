package dimorphisms;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * The type Graphic view.
 */
public class GraphicView {

    private ViewController viewController;
    private boolean graphic;

    @FXML private BorderPane graphicPane;
    @FXML private Button viewTopology;
    /**
     * Sets view controller.
     *
     * @param viewController the view controller
     */
    public void setViewController(ViewController viewController, boolean graphic) {
        this.viewController = viewController;
        this.graphic = graphic;
        graphicPane.setCenter(viewController.getLinearGraphic(graphic));
        if (graphic) viewTopology.setVisible(false);
        else viewTopology.setVisible(true);
    }

    @FXML
    public void onLinearScalePress(){
        graphicPane.setCenter(viewController.getLinearGraphic(graphic));
    }

    @FXML
    public void onLogScalePress() {
        LineChart[] lineCharts = viewController.getLogGraphic();
        graphicPane.setCenter(lineCharts[0]);

    }

    @FXML
    public void onAutoScalePress(){
        viewController.autoScale(graphic);
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
            viewController.changeScale(xMin, xMax, yMin, yMax,graphic);
        }
    }

    @FXML
    public void onViewTopologyPressed() {
        Image image;
        String text;
        switch (viewController.getTopology()) {
            case 1:
                text = Utils.TOPOLOGY_TEXT_1;
                image = new Image(getClass().getResource("/Dimorfisme_1.png").toExternalForm());
                break;

            case 2:
                text = Utils.TOPOLOGY_TEXT_2;
                image = new Image(getClass().getResource("/Dimorfisme_2.png").toExternalForm());
                break;

            case 3:
                text = Utils.TOPOLOGY_TEXT_3;
                image = new Image(getClass().getResource("/Dimorfisme_3.png").toExternalForm());
                break;

            case 4:
                text = Utils.TOPOLOGY_TEXT_4;
                image = new Image(getClass().getResource("/Dimorfisme_4.png").toExternalForm());
                break;

            default:
                text = Utils.TOPOLOGY_TEXT_4;
                image = new Image(getClass().getResource("/Dimorfisme_1.png").toExternalForm());
                break;
        }
        Dialog dialog = new Dialog();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.add(new Label(text), 0, 0);
        grid.add(new ImageView(image), 0, 1);
        dialog.setTitle("P-T topology information");
        dialog.setHeaderText(text);
        dialog.getDialogPane().setContent(new VBox(8,new Label(Utils.TOPOLOGY_TEXT),new ImageView(image),new Label(Utils.TOPOLOGY_ADITIONAL_INFO)));
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.getDialogPane().setPrefSize(550, 600);
        dialog.show();
    }

    /**
     * On back pressed.
     *
     */
    @FXML
    public void onBackPressed() {
        viewController.returnDataEntry();
    }
}
