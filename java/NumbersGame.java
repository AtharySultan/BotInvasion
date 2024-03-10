

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


    public class NumbersGame extends Application {

        private static final String[] WORDS = new String[] {
                "apple",
                "orange",
                "computer",
                "screen",
                "keyboard",
                "class",
                "file",
                "module",
                "record",
                "pattern"
        };

        private Text word = new Text();
        private TextField userInput = new TextField();
        private ImageView image;
        
        private Text play = new Text("1-a   2-b    3-c    4-d   "
                + " 5-e    6-f    7-g    8-h    9-i    10-j\n11-k "
                + "   12-l    13-m    14-n    15-o    16-p    "
                + "17-q     18-r\n19-s    20-t    21-u    22-v    "
                + "23-w    24-x    25-y    26-z");

        @Override
        public void start(Stage stage) throws Exception {
            Scene scene=new Scene(createContent(),544,361);
            scene.setFill(Color.AQUAMARINE);
            stage.setScene(scene);
            stage.show();
        }

        public Parent createContent() {
            Image image1 = new Image("video7.gif");
            image = new ImageView(image1);
            image.setFitHeight(200);
            image.setFitWidth(250);
            nextWord();

            word.setFont(Font.font(20));
            userInput.setFont(Font.font(20));
            userInput.setOnAction(e -> guess(userInput.getText()));

            VBox box1 = new VBox(image);
            box1.setAlignment(Pos.TOP_LEFT);
            VBox box = new VBox(20,play,box1,word, userInput);
            box.setPrefSize(50, 50);
            box.setAlignment(Pos.CENTER);

            return box;
        }

        private void nextWord() {
            String randomWord = WORDS[new Random().nextInt(WORDS.length)];

            String randomWordInNumbers = "";

            for (char c : randomWord.toCharArray()) {
                randomWordInNumbers += charToInt(c) + "_";
            }

            word.setText(randomWordInNumbers.substring(0, randomWordInNumbers.length() - 1));
            word.getProperties().put("word", randomWord);
        }

        private int charToInt(char c) {
            // a - 97
            // b - 98, and so on
            int i = c;

            return i - 96;
        }

        private void guess(String text) {
            userInput.clear();

            String actualWord = (String) word.getProperties().get("word");

            if (text.equalsIgnoreCase(actualWord)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations");
                alert.setHeaderText("Congratulations, you found the last number in the code.");
                alert.setContentText("You won!");
                ImageView imageView = new ImageView("77.gif");
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                alert.getDialogPane().setGraphic(imageView);
                ButtonType okButton = new ButtonType("close");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Your solution is wrong, please try again.");
                alert.setContentText("wrong solution");
                ImageView imageView = new ImageView("84.gif");
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                alert.getDialogPane().setGraphic(imageView);
                ButtonType okButton = new ButtonType("close");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
        }


    }


