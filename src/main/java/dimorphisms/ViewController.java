package dimorphisms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * The type View controller.
 */
public class ViewController extends Application {

    private Stage primaryStage;
    private DomainController domainController;
    private Scene dataEntryScene;
    private Scene mainMenuScene;
    private Scene graphicViewScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.getIcons().add(new Image("/startIcon.png"));
        this.primaryStage = primaryStage;
        domainController = new DomainController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("/MainMenu.fxml").openStream());
        MainMenu fooController = (MainMenu) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.setTitle("Main Menu");
        mainMenuScene = new Scene(p,715,415);
        mainMenuScene.getStylesheets().add("/mainMenuStyle.css");
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * New material.
     *
     * @param materialName the material name
     * @throws IOException the io exception
     */
    public void newMaterial(String materialName) throws IOException {

                FXMLLoader fxmlLoader = new FXMLLoader();
                GridPane pane = fxmlLoader.load(getClass().getResource("/DataEntry.fxml").openStream());
                DataEntry dataEntry = (DataEntry) fxmlLoader.getController();
                dataEntry.setViewController(this);
                dataEntry.setMaterialName(materialName);
                primaryStage.setTitle("Data Entry");
                dataEntryScene = new Scene(pane, 715, 415);
                primaryStage.setScene(dataEntryScene);
                primaryStage.show();
                domainController.newMaterial(materialName);
                fxmlLoader = new FXMLLoader();
                Pane p = fxmlLoader.load(getClass().getResource("/GraphicView.fxml").openStream());
                GraphicView fooController = (GraphicView) fxmlLoader.getController();
                fooController.setViewController(this, true);
                graphicViewScene = new Scene(p,715,415);
                graphicViewScene.getStylesheets().add("/chartStyle.css");

    }

    /**
     * View graphic.
     *
     * @throws IOException the io exception
     */
    public void viewGraphic() throws IOException {
        primaryStage.setScene(graphicViewScene);
    }

    /**
     * View stable diagram.
     *
     * @throws IOException the io exception
     */
    public void viewStableDiagram() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("/GraphicView.fxml").openStream());
        GraphicView fooController = (GraphicView) fxmlLoader.getController();
        fooController.setViewController(this, false);

        primaryStage.close();
        primaryStage.setTitle("Stable Diagram");
        Scene stableDiagramScene = new Scene(p,715,415);
        stableDiagramScene.getStylesheets().add("/stabDiagramStyle.css");

        primaryStage.setScene(stableDiagramScene);
        primaryStage.show();
    }

    /**
     * Return data entry.
     */
    public void returnDataEntry() {
        primaryStage.setScene(dataEntryScene);

    }

    /**
     * Return main menu.
     */
    public void returnMainMenu() {
        primaryStage.setScene(mainMenuScene);
    }

    /**
     * Add solid 1 vapor boolean.
     *
     * @param a     the a
     * @param b     the b
     * @param c     the c
     * @param isLog the is log
     * @return the boolean
     */
    public boolean addSolid1Vapor(float a, float b, float c, boolean isLog) {
        domainController.addVaporSolid1(a,b,c,isLog);
        return true;
    }

    /**
     * Add solid 2 vapor boolean.
     *
     * @param a     the a
     * @param b     the b
     * @param c     the c
     * @param isLog the is log
     * @return the boolean
     */
    public boolean addSolid2Vapor(float a, float b, float c, boolean isLog) {
        domainController.addVaporSolid2(a,b,c,isLog);
        return true;
    }

    /**
     * Add liquid vapor boolean.
     *
     * @param a     the a
     * @param b     the b
     * @param c     the c
     * @param isLog the is log
     * @return the boolean
     */
    public boolean addLiquidVapor(float a, float b, float c, boolean isLog) {
        domainController.addLiquidVapor(a,b,c,isLog);
        return true;
    }

    /**
     * Add liquid solid 1 boolean.
     *
     * @param dpdt the dpdt
     * @return the boolean
     */
    public boolean addLiquidSolid1(float dpdt) {
        domainController.addLiquidSolid1(dpdt);
        return true;
    }

    /**
     * Add liquid solid 2 boolean.
     *
     * @param dpdt the dpdt
     * @return the boolean
     */
    public boolean addLiquidSolid2(float dpdt) {
        domainController.addLiquidSolid2(dpdt);
        return true;
    }

    /**
     * Add solid 1 solid 2 boolean.
     *
     * @param dpdt the dpdt
     * @return the boolean
     */
    public boolean addSolid1Solid2(float dpdt) {
        domainController.addSolid1Solid2(dpdt);
        return true;
    }

    public boolean saveMaterial() {
        return domainController.saveMaterial();
    }

    public LineChart getLinearGraphic(boolean graphic) {
        return domainController.getLinearGraphic(graphic);
    }

    public LineChart[] getLogGraphic() {
        return domainController.getLogGraphic();
    }

    public void addLiquidSolid1VaporTemp(float temp) {
        domainController.addTempLV1(temp);
    }

    public void addLiquidSolid2VaporTemp(float temp) {
        domainController.addTempLV2(temp);
    }

    public void addSolid1Solid2VaporTemp(float temp) {
        domainController.addTempV12(temp);
    }

    public void changeScale(double xMin, double xMax, double yMin, double yMax, boolean graphic) {
        domainController.changeScale(xMin, xMax, yMin, yMax, graphic);
    }

    public void autoScale(boolean graphic) {
        domainController.autoScale(graphic);
    }

    public int getTopology() {
        return domainController.getTopology();
    }
}