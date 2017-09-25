/**
 * Created by Alex on 21.09.2017.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    private static BorderPane root= new BorderPane();
    private static Stage primaryStage;
    public static BorderPane getRoot(){       return root;}

    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        URL munuBarUrl=getClass().getProtectionDomain().getClassLoader().getResource("fxml/menu.fxml");
        HBox box= FXMLLoader.load(munuBarUrl);
        URL paneOneUrl= getClass().getProtectionDomain().getClassLoader().getResource("fxml/paneLab1.fxml");
        AnchorPane paneOne= FXMLLoader.load(paneOneUrl);
        root.setTop(box);
        root.setCenter(paneOne);
        Scene scene = new Scene(root,700,900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
