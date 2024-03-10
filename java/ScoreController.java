
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author athary
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

public class ScoreController implements Initializable {

    @FXML
    private Label LevelLabel;

    @FXML
    private Label NameLabel;

    @FXML
    private Label ScoreLabel;

    @FXML
    private Label TokenLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<SCORE> sList = null;
        String queryStr = "from SCORE";
        Query query = session.createQuery(queryStr);
        sList = query.list();
        session.close();

        double tkns = ((double) sList.get(USER_INFO.getCurUserIndex() - 1).getCollectedTokens()) / 10 * 100;
        double lvls = ((double) sList.get(USER_INFO.getCurUserIndex() - 1).getLevelID()) / 3 * 100;
        double all = (tkns + lvls) / 2;

        NameLabel.setText("Name: " + USER_INFO.uList.get(USER_INFO.getCurUserIndex() - 1).getUserName());
        TokenLabel.setText("" + sList.get(USER_INFO.getCurUserIndex() - 1).getCollectedTokens());
        LevelLabel.setText("" + sList.get(USER_INFO.getCurUserIndex() - 1).getLevelID());

        ScoreLabel.setText(String.format("%.2f%%", all));

    }

    @FXML
    public void ButtonBack(ActionEvent event) throws Exception {
        URL url = new File("src/FXML3.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene LogInPage = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(LogInPage);
        stage.show();

    }

}
