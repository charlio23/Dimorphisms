import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.rmi.CORBA.Util;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Optional;


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
        Pane p = fxmlLoader.load(getClass().getResource("../resources/MainMenu.fxml").openStream());
        MainMenu fooController = (MainMenu) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.setTitle("Main Menu");
        mainMenuScene = new Scene(p,715,415);
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void newMaterial() throws IOException {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("New Dimorphism");
        dialog.setHeaderText("Enter the dimorphism name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            if (result.get().matches(Utils.nameRegExp)) {
                System.out.println("Name is " + result.get());
                domainController.createMaterial(result.get());
                domainController.saveMaterial();
                FXMLLoader fxmlLoader = new FXMLLoader();
                GridPane pane = fxmlLoader.load(getClass().getResource("../resources/DataEntry.fxml").openStream());
                DataEntry dataEntry = (DataEntry) fxmlLoader.getController();
                dataEntry.setViewController(this);
                dataEntry.setMaterialName(result.get());
                primaryStage.setTitle("Data Entry");
                dataEntryScene = new Scene(pane, 715, 415);
                primaryStage.setScene(dataEntryScene);
                primaryStage.show();
            } else {Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Dimorphism name input ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please, provide a name with at least 3 characters");
                alert.showAndWait();

            }
        } else {
            System.out.println("Pressed cancel button");
        }
    }

    public void viewGraphic() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("../resources/GraphicView.fxml").openStream());
        GraphicView fooController = (GraphicView) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.close();
        primaryStage.setTitle("Graphic View");
        primaryStage.setScene(new Scene(p, 715, 415));
        primaryStage.show();
    }

    public void returnDataEntry() {
        primaryStage.setScene(dataEntryScene);

    }

    public void returnMainMenu() {
        primaryStage.setScene(mainMenuScene);
    }
}