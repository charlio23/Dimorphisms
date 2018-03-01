import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class ViewController extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("../resources/MainMenu.fxml").openStream());
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
        Pane p = fxmlLoader.load(getClass().getResource("../resources/Second.fxml").openStream());
        Second fooController = (Second) fxmlLoader.getController();
        fooController.setViewController(this);

        primaryStage.close();
        primaryStage.setScene(new Scene(p, 300, 275));
        primaryStage.show();
    }
}