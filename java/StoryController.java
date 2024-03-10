
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author athary
 */
public class StoryController implements Initializable {

    Media media;
    MediaPlayer mediaPlayer;


    @FXML
    private Slider volumeSlider;

    @FXML
    private MediaView mediaView;

    @FXML
    private ImageView volume;

    @FXML
    private Button ButtonNext;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            media = new Media(new File("src/video.mp4").toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(StoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
     mediaPlayer = new MediaPlayer(media);
      mediaView.setMediaPlayer(mediaPlayer);
      mediaPlayer.stop();
      mediaPlayer.volumeProperty().bindBidirectional(volumeSlider.valueProperty());
    }

   public void play(MouseEvent event) {
    mediaPlayer.play();
   }

   public void pause(MouseEvent event) {
    mediaPlayer.pause();
   }

   public void repeat(MouseEvent event) {
    mediaPlayer.seek(Duration.ZERO);
    mediaPlayer.play();
   }

   public void mute(MouseEvent event) {

 if (mediaPlayer.getVolume() > 0) {
        mediaPlayer.setVolume(0);
        Image muteImage = new Image("mute.png");
        volume.setImage(muteImage);

    } else {
        mediaPlayer.setVolume(1);
        Image unMuteImage = new Image("volume.png");
        volume.setImage(unMuteImage);
    }

   }

   @FXML
public void ButtonNext(ActionEvent event) throws Exception {
        mediaPlayer.stop();
        URL url = new File("src/FXML3.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene LogInPage = new Scene(root);
        Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(LogInPage);
        stage.show();
    }





   public void volumeSlider(MouseEvent event) {

   }

}
