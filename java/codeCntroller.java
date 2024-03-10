
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.stage.Stage;

public class codeCntroller {

    @FXML
    private PasswordField MSG;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label CLEAR;

    private long firstnum = 0;
    private boolean isdelet = false;
        @FXML
    private Button btn1;
    static boolean puzzle = false;
    @FXML
    private void digitclick(ActionEvent event) {
        MSG.setText(MSG.getText() + ((Button) event.getSource()).getText());
    }

    @FXML
    private void checkbutton(ActionEvent event) throws MalformedURLException, IOException {
        if (MSG.getText().equals("351")) {

            Stage stage = (Stage) btn1.getScene().getWindow();
            stage.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("level comleted");
            alert.setHeaderText("WOW,level Completed, it's good.");
            alert.setContentText("You won in this level !");
            puzzle = true;
            ImageView imageView = new ImageView("OH.gif");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            alert.getDialogPane().setGraphic(imageView);
            ButtonType okButton = new ButtonType("ok");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
            

            

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("level not comleted");
            alert.setHeaderText("sorry,level not Completed,please try again!");
            alert.setContentText("You lost in this level !");
            ImageView imageView = new ImageView(new File("src/video7 (1).gif").toURI().toURL().toExternalForm());
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            alert.getDialogPane().setGraphic(imageView);
            ButtonType okButton = new ButtonType("ok");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();

        }

    }

    @FXML
    private void cle(ActionEvent event) {
        MSG.setText(" ");
    }

}
