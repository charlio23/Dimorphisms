package dimorphisms;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


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
        primaryStage.getIcons().add(new Image(getClass().getResource("/startIcon.png").toExternalForm()));
        this.primaryStage = primaryStage;
        domainController = new DomainController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("/MainMenu.fxml").openStream());
        MainMenu fooController = (MainMenu) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.setTitle("Main Menu");
        mainMenuScene = new Scene(p,800,500);
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
    public  boolean checkForMaterial(String materialName) {
        return domainController.checkMaterialName(materialName);
    }

    public void newMaterial(String materialName) throws IOException {

                FXMLLoader fxmlLoader = new FXMLLoader();
                AnchorPane pane = fxmlLoader.load(getClass().getResource("/DataEntry.fxml").openStream());
                DataEntry dataEntry = (DataEntry) fxmlLoader.getController();
                dataEntry.setViewController(this);
                dataEntry.setMaterialName(materialName);
                primaryStage.setTitle("Data Entry");
                dataEntryScene = new Scene(pane, 800, 500);
                dataEntryScene.getStylesheets().add("/dataEntryStyle.css");
                primaryStage.setScene(dataEntryScene);
                primaryStage.show();
                domainController.newMaterial(materialName);
                fxmlLoader = new FXMLLoader();
                AnchorPane p = fxmlLoader.load(getClass().getResource("/GraphicView.fxml").openStream());
                GraphicView fooController = (GraphicView) fxmlLoader.getController();
                fooController.setViewController(this, true);
                graphicViewScene = new Scene(p,800,500);
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
        Scene stableDiagramScene = new Scene(p,800,500);
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
    public ArrayList<String> addSolid1Vapor(double a, double b, double c, boolean isLog) {
        return domainController.addVaporSolid1(a,b,c,isLog);
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
    public ArrayList<String> addSolid2Vapor(double a, double b, double c, boolean isLog) {
        return domainController.addVaporSolid2(a,b,c,isLog);
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
    public ArrayList<String> addLiquidVapor(double a, double b, double c, boolean isLog) {
        return domainController.addLiquidVapor(a,b,c,isLog);
    }

    /**
     * Add liquid solid 1 boolean.
     *
     * @param dpdt the dpdt
     * @return the boolean
     */
    public ArrayList<String> addLiquidSolid1(double dpdt) {
        return domainController.addLiquidSolid1(dpdt);
    }

    /**
     * Add liquid solid 2 boolean.
     *
     * @param dpdt the dpdt
     * @return the boolean
     */
    public ArrayList<String> addLiquidSolid2(double dpdt) {
        return domainController.addLiquidSolid2(dpdt);
    }

    /**
     * Add solid 1 solid 2 boolean.
     *
     * @param dpdt the dpdt
     * @return the boolean
     */
    public ArrayList<String> addSolid1Solid2(double dpdt) {
        return domainController.addSolid1Solid2(dpdt);
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

    public ArrayList<String> addLiquidSolid1VaporTemp(double temp) {
        return domainController.addTempLV1(temp);
    }

    public ArrayList<String> addLiquidSolid2VaporTemp(double temp) {
        return domainController.addTempLV2(temp);
    }

    public ArrayList<String> addSolid1Solid2VaporTemp(double temp) {
        return domainController.addTempV12(temp);
    }

    public boolean changeScale(double xMin, double xMax, double yMin, double yMax, boolean graphic) {
        domainController.changeScale(xMin, xMax, yMin, yMax, graphic);
        return true;
    }

    public void autoScale(boolean graphic) {
        domainController.autoScale(graphic);
    }

    public int getTopology() {
        return domainController.getTopology();
    }

    public void deleteMaterial(String name) {
        domainController.deleteMaterial(name);
    }

    public ArrayList<String> getMaterialNames() {
        return domainController.listMaterialNames();
    }

    public void loadMaterial(String materialName) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane pane = fxmlLoader.load(getClass().getResource("/DataEntry.fxml").openStream());
        DataEntry dataEntry = (DataEntry) fxmlLoader.getController();
        dataEntry.setViewController(this);
        dataEntry.setMaterialName(materialName);
        primaryStage.setTitle("Data Entry");
        dataEntryScene = new Scene(pane, 800, 500);
        dataEntryScene.getStylesheets().add("/dataEntryStyle.css");
        primaryStage.setScene(dataEntryScene);
        primaryStage.show();
        dataEntry.setMaterialInfo(domainController.loadMaterial(materialName));
        fxmlLoader = new FXMLLoader();
        AnchorPane p = fxmlLoader.load(getClass().getResource("/GraphicView.fxml").openStream());
        GraphicView fooController = (GraphicView) fxmlLoader.getController();
        fooController.setViewController(this, true);
        graphicViewScene = new Scene(p,800,500);
        graphicViewScene.getStylesheets().add("/chartStyle.css");

    }

    public ArrayList<String> getUIInformation(){
        return domainController.getUIInformation();
    }

    public boolean isMaterialComplete() {
        return domainController.isMaterialComplete();
    }
}