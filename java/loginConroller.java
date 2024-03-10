

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Query;
import org.hibernate.Session;

public class loginConroller implements Initializable {

    @FXML
    private Label loginMessage;
    @FXML
    private TextField username;
    @FXML
    private TextField pa1;

    private Stage stage;
    private Scene scene1;
    private Parent r;
    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer2;

    private MediaPlayer mediaPlayer;

    private MediaPlayer mediaPlayer1;
    @FXML
    private ImageView loginButton;
    @FXML
    private ImageView signUpButton;
    private Scene scene2;

    private Stage stage1;
    @FXML
    private ImageView backgroundImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media = new Media(new File("src/bac.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

        Media media1 = new Media(new File("src/intro.mp3").toURI().toString());
        mediaPlayer1 = new MediaPlayer(media1);
        mediaView.setMediaPlayer(mediaPlayer1);

        Media media2 = new Media(new File("src/bubble.mp3").toURI().toString());
        mediaPlayer2 = new MediaPlayer(media2);
        mediaView.setMediaPlayer(mediaPlayer2);
    }

    public void scaleUp(ImageView b) {
        b.setScaleX(b.getScaleX() - 0.1);
        b.setScaleY(b.getScaleY() - 0.1);
    }

    public void scaleDown(ImageView b) {
        b.setScaleX(b.getScaleX() + 0.1);
        b.setScaleY(b.getScaleY() + 0.1);

    }

    @FXML
    public void loginButtonOnAction(ActionEvent e) throws IOException {
        mediaPlayer2.play();
        if (!username.getText().isEmpty() && !pa1.getText().isEmpty()) {

            USER_INFO.uListSetter();
            
            for (int i = 0; i < USER_INFO.uList.size(); i++) {
                if (username.getText().equals(USER_INFO.uList.get(i).getUserName()) && pa1.getText().equals(USER_INFO.uList.get(i).getUserPassword())) {
                    USER_INFO.curUserIndex = i+1;
                    break;
                }
            }
            
            if (USER_INFO.curUserIndex < -1) {
                loginMessage.setText("Username and password are not vaild.");
            } else {
                Parent ro = FXMLLoader.load(getClass().getResource("story.fxml"));
                stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene2 = new Scene(ro);
                stage1.setScene(scene2);
                stage1.show();
                mediaPlayer1.play();
                mediaPlayer.stop();
            }

        } else {
            loginMessage.setText("Please enter username and password .");
        }
    }// end method

    @FXML
    public void signupButtonOnAction(ActionEvent e) throws IOException {
        mediaPlayer2.play();
        Parent r = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene1 = new Scene(r);
        stage.setScene(scene1);
        stage.show();
        mediaPlayer1.play();
    }

    @FXML
    public void loginEnterMouse(MouseEvent e) {
        scaleUp(loginButton);
    }

    @FXML
    public void loginExitMouse(MouseEvent e) {
        scaleDown(loginButton);
    }

    @FXML
    public void signEnterMouse(MouseEvent e) {
        scaleUp(signUpButton);
    }

    @FXML
    public void signUpExitMouse(MouseEvent e) {
        scaleDown(signUpButton);
    }

}
