
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
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import java.util.regex.*;

public class signupController {

    @FXML
    private Label welcomeText;
    @FXML
    private Button cancelButton;
    @FXML
    private Label registerMassageLabel;
    @FXML
    private TextField usernameT;
    @FXML
    private TextField PASS;
    @FXML
    private Label registerB;
    @FXML
    private ImageView cancel;
    @FXML
    private ImageView reg;
    @FXML
    private MediaView mediaView1;
    @FXML
    private TextField emailField;
    private MediaPlayer mediaPlayer2;

    public void scaleUp1(ImageView b) {
        b.setScaleX(b.getScaleX() - 0.1);
        b.setScaleY(b.getScaleY() - 0.1);
    }

    public void scaleDown1(ImageView b) {
        b.setScaleX(b.getScaleX() + 0.1);
        b.setScaleY(b.getScaleY() + 0.1);

    }

    public void regestierButtonAction(ActionEvent e) {
        registerMassageLabel.setText("user registered successfully!");
    }
    
    public boolean isValidEmail(String email) {
    String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}

    public void signupButtonOnAction1(ActionEvent e) {

        // end else
        if (!usernameT.getText().isEmpty() && !PASS.getText().isEmpty()) {
            
              // Check if password is at least 8 characters long
        if (PASS.getText().length() < 8) {
            registerB.setText("Password must be at least 8 characters long.");
            return; 
        }

        // Check if email is valid
        if (!isValidEmail(emailField.getText())) {
            registerB.setText("Invalid email address.");
            return; 
        }

            USER_INFO.uListSetter();
            
            boolean sim = false;
            for (int i = 0; i < USER_INFO.uList.size(); i++) {
                if (usernameT.getText().equals(USER_INFO.uList.get(i).getUserName())) {
                    sim = true;;
                    break;
                }
            }
            if (sim) {

                registerB.setText("Username already exists");
                
            } else {
                USER_INFO user1 = new USER_INFO(usernameT.getText(), emailField.getText(), PASS.getText());
                SCORE score1 = new SCORE(user1.getUserID());

                Session session = HibernateUtil.getSessionFactory().openSession();
                session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                int uId = (Integer) session.save(user1);
                int us = (Integer) session.save(score1);
                tx.commit();
                session.close();

                USER_INFO.uList.add(user1);

                registerB.setText("user registered successfully!");
            }
            
        } //end if
        
        else {
            registerB.setText("Please enter username and password .");
        }

    } // end method

    @FXML
    public void logupButtonOnAction(ActionEvent e) throws IOException {

        Parent r1 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(r1);
        stage.setScene(scene2);
        stage.show();

    }

    @FXML
    public void cancelEnterMouse(MouseEvent e) {
        scaleUp1(cancel);
    }

    @FXML
    public void cancelExitMouse(MouseEvent e) {
        scaleDown1(cancel);
    }

    @FXML
    public void regEnterMouse(MouseEvent e) {
        scaleUp1(reg);
    }

    @FXML
    public void regExitMouse(MouseEvent e) {
        scaleDown1(reg);
    }

}
