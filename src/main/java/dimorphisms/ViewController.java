package dimorphisms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        domainController = new DomainController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("../../resources/MainMenu.fxml").openStream());
        MainMenu fooController = (MainMenu) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.setTitle("Main Menu");
        mainMenuScene = new Scene(p,715,415);
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

                domainController.newMaterial(materialName);
                FXMLLoader fxmlLoader = new FXMLLoader();
                GridPane pane = fxmlLoader.load(getClass().getResource("../../resources/DataEntry.fxml").openStream());
                DataEntry dataEntry = (DataEntry) fxmlLoader.getController();
                dataEntry.setViewController(this);
                dataEntry.setMaterialName(materialName);
                primaryStage.setTitle("Data Entry");
                dataEntryScene = new Scene(pane, 715, 415);
                primaryStage.setScene(dataEntryScene);
                primaryStage.show();
    }

    /**
     * View graphic.
     *
     * @throws IOException the io exception
     */
    public void viewGraphic() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("../../resources/GraphicView.fxml").openStream());
        GraphicView fooController = (GraphicView) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.close();
        primaryStage.setTitle("Graphic View");
        primaryStage.setScene(new Scene(p, 715, 415));
        primaryStage.show();
    }

    /**
     * View stable diagram.
     *
     * @throws IOException the io exception
     */
    public void viewStableDiagram() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("../../resources/GraphicView.fxml").openStream());
        GraphicView fooController = (GraphicView) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.close();
        primaryStage.setTitle("Stable Diagram");
        primaryStage.setScene(new Scene(p, 715, 415));
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
        return true;
    }

    /**
     * Add liquid solid 1 boolean.
     *
     * @param temp the temp
     * @param dpdt the dpdt
     * @return the boolean
     */
    public boolean addLiquidSolid1(float temp, float dpdt) {
        return true;
    }

    /**
     * Add liquid solid 2 boolean.
     *
     * @param temp the temp
     * @param dpdt the dpdt
     * @return the boolean
     */
    public boolean addLiquidSolid2(float temp, float dpdt) {
        return true;
    }

    /**
     * Add solid 1 solid 2 boolean.
     *
     * @param temp the temp
     * @param dpdt the dpdt
     * @return the boolean
     */
    public boolean addSolid1Solid2(float temp, float dpdt) {
        return true;
    }

    public boolean saveMaterial() {
        return domainController.saveMaterial();
    }
}