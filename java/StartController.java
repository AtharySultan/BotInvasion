

import java.io.File;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


/**
 * FXML Controller class
 *
 * @author athary
 */
public class StartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button ButtonStart;
    
     @FXML
    private MediaView mediaView;
    
    private MediaPlayer mediaPlayer;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Media media = new Media(new File("src/bac.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }


@FXML
public void ButtonStar(ActionEvent event) throws Exception {
        mediaPlayer.stop();
        URL url = new File("src/login.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene LogInPage = new Scene(root);
        Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(LogInPage);
        stage.show();
    }






}
