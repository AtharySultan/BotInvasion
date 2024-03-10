

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author athary
 */
public class StagesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView stage1;
    @FXML
    private ImageView stage2;
    @FXML
    private ImageView stage3;
    @FXML
    private ImageView scoreim;
    @FXML
    Button lvl1btn;
    static boolean lv1 = false;

    @FXML
    Button lvl2btn;
    static boolean lv2 = false;

    @FXML
    Button lvl3btn;
    static boolean lv3 = false;
    
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
    public void scaleUp1(ImageView b) {
        b.setScaleX(b.getScaleX() - 0.1);
        b.setScaleY(b.getScaleY() - 0.1);
    }

    public void scaleDown1(ImageView b) {
        b.setScaleX(b.getScaleX() + 0.1);
        b.setScaleY(b.getScaleY() + 0.1);

    }
    
    @FXML
    public void scoreEnterMouse(MouseEvent e) {
        scaleUp1(scoreim);
    }

    @FXML
    public void scoreExitMouse(MouseEvent e) {
        scaleDown1(scoreim);
    }
    
    
    @FXML
    public void ScoreImage(MouseEvent event) throws Exception {
        URL url = new File("src/score.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene LogInPage = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(LogInPage);
        stage.show();

    }

    @FXML
    public void BackButton(ActionEvent event) throws Exception {
        try {
            mediaPlayer.stop();
            URL url = new File("src/story.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene LogInPage = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(LogInPage);
            stage.show();
        } catch (RuntimeException io) {
            System.out.println(io.getMessage());
        }
    }

    @FXML
    void selectlvl(ActionEvent event) throws Exception {
        if (event.getSource() == lvl1btn && !lv1) {
            mediaPlayer.stop();
            URL url = new File("src/level1.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene LogInPage = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(LogInPage);
            stage.show();
            lv1 = true;

        }else if (event.getSource() == lvl2btn && !lv2) {
            mediaPlayer.stop();
            URL url = new File("src/stage2.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene LogInPage = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(LogInPage);
            stage.show();
            lv2 = true;
            
        } else if (event.getSource() == lvl3btn && !lv3) {
            mediaPlayer.stop();
            URL url = new File("src/Sample.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene LogInPage = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(LogInPage);
            stage.show();
            lv3 = true;
        } else {
        mediaPlayer.stop();
        }
    }

}
