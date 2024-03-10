
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MemoreygameController implements Initializable {
        ArrayList<Button> buttons = new ArrayList();
        MemoreyGame memoryGame = new MemoreyGame();
        @FXML
        private Button button0;
        @FXML
        private Button button1;
        @FXML
        private Button button2;
        @FXML
        private Button button3;
        @FXML
        private Button button4;
        @FXML
        private Button button5;
        @FXML
        private Button button6;
        @FXML
        private Button button7;
        Timeline timeline = new Timeline(new KeyFrame[]{new KeyFrame(Duration.seconds(1.5), (e) -> {
            this.hideButtons();
        }, new KeyValue[0])});
        private boolean firstButtonClicked = false;
        private int firstButtonIndex;
        private int secondButtonIndex;
        private boolean match;
    private int points;
    private int boardLength;

    public MemoreygameController() {
        }

        public void initialize(URL url, ResourceBundle resourceBundle) {
            this.memoryGame.setupGame();
            this.buttons.addAll(Arrays.asList(this.button0, this.button1, this.button2, this.button3, this.button4, this.button5, this.button6, this.button7));
        }

        @FXML
        void buttonClicked(ActionEvent event) {
            String buttonId;
            if (!this.firstButtonClicked) {
                if (!this.match) {
                    this.hideButtons();
                    this.timeline.stop();
                }

                this.match = false;
                this.firstButtonClicked = true;
                buttonId = ((Control)event.getSource()).getId();
                this.firstButtonIndex = Integer.parseInt(buttonId.substring(buttonId.length() - 1));
                ((Button)this.buttons.get(this.firstButtonIndex)).setText(this.memoryGame.getOptionAtIndex(this.firstButtonIndex));
            } else {
                buttonId = ((Control)event.getSource()).getId();
                this.secondButtonIndex = Integer.parseInt(buttonId.substring(buttonId.length() - 1));
                ((Button)this.buttons.get(this.secondButtonIndex)).setText(this.memoryGame.getOptionAtIndex(this.secondButtonIndex));
                this.firstButtonClicked = false;
                if (this.memoryGame.checkTwoPositions(this.firstButtonIndex, this.secondButtonIndex)) {
                    System.out.println("Match");
                    this.match = true;



                } else {
                    this.timeline.play();
                }
            }
        }

        private void hideButtons() {
            ((Button)this.buttons.get(this.firstButtonIndex)).setText("");
            ((Button)this.buttons.get(this.secondButtonIndex)).setText("");
        }


}
