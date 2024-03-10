
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

public class stage2Controller implements Initializable {

    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    System Logger;
    private int counter = 0;
    @FXML
    private ImageView token1;
    @FXML
    private ImageView token2;
    @FXML
    private ImageView token3;
    boolean flag1;
    boolean flag2;
    boolean flag3;
    @FXML
    private Label labe;
    MediaView mediaView;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;
    MediaPlayer mediaPlayer4;
    MediaPlayer mediaPlayer5;
    MediaPlayer mediaPlayer6;
    MediaPlayer mediaPlayer7;
 

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media = new Media(new File("src/sound3.mp3").toURI().toString());
        Media media1 = new Media(new File("src/bubble.mp3").toURI().toString());
        Media media2 = new Media(new File("src/back.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer1 = new MediaPlayer(media);
        mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer3 = new MediaPlayer(media1);
        mediaPlayer4 = new MediaPlayer(media1);
        mediaPlayer5 = new MediaPlayer(media1);
        mediaPlayer6 = new MediaPlayer(media1);
        mediaPlayer7 = new MediaPlayer(media2);
        mediaPlayer7.play();

    }

    public void signupButtonOnAction(ActionEvent e) throws IOException {
        mediaPlayer3.play();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("memorey_game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 544, 361);
        Stage stage = new Stage();
        stage.setTitle("MEMOREY GAME");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(evt -> {
            // allow user to decide between yes and no
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?", ButtonType.YES, ButtonType.NO);

            // clicking X also means no
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (ButtonType.NO.equals(result)) {
                // consume event i.e. ignore close request
                evt.consume();
            }
        });

    }

    public void Bejeweled_btn(ActionEvent e) throws IOException {
        mediaPlayer4.play();
        Stage stage = new Stage();
        BejeweledApp app = new BejeweledApp();
        Scene scene = new Scene(app.createContent(), 599, 599);
        stage.setScene(scene);
        stage.showAndWait();
        stage.setOnCloseRequest(evt -> {
            // allow user to decide between yes and no
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?", ButtonType.YES, ButtonType.NO);

            // clicking X also means no
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (ButtonType.NO.equals(result)) {
                // consume event i.e. ignore close request
                evt.consume();
            }
        });

    }

    public void numbergame_btn(ActionEvent e) throws IOException {
        mediaPlayer5.play();
        Stage stage = new Stage();
        NumbersGame app = new NumbersGame();
        stage.setScene(new Scene(app.createContent(), 544, 361));
//        char[] alphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();
//        for (int i = 0; i < alphabets.length; i++) {
//            System.out.println(i + 1 + " - " + alphabets[i]);
//        }
        stage.showAndWait();
    }

    //tokens
    public void Token1(MouseEvent event) {

        token1.setVisible(false);
        mediaPlayer.play();

        if (!flag1) {
            flag1 = true;
            counter++;
            labe.setText(String.valueOf(counter));

        }
    }

    public void Token2(MouseEvent event) {

        token2.setVisible(false);
        mediaPlayer1.play();
        if (!flag2) {
            flag2 = true;
            counter++;
            labe.setText(String.valueOf(counter));
        }
    }

    public void Token3(MouseEvent event) {

        token3.setVisible(false);
        mediaPlayer2.play();
        if (!flag3) {
            flag3 = true;
            counter++;
            labe.setText(String.valueOf(counter));

        }
    }

    public void cod(ActionEvent e) throws IOException {
        mediaPlayer6.play();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("codeofstage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 283, 392);
        Stage stage1 = new Stage();
        stage1.setTitle("code stage 2");
        stage1.setScene(scene);
        stage1.showAndWait();

        if (codeCntroller.puzzle == true) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            LEVELS tknUpdate = null;
            tknUpdate = (LEVELS) session.get(LEVELS.class, 2); //2 = levelID (primary key)
            tknUpdate.setCollectedTKNs(counter);
            session.update(tknUpdate);
            tx.commit();
            session.close();

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            SCORE uScore = null;
            uScore = (SCORE) session.get(SCORE.class, USER_INFO.curUserIndex);
            uScore.setLevelID(2);
            uScore.setCollectedTokens(uScore.getCollectedTokens() + counter);
            uScore.setCompletedLvl(uScore.getCompletedLvl() + 1);
            session.update(uScore);

            tx.commit();
            session.close();

            ////////////////////////////////////
            URL url = new File("src/FXML3.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene LogInPage = new Scene(root1);
            Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage2.setScene(LogInPage);
            stage2.show();
        }

    }
}


