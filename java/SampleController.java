
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SampleController {

    @FXML
    Button flask;
    @FXML
    private CheckBox ox;
    @FXML
    private CheckBox so;
    @FXML
    private CheckBox po;
    @FXML
    private CheckBox hy;
    @FXML
    private CheckBox he;
    @FXML
    private Hyperlink createLink;
    @FXML
    private RadioButton Lswitch;
    @FXML
    private Rectangle lightRec;
    static boolean waterPuzzle = false;
    @FXML
    Button cal;
    @FXML
    private Label dateLabel;
    @FXML
    private Text dateText;
    @FXML
    private DatePicker dateQ;
    static boolean datePuzzle = false;
    @FXML
    Button bookC;
    @FXML
    Button flasF;
    static boolean flasksPuzzle = false;
    @FXML
    private Hyperlink OrFlask;
    @FXML
    private Hyperlink grFlask;

    @FXML
    private Hyperlink redFlask;

    @FXML
    private Hyperlink yeFlask;
    @FXML
    private RadioButton gr;
    @FXML
    private RadioButton or;
    @FXML
    private RadioButton rr;
    @FXML
    private RadioButton yr;
    static boolean yechk = false;
    static boolean grchk = false;
    static boolean orchk = false;
    static boolean rechk = false;

    @FXML
    private ImageView tkn1;

    @FXML
    private ImageView tkn2;

    @FXML
    private ImageView tkn3;

    @FXML
    private ImageView tkn4;
    static int numOfToken3 = 0;
    @FXML
    private Label tknLabel;

    @FXML
    private Text ctl;
    @FXML
    private RadioButton clrad;

    @FXML
    void flaskAction(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;
        Alert a = new Alert(AlertType.NONE);

        if (event.getSource() == flask) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("flaskScene.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(flask.getScene().getWindow());
            stage.showAndWait();
            if (waterPuzzle == true) {
                flask.setDisable(true);
                if (waterPuzzle == true && datePuzzle == true && flasksPuzzle == true) {
                    Media sound2 = new Media(new File("src/sound4.mp3").toURI().toURL().toExternalForm());
                    MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                    a.setAlertType(AlertType.CONFIRMATION);
                    a.setContentText(String.format("Collected tokens: %d/4", numOfToken3));
                    a.setHeaderText("LEVEL COMPLETED!");
                    //a.show();
                    mediaPlayer2.play();
                    tkn1.setDisable(true);
                    tkn2.setDisable(true);
                    tkn3.setDisable(true);
                    tkn4.setDisable(true);
                    //a.close();
                    
                    ////////////////////////////////////
                    //THIS CODE 
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                                        
                    LEVELS tknUpdate = null;
                    tknUpdate = (LEVELS) session.get(LEVELS.class, 3); //3 = levelID (primary key)
                    tknUpdate.setCollectedTKNs(numOfToken3);
                    session.update(tknUpdate);
                    tx.commit();
                    session.close();
                    
                    
                    
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    
                    SCORE uScore = null;
                    uScore = (SCORE) session.get(SCORE.class, USER_INFO.curUserIndex); 
                    uScore.setLevelID(3);
                    uScore.setCollectedTokens(uScore.getCollectedTokens()+numOfToken3);
                    uScore.setCompletedLvl(uScore.getCompletedLvl()+1);
                    session.update(uScore);
                    
                    tx.commit();
                    session.close();
                    
                    ////////////////////////////////////
                    
                    
                    URL url = new File("src/end.fxml").toURI().toURL();
                    Parent root1 = FXMLLoader.load(url);
                    Scene LogInPage = new Scene(root1);
                    Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage1.setScene(LogInPage);
                    stage1.show();
                }
            }
        } else if (event.getSource() == cal) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("dateScene.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(cal.getScene().getWindow());
            stage.showAndWait();
            if (datePuzzle == true) {
                cal.setDisable(true);
                if (waterPuzzle == true && datePuzzle == true && flasksPuzzle == true) {
                    Media sound2 = new Media(new File("src/sound4.mp3").toURI().toURL().toExternalForm());
                    MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                    a.setAlertType(AlertType.CONFIRMATION);
                    a.setContentText(String.format("Collected tokens: %d/4", numOfToken3));
                    a.setHeaderText("LEVEL COMPLETED!");
                    //a.show();
                    mediaPlayer2.play();
                    tkn1.setDisable(true);
                    tkn2.setDisable(true);
                    tkn3.setDisable(true);
                    tkn4.setDisable(true);
                    //a.close();
                    
                    ////////////////////////////////////
                    //THIS CODE 
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                                        
                    LEVELS tknUpdate = null;
                    tknUpdate = (LEVELS) session.get(LEVELS.class, 3); //3 = levelID (primary key)
                    tknUpdate.setCollectedTKNs(numOfToken3);
                    session.update(tknUpdate);
                    tx.commit();
                    session.close();
                    
                    
                    
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    
                    SCORE uScore = null;
                    uScore = (SCORE) session.get(SCORE.class, USER_INFO.curUserIndex); 
                    uScore.setLevelID(3);
                    uScore.setCollectedTokens(uScore.getCollectedTokens()+numOfToken3);
                    uScore.setCompletedLvl(uScore.getCompletedLvl()+1);
                    session.update(uScore);
                    
                    tx.commit();
                    session.close();
                    
                    ////////////////////////////////////

                    URL url = new File("src/end.fxml").toURI().toURL();
                    Parent root1 = FXMLLoader.load(url);
                    Scene LogInPage = new Scene(root1);
                    Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage1.setScene(LogInPage);
                    stage1.show();
                }
            }
        } else if (event.getSource() == bookC) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("bookHint.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(bookC.getScene().getWindow());
            stage.showAndWait();
        } else if (event.getSource() == flasF) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("flaskOrder.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(flasF.getScene().getWindow());
            stage.showAndWait();

            if (flasksPuzzle == true) {
                Media sound = new Media(new File("src/sound2.mp3").toURI().toURL().toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                flasF.setDisable(true);
            }
            if (waterPuzzle == true && datePuzzle == true && flasksPuzzle == true) {
                Media sound2 = new Media(new File("src/sound4.mp3").toURI().toURL().toExternalForm());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                a.setAlertType(AlertType.CONFIRMATION);
                a.setContentText(String.format("Collected tokens: %d/4", numOfToken3));
                a.setHeaderText("LEVEL COMPLETED!");
                //a.show();
                mediaPlayer2.play();
                tkn1.setDisable(true);
                tkn2.setDisable(true);
                tkn3.setDisable(true);
                tkn4.setDisable(true);
                //a.close();
                
                ////////////////////////////////////
                    //THIS CODE 
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                                        
                    LEVELS tknUpdate = null;
                    tknUpdate = (LEVELS) session.get(LEVELS.class, 3); //3 = levelID (primary key)
                    tknUpdate.setCollectedTKNs(numOfToken3);
                    session.update(tknUpdate);
                    tx.commit();
                    session.close();
                    
                    
                    
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    
                    SCORE uScore = null;
                    uScore = (SCORE) session.get(SCORE.class, USER_INFO.curUserIndex); 
                    uScore.setLevelID(3);
                    uScore.setCollectedTokens(uScore.getCollectedTokens()+numOfToken3);
                    uScore.setCompletedLvl(uScore.getCompletedLvl()+1);
                    session.update(uScore);
                    
                    tx.commit();
                    session.close();
                    
                    ////////////////////////////////////
                    
                URL url = new File("src/end.fxml").toURI().toURL();
                Parent root1 = FXMLLoader.load(url);
                Scene LogInPage = new Scene(root1);
                Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage1.setScene(LogInPage);
                stage1.show();
            }
        }

    }

    @FXML
    void crWater(MouseEvent event) throws IOException {

        if (ox.isSelected() && hy.isSelected() && !(so.isSelected()) && !(po.isSelected()) && !(he.isSelected())) {
            waterPuzzle = true;
            Stage stage = (Stage) createLink.getScene().getWindow();
            Media sound = new Media(new File("src/sound2.mp3").toURI().toURL().toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            stage.close();

        }

    }

    @FXML
    void lightsOff(ActionEvent event) throws IOException {

        Media sound = new Media(new File("src/sound3.mp3").toURI().toURL().toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        if (Lswitch.isSelected()) {
            mediaPlayer.play();
            lightRec.setVisible(true);
            dateLabel.setEffect(null);
            dateLabel.setTextFill(new Color(0.9294, 0.9725, 0.9059, 1.0));

        } else {
            mediaPlayer.play();
            lightRec.setVisible(false);
            DropShadow labelEffect = new DropShadow();
            labelEffect.setRadius(10.0);
            labelEffect.setWidth(21.0);
            labelEffect.setHeight(21.0);
            labelEffect.setColor(new Color(0.0, 0.0, 0.0, 1.0));
            dateLabel.setEffect(labelEffect);
            dateLabel.setTextFill(new Color(0.0, 0.0, 0.0, 1.0));
            GaussianBlur blur = new GaussianBlur();
            dateLabel.setEffect(blur);

        }

    }

    @FXML
    void dateP(ActionEvent event) throws IOException {

        LocalDate pDate = dateQ.getValue();
        dateText.setText(dateQ.getValue().toString());
        if (pDate.toString().equals("2023-03-05")) {
            datePuzzle = true;
            Stage stage = (Stage) dateQ.getScene().getWindow();
            Media sound = new Media(new File("src/sound2.mp3").toURI().toURL().toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            stage.close();
        }

    }

    @FXML
    void selectFlask(KeyEvent event) throws IOException {

        Media sound = new Media(new File("src/sound3.mp3").toURI().toURL().toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        switch (event.getCode()) {

            case LEFT:
                if (or.isSelected() && !or.isDisabled()) {
                    OrFlask.translateXProperty().set(OrFlask.getTranslateX() - 3);
                } else if (gr.isSelected() && !gr.isDisabled()) {
                    grFlask.translateXProperty().set(grFlask.getTranslateX() - 3);
                } else if (rr.isSelected() && !rr.isDisabled()) {
                    redFlask.translateXProperty().set(redFlask.getTranslateX() - 3);
                } else if (yr.isSelected() && !yr.isDisabled()) {
                    yeFlask.translateXProperty().set(yeFlask.getTranslateX() - 3);
                }
                break;

            case RIGHT:
                if (or.isSelected() && !or.isDisabled()) {
                    OrFlask.translateXProperty().set(OrFlask.getTranslateX() + 3);
                } else if (gr.isSelected() && !gr.isDisabled()) {
                    grFlask.translateXProperty().set(grFlask.getTranslateX() + 3);
                } else if (rr.isSelected() && !rr.isDisabled()) {
                    redFlask.translateXProperty().set(redFlask.getTranslateX() + 3);
                } else if (yr.isSelected() && !yr.isDisabled()) {
                    yeFlask.translateXProperty().set(yeFlask.getTranslateX() + 3);
                }
                break;

            case UP:
                if (or.isSelected() && !or.isDisabled()) {
                    OrFlask.translateYProperty().set(OrFlask.getTranslateY() - 3);
                } else if (gr.isSelected() && !gr.isDisabled()) {
                    grFlask.translateYProperty().set(grFlask.getTranslateY() - 3);
                } else if (rr.isSelected() && !rr.isDisabled()) {
                    redFlask.translateYProperty().set(redFlask.getTranslateY() - 3);
                } else if (yr.isSelected() && !yr.isDisabled()) {
                    yeFlask.translateYProperty().set(yeFlask.getTranslateY() - 3);
                }
                break;

            case DOWN:
                if (or.isSelected() && !or.isDisabled()) {
                    OrFlask.translateYProperty().set(OrFlask.getTranslateY() + 3);
                } else if (gr.isSelected() && !gr.isDisabled()) {
                    grFlask.translateYProperty().set(grFlask.getTranslateY() + 3);
                } else if (rr.isSelected() && !rr.isDisabled()) {
                    redFlask.translateYProperty().set(redFlask.getTranslateY() + 3);
                } else if (yr.isSelected() && !yr.isDisabled()) {
                    yeFlask.translateYProperty().set(yeFlask.getTranslateY() + 3);
                }
                break;

            default:
                break;
        }

        // to calculate required translatition:
        // System.out.println("greenX= "+grFlask.getTranslateX()+"\n"+"greenY=
        // "+grFlask.getTranslateY()+"\n"+"yellowX=
        // "+yeFlask.getTranslateX()+"\n"+"yellowY= "+yeFlask.getTranslateY()+"\n"+
        // "orangeX= "+OrFlask.getTranslateX()+"\n"+"orangeY=
        // "+OrFlask.getTranslateY()+"\n"+"redX= "+redFlask.getTranslateX()+"\n"+"redY=
        // "+redFlask.getTranslateY()+"\n");
        if (yeFlask.getTranslateX() <= -39 && yeFlask.getTranslateX() >= -90 && yeFlask.getTranslateY() <= 54
                && yeFlask.getTranslateY() >= 30 && yechk == false) {
            mediaPlayer.play();
            yechk = true;
            yr.setDisable(true);

        }

        if (redFlask.getTranslateX() >= 30 && redFlask.getTranslateX() <= 72 && redFlask.getTranslateY() <= 189
                && redFlask.getTranslateY() >= 165 && rechk == false) {
            mediaPlayer.play();
            rechk = true;
            rr.setDisable(true);
        }

        if (grFlask.getTranslateX() <= -30 && grFlask.getTranslateX() >= -75 && grFlask.getTranslateY() <= 51
                && grFlask.getTranslateY() >= 27 && grchk == false) {
            mediaPlayer.play();
            grchk = true;
            gr.setDisable(true);
        }

        if (OrFlask.getTranslateX() >= 42 && OrFlask.getTranslateX() <= 78 && OrFlask.getTranslateY() <= 192
                && OrFlask.getTranslateY() >= 174 && orchk == false) {
            mediaPlayer.play();
            orchk = true;
            or.setDisable(true);
        }

        if (yechk == true && rechk == true && grchk == true && orchk == true) {
            flasksPuzzle = true;
            Stage stage = (Stage) or.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void tokenAction(MouseEvent event) throws IOException {

        Media sound = new Media(new File("src/sound.mp3").toURI().toURL().toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        if (event.getSource() == tkn1) {
            numOfToken3++;
            tkn1.setVisible(false);
            mediaPlayer.play();
            tknLabel.setText(String.format("Collected tokens: %d/4", numOfToken3));
        } else if (event.getSource() == tkn2) {
            numOfToken3++;
            tkn2.setVisible(false);
            mediaPlayer.play();
            tknLabel.setText(String.format("Collected tokens: %d/4", numOfToken3));
        } else if (event.getSource() == tkn3) {
            numOfToken3++;
            tkn3.setVisible(false);
            mediaPlayer.play();
            tknLabel.setText(String.format("Collected tokens: %d/4", numOfToken3));
        } else if (event.getSource() == tkn4) {
            numOfToken3++;
            tkn4.setVisible(false);
            mediaPlayer.play();
            tknLabel.setText(String.format("Collected tokens: %d/4", numOfToken3));
        }

    }

}
