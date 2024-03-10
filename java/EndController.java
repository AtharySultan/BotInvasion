


import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EndController implements Initializable {
    @FXML
    ImageView myImageView = new ImageView();
    // Create a new ImageView object
    @FXML
    private Label labelImage;
    @FXML
    private Label numOfTokens;
    @FXML
    ImageView ballons;
    @FXML
    ImageView ballons1;
    @FXML
    ImageView ballons2;
    @FXML
    ImageView ballons11;
    @FXML
    ImageView ballons21;
    @FXML
    ImageView ballons211;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

   USER_INFO userID = USER_INFO.uList.get(USER_INFO.getCurUserIndex() - 1);
    
    // Query the SCORE table to retrieve the user's score information
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("FROM SCORE WHERE userID = :userID");
    query.setParameter("userID", userID.getUserID());
    List<SCORE> resultList = query.list();
   
    
      SCORE score = resultList.get(0);
    if (!resultList.isEmpty()) { 
    numOfTokens.setText(Integer.toString(score.getCollectedTokens()));

    }

/////////////////////////////////////////////////////////////////////////////////////////
    
        myImageView.setPreserveRatio(false); // Maintain the aspect ratio of the image
        Font font = Font.font("System", FontWeight.BOLD, 15);
        labelImage.setFont(font);

        int getText = Integer.parseInt(numOfTokens.getText());

      

        if (getText > 6) {
            Transaction tx = session.beginTransaction();
            score.setPrize("YES");
            session.update(score);
            tx.commit();
            session.close();
            Image congratulationsImage = new Image("destryedRobots.jpg");
            myImageView.setImage(congratulationsImage); // Set the image of the ImageView to the congratulations image
            labelImage.setText("Congratulations!\n You've collected \nmore than 6 token!\n And saved the world! ");

            Image ballonsImage = new Image("singleBallon.png");
            Image ballonsDouble = new Image("doubleBallon.png");
            Image ballonsImage1 = new Image("singleBallon.png");
            Image ballonsDouble2 = new Image("doubleBallon.png");
            Image lastBallon = new Image("3rdballo.png");
            Image lasttBallon = new Image("3rdballo.png");
            
          

            ballons.setImage(ballonsImage);
            ballons1.setImage(ballonsDouble);
            ballons2.setImage(ballonsImage1);
            ballons11.setImage(ballonsDouble2);
            ballons21.setImage(lastBallon);
            ballons211.setImage(lasttBallon);



            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(5), ballons);
            translateTransition.setByY(-1000); // move the photo 200 pixels to the right

            // Create a Timeline to play the animation
            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO), new KeyFrame(Duration.seconds(0.5), e -> {
                translateTransition.play();
            }));
            timeline.setCycleCount(Timeline.INDEFINITE); // repeat the animation indefinitely

            timeline.play();

            /////////////////////////////////////////////////////////////

            TranslateTransition tt = new TranslateTransition(Duration.seconds(5), ballons1);
            tt.setByY(-1000); // move the photo 200 pixels to the right

            // Create a Timeline to play the animation
            Timeline tl = new Timeline(new KeyFrame(Duration.ZERO), new KeyFrame(Duration.seconds(0.5), e -> {
                tt.play();
            }));
            tl.setCycleCount(Timeline.INDEFINITE); // repeat the animation indefinitely

            tl.play();
            ///////////////////////////////////////////////

            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(5), ballons2);
            tt3.setByY(-1000); // move the photo 200 pixels to the right

            // Create a Timeline to play the animation
            Timeline tl2 = new Timeline(new KeyFrame(Duration.ZERO), new KeyFrame(Duration.seconds(0.5), e -> {
                tt3.play();
            }));
            tl2.setCycleCount(Timeline.INDEFINITE); // repeat the animation indefinitely

            tl2.play();
            /////////////////////////////

            TranslateTransition tt4 = new TranslateTransition(Duration.seconds(5), ballons11);
            tt4.setByY(-1000); // move the photo 200 pixels to the right

            // Create a Timeline to play the animation
            Timeline tl3 = new Timeline(new KeyFrame(Duration.ZERO), new KeyFrame(Duration.seconds(0.5), e -> {
                tt4.play();
            }));
            tl3.setCycleCount(Timeline.INDEFINITE); // repeat the animation indefinitely

            tl3.play();
            ////////////////////////////////////////////////////////////////

            TranslateTransition tt5 = new TranslateTransition(Duration.seconds(5), ballons21);
            tt5.setByY(-1000); // move the photo 200 pixels to the right

            // Create a Timeline to play the animation
            Timeline tl4 = new Timeline(new KeyFrame(Duration.ZERO), new KeyFrame(Duration.seconds(0.5), e -> {
                tt5.play();
            }));
            tl4.setCycleCount(Timeline.INDEFINITE); // repeat the animation indefinitely

            tl4.play();
            /////////////////////////////

            TranslateTransition tt6 = new TranslateTransition(Duration.seconds(5), ballons211);
            tt6.setByY(-1000); // move the photo 200 pixels to the right

            // Create a Timeline to play the animation
            Timeline tl5 = new Timeline(new KeyFrame(Duration.ZERO), new KeyFrame(Duration.seconds(0.5), e -> {
                tt6.play();
            }));
            tl5.setCycleCount(Timeline.INDEFINITE); // repeat the animation indefinitely

            tl5.play();
            ////////////////////////////////////////////////////////////////


        } else {
            Image tryAgainImage = new Image("robotsWin.jpg");
            myImageView.setImage(tryAgainImage);
            // Set the image of the ImageView to the try again image
            labelImage.setText("Oops! The robots took over :( \nYou need to \ncollect more tokens");
           
        }

        
    }


    @FXML
    public void ButtonBack(ActionEvent event) throws Exception {
        URL url = new File("src/start.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene LogInPage = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(LogInPage);
        stage.show();
    }
}