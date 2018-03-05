import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;


public class ViewController extends Application {

    private Stage primaryStage;
    private DomainController domainController;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        domainController = new DomainController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("MainMenu.fxml").openStream());
        MainMenu fooController = (MainMenu) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(p, 300, 275));
        primaryStage.show();
    }

    public void call(){
        System.out.println("CALL FROM VIEW!");
    }



    public static void main(String[] args) {
        launch(args);
    }


    public void changeStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("Second.fxml").openStream());
        Second fooController = (Second) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.close();
        primaryStage.setScene(new Scene(p, 1000, 1000));
        primaryStage.show();
    }

    public void firstStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("MainMenu.fxml").openStream());
        MainMenu fooController = (MainMenu) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(p, 300, 275));
        primaryStage.show();
    }

    public String newMaterial() throws IOException {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("New Dimorphism");
        dialog.setHeaderText("Enter the dimorphism name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        try {
            System.out.println("Name is" + result.get());
            return result.get();
        } catch (Exception e){
            System.out.println("Pressed cancel button");
            return "";
        }
    }
}