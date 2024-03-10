
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author athar
 */
public class AllPro extends Application {

    @Override
    public void start(Stage primaryStage)throws Exception {

        Parent root1 = FXMLLoader.load(getClass().getResource("start.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Start");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
