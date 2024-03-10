import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javafx.collections.FXCollections;


public class Level1Controller implements Initializable {
    private Media Tokensound;
    private Media levelSound2;
    private Media MouseClick;
    private Media buttonSound;
    private Media puzzleSound;

    private int counter=0;

    private TextField nineteen = new TextField();
    private TextField three = new TextField();
    private TextField two = new TextField();
    private Alert computerAlert = new Alert(AlertType.INFORMATION);

    private PasswordField code=new PasswordField();

    @FXML
    private Label mylabe;
    private int counter2=0;
    boolean flag1 = false, flag2 = false, flag3;

    @FXML
    private ImageView Token1;
    @FXML
    private ImageView Token2;
    @FXML
    private ImageView Token3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Tokensound = new Media(new File("src/sound3.mp3").toURI().toURL().toExternalForm());
            levelSound2 = new Media(new File("src/sound3.mp3").toURI().toURL().toExternalForm());
            MouseClick = new Media(new File("src/Mouse-Click.mp3").toURI().toURL().toExternalForm());
            buttonSound = new Media(new File("src/buttonSound.wav").toURI().toURL().toExternalForm());
            puzzleSound = new Media(new File("src/sound4.mp3").toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Level1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void ComputerButton(ActionEvent event) {
        ButtonType showAlertButton = new ButtonType("cancel");
        computerAlert.getButtonTypes().setAll(showAlertButton);
    String nineteenValue = "";
    String threeValue = "";
    String twoValue = "";
    StackPane stackPane = new StackPane(); //root
    Pane pane = new Pane(); //pane for alert window
    //desktop image
    Image image = new Image("computerImage.png");
    ImageView imageView1 = new ImageView(image);
    imageView1.setFitHeight(600);
    imageView1.setFitWidth(1000);
    //code image
    Image image1 = new Image("solve.jpg");
    ImageView imageView2 = new ImageView(image1);
    imageView2.setFitHeight(510);
    imageView2.setFitWidth(660);
    imageView2.setTranslateX(-5);
    imageView2.setTranslateY(0);

    nineteen.setPrefSize(30,15);
    nineteen.setTranslateX(430);
    nineteen.setTranslateY(275);
    // to limit the user from using alphabet
    nineteen.setTextFormatter(new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*")) {
            return change;        }
        else {
            return null;
        }
    }));
    three.setPrefSize(30,15);
    three.setTranslateX(520);
    three.setTranslateY(275);
    // to limit the user from using alphabet
    three.setTextFormatter(new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*")) {
            return change;        }
        else {
            return null;
        }
    }));
    two.setPrefSize(30,15);
    two.setTranslateX(600);
    two.setTranslateY(275);
    // to limit the user from using alphabet
    two.setTextFormatter(new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*")) {
            return change;        }
        else {
            return null;
        }
    }));
    //getting the data from the text filed
    nineteenValue = nineteen.getText();
    threeValue = three.getText();
    twoValue = two.getText();

    nineteen.setText(nineteenValue);
    three.setText(threeValue);
    two.setText(twoValue);


    Button run = new Button("RUN ▶");
        run.setTranslateX(685);
        run.setTranslateY(65);
        run.setPrefWidth(70);
        run.setPrefHeight(20);

    run.setOnAction(e -> calculate());

    pane.getChildren().addAll(nineteen,three,two,run);
    stackPane.getChildren().addAll(imageView1,imageView2,pane);
    computerAlert.setGraphic(stackPane);
    computerAlert.setHeaderText(" ");
    computerAlert.showAndWait();
}
//a method that calculates the input from the user
    private void calculate() {
         MediaPlayer puzzleSoundPlayer = new MediaPlayer(puzzleSound);
    try {
        double value1 = Double.parseDouble(nineteen.getText());
        double value2 = Double.parseDouble(three.getText());
        double value3 = Double.parseDouble(two.getText());
        double result1 = (value1 - value2) / value3;
        if (result1 == 8) {
            Alert correct = new Alert(AlertType.INFORMATION);
            correct.setTitle("Correct!!");
            puzzleSoundPlayer.play();
            correct.getDialogPane().setStyle("-fx-background-image: url(puzCom.gif);"
                + "-fx-background-size: 100px 70px;-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: left bottom;");

            correct.setHeaderText(("PUZZLE COMPLETED\nThe solution to the equation is: " + String.format("%.2f", result1)+
                    "\nPay attention to the solution  number ;)"));
            correct.showAndWait();
        }
        else {
            Alert wrong = new Alert(AlertType.INFORMATION);
            wrong.setTitle("Incorrect :(");
            wrong.setHeaderText(("The solution should multiples of 2, \n use 19,2,3 numbers only"));
            wrong.showAndWait();
            nineteen.clear();
            three.clear();
            two.clear();
        }
    }
    catch (NumberFormatException ex) {
        computerAlert.setContentText("Invalid input");
        }
}


    public void FilesButton(ActionEvent event) {
        Alert FileAlert = new Alert(AlertType.CONFIRMATION);
        MediaPlayer puzzleSoundPlayer = new MediaPlayer(puzzleSound);
        DialogPane dialogPane=FileAlert.getDialogPane();
        dialogPane.setStyle("-fx-font-size:10px;-fx-font-family:verdana;");
        FileAlert.setHeaderText("4 9 1\nHINT:\nI=1 ** II=2,\nIV=4**IX=9");

        Button B1=new Button("check");
        B1.setTranslateX(150);
        B1.setTranslateY(120);
        Image image = new Image("check.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        B1.setGraphic(imageView);

        String ComboNumber[] = { "II", "IV", "I", "IX"};
        ComboBox<String> ComboBox = new ComboBox<>();
        ComboBox.setEditable(true);
        ComboBox.setPrefWidth(30);
        ComboBox.getItems().addAll(ComboNumber);

        String ComboNumber2[] = { "I", "II", "IX", "VI"};
        ComboBox<String> ComboBox2 = new ComboBox<>();
        ComboBox2.setEditable(true);
        ComboBox2.setPrefWidth(30);
        ComboBox2.getItems().addAll(ComboNumber2);

        String ComboNumber3[] = { "II", "IV", "I", "IX"};
        ComboBox<String> ComboBox3 = new ComboBox<>();
        ComboBox3.setEditable(true);
        ComboBox3.setPrefWidth(30);
        ComboBox3.getItems().addAll(ComboNumber3);

        ComboBox.setEditable(false);
        ComboBox2.setEditable(false);
        ComboBox3.setEditable(false);



        StackPane stackPane=new StackPane();

        Image image1=new Image("https://img.freepik.com/premium-photo/white-art-paper-background_35956-2379.jpg?w=2000");
        ImageView imageV1=new ImageView(image1);
        imageV1.setFitHeight(400);
        imageV1.setFitWidth(400);

        Label label1 = new Label("IV  IX   VII  IX");
        Label label2 = new Label("I  IV  IX  III  II");
        Label label3 = new Label("VI   VII   II  ");
        Label label4 = new Label("IX   VII   IX   IV");

        label1.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));
        label2.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));
        label3.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));
        label4.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));

        Label num = new Label("II");
        num.setTextFill(Color.web("#B00E0E"));

        Label lab = new Label("VI   VII   II ");
        Label lab1 = new Label("IV  IX   VII  IX");
        Label lab2 = new Label("IX   VII  IX   ");
        Label lab3 = new Label("I  IV  IX  III ");

        lab.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));
        lab1.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));
        lab2.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));
        lab3.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));
        num.setFont((Font.font("verdana",FontWeight.BLACK,FontPosture.ITALIC,15)));

        HBox root=new HBox(40);
        VBox v1=new VBox(40);
        HBox h1=new HBox(5);
        h1.getChildren().addAll(lab,ComboBox);
        HBox h2=new HBox(5);
        h2.getChildren().addAll(lab1);
        HBox h3=new HBox(5);
        h3.getChildren().addAll(lab2,num);
        HBox h4=new HBox(5);
        h4.getChildren().addAll(lab3,ComboBox3);
        v1.getChildren().addAll(h1,h2,h3,h4);


        VBox v2=new VBox(40);
        HBox h21=new HBox(5);
        h21.getChildren().addAll(label1);
        HBox h22=new HBox(5);
        h22.getChildren().addAll(label2);
        HBox h23=new HBox(5);
        h23.getChildren().addAll(label3,ComboBox2);
        HBox h24=new HBox(5);//
        h24.getChildren().addAll(label4);

        v2.getChildren().addAll(h21,h22,h23,h24);


        root.getChildren().addAll(v1,v2);
        root.setTranslateX(30);
        root.setTranslateY(70);
        ButtonType showAlertButton = new ButtonType("cancel");
        FileAlert.getButtonTypes().setAll(showAlertButton);


        stackPane.getChildren().addAll(imageV1,root,B1);
        FileAlert.setGraphic(stackPane);


        ComboBox.setOnAction(e -> {
            FileAlert.setContentText(" ");
            String selectedValue = ComboBox.getValue();
            if (selectedValue.equals("IV")) {
                FileAlert.setContentText("Correct!!");
            } else {
                FileAlert.setContentText("InCorrect!!");
            }
        });

           ComboBox2.setOnAction(e -> {
               FileAlert.setContentText(" ");
            String selectedValue = ComboBox2.getValue();
            if (selectedValue.equals("IX")) {
                FileAlert.setContentText("Correct!!");

            } else {
                FileAlert.setContentText("InCorrect!!");
            }
        });

        ComboBox3.setOnAction(e -> {
          FileAlert.setContentText(" ");
            String selectedValue = ComboBox3.getValue();
            if (selectedValue.equals("I")) {
                FileAlert.setContentText("Correct!!");
            } else {
                FileAlert.setContentText("InCorrect!!");
            }
        });

    B1.setOnAction(e->{
    if (ComboBox.getValue() == "IV" && ComboBox2.getValue() == "IX" && ComboBox3.getValue() == "I") {
        FileAlert.setContentText("PUZZLE COMPLETED, pay attention to II");

        FileAlert.getDialogPane().setStyle("-fx-background-image: url(puzCom.gif);"
                + "-fx-background-size: 100px 70px;-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: center bottom;");
        puzzleSoundPlayer.play();

        ComboBox.setDisable(true);
        ComboBox2.setDisable(true);
        ComboBox3.setDisable(true);
    }
     });

FileAlert.showAndWait();
     }

    public void soundFORButtons(){
        MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
        mediaPlayer.play();
    }

    //board buttons
    public void boardButtons(ActionEvent event) {
        Alert boardAlert = new Alert(AlertType.INFORMATION);

        boardAlert.setHeaderText(" ");

        Label code = new Label("0110");
        code.setFont((Font.font("verdana",FontWeight.MEDIUM,FontPosture.ITALIC,50)));
        code.setTranslateY(200);

        StackPane stackPane = new StackPane();
        Image image = new Image("board0.jpg");
        ImageView iimgge = new ImageView(image);
        iimgge.setFitHeight(500);
        iimgge.setFitWidth(500);

        stackPane.getChildren().addAll(iimgge,code);
        boardAlert.setGraphic(stackPane);


        boardAlert.showAndWait();
      }
    public void boardButton1(ActionEvent event) {
        Alert boardAlert1 = new Alert(AlertType.INFORMATION);
        boardAlert1.setHeaderText(" ");



        StackPane stackPane = new StackPane();
        Image image = new Image("board1.jpg");
        ImageView iimgge = new ImageView(image);
        iimgge.setFitHeight(500);
        iimgge.setFitWidth(500);

        stackPane.getChildren().addAll(iimgge);
        boardAlert1.setGraphic(stackPane);


        boardAlert1.showAndWait();
      }
    public void boardButton2(ActionEvent event) {
        Alert boardAlert2 = new Alert(AlertType.INFORMATION);
        boardAlert2.setHeaderText(" ");




        StackPane stackPane = new StackPane();
        Image image = new Image("board2.jpg");
        ImageView iimgge = new ImageView(image);
        iimgge.setFitHeight(500);
        iimgge.setFitWidth(500);

        stackPane.getChildren().addAll(iimgge);
        boardAlert2.setGraphic(stackPane);


        boardAlert2.showAndWait();
      }
    public void boardButton3(ActionEvent event) {
        Alert boardAlert3 = new Alert(AlertType.INFORMATION);
        boardAlert3.setHeaderText(" ");

        Label code = new Label("Nothing\nIntrersting..");

        code.setTextFill(Color.web("#9C1212"));
        code.setFont((Font.font("Comic Sans MS",FontWeight.MEDIUM,FontPosture.ITALIC,20)));
        code.setTranslateX(180);
        code.setTranslateY(60);

        StackPane stackPane = new StackPane();
        Image image = new Image("board3.jpg");
        ImageView iimgge = new ImageView(image);
        iimgge.setFitHeight(500);
        iimgge.setFitWidth(500);

        stackPane.getChildren().addAll(iimgge,code);
        boardAlert3.setGraphic(stackPane);


        boardAlert3.showAndWait();
      }



    //tokens
    public void Token1(MouseEvent event) {

   MediaPlayer mediaPlayer = new MediaPlayer(Tokensound);
       Token1.setVisible(false);
       mediaPlayer.play();
       if (!flag1) {
        flag1 = true;
            counter2++;
            mylabe.setText(String.valueOf(counter2));

        }
   }
    public void Token2(MouseEvent event) {
   MediaPlayer mediaPlayer = new MediaPlayer(Tokensound);
       Token2.setVisible(false);
       mediaPlayer.play();
       if (!flag2) {
        flag2 = true;
            counter2++;
            mylabe.setText(String.valueOf(counter2));
        }
   }
    public void Token3(MouseEvent event) {
   MediaPlayer mediaPlayer = new MediaPlayer(Tokensound);
       Token3.setVisible(false);
       mediaPlayer.play();
       if (!flag3) {
        flag3 = true;
            counter2++;
            mylabe.setText(String.valueOf(counter2));


        }
   }

    public void backgroundSound(MouseEvent event) {
    MediaPlayer mediaPlayer = new MediaPlayer(MouseClick);
       mediaPlayer.play();

   }

    public void password(MouseEvent event) {

        String passText = "";
        StackPane rootAlert1 = new StackPane();

        Alert AlertPass = new Alert(AlertType.INFORMATION);
        AlertPass.setHeaderText(" ");
        ButtonType showAlertButton = new ButtonType("cancel");
        AlertPass.getButtonTypes().setAll(showAlertButton);

        Button button0 = new Button("0");
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button buttonEnter = new Button("ENTER");
        Button buttonDEL = new Button("DELETE");



        Label labelBind = new Label();
        code.setPromptText("***");
        labelBind.textProperty().bind(code.textProperty());

        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> list5 = FXCollections.observableArrayList("8", "6", "2");

        Image imageAlert1 = new Image("backPass.gif");
        ImageView imvAlert1 = new ImageView(imageAlert1);
        imvAlert1.setFitHeight(200);
        imvAlert1.setFitWidth(300);

        Label Passlabel =new Label("ENTER PASSWORD");
        Passlabel.setTextFill(Color.web("#ffffff"));

        VBox ButtonRoot=new VBox(5);

        HBox hbox3 = new HBox(5);
        hbox3.getChildren().addAll(code,buttonEnter);


        HBox hbox1 = new HBox(7);
        hbox1.getChildren().addAll(button1,button2,button3,button4,button5,buttonDEL);

        HBox hbox2 = new HBox(7);
        hbox2.getChildren().addAll(button6,button7,button8,button9,button0);

        ButtonRoot.getChildren().addAll(Passlabel,hbox3,hbox1,hbox2);
        ButtonRoot.setAlignment(Pos.CENTER_LEFT);

        rootAlert1.getChildren().addAll(imvAlert1,ButtonRoot);
        AlertPass.setGraphic(rootAlert1);

        passText = code.getText();
        code.setText(passText);

        button1.setOnAction(e -> {
        if(counter<3) {
        list.add(button1.getText());
        code.appendText(button1.getText());
        counter++;
        }

     });
        button2.setOnAction(e -> {
            if(counter<3) {
        list.add(button2.getText());
        code.appendText(button2.getText());
         counter++;
            }
     });
        button3.setOnAction(e -> {
            if(counter<3) {
        list.add(button3.getText());
        code.appendText(button3.getText());
         counter++;
            }
     });
        button4.setOnAction(e -> {
            if(counter<3) {
        list.add(button4.getText());
        code.appendText(button4.getText());
             counter++;
        }

     });
        button5.setOnAction(e -> {
            if(counter<3) {
        list.add(button5.getText());
        code.appendText(button5.getText());
         counter++;
        }

     });
        button6.setOnAction(e -> {
            if(counter<3) {
          list.add(button6.getText());
        code.appendText(button6.getText());
         counter++;
        }

     });
        button7.setOnAction(e -> {
            if(counter<3) {
            list.add(button7.getText());
            code.appendText(button7.getText());
             counter++;
        }

     });
        button8.setOnAction(e -> {
            if(counter<3) {
        list.add(button8.getText());
        code.appendText(button8.getText());
        counter++;
        }

     });
        button9.setOnAction(e -> {
            if(counter<3) {
        list.add(button9.getText());
        code.appendText(button9.getText());
        counter++;
        }

     });
        button0.setOnAction(e -> {
        if(counter<3) {
        list.add(button0.getText());
        code.appendText(button0.getText());
         counter++;
        }

     });

        buttonDEL.setOnAction(e -> {

        list.clear();
        code.setText("");
        counter=0;


     });

        buttonEnter.setOnAction(e -> {
        if(list5.equals(list)) {
        PassSuccess();
        AlertPass.close();
			////////////////////////////////////
                    //THIS CODE 
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                                        
                    LEVELS tknUpdate = null;
                    tknUpdate = (LEVELS) session.get(LEVELS.class, 1); //1 = levelID (primary key)
                    tknUpdate.setCollectedTKNs(counter);
                    session.update(tknUpdate);
                    tx.commit();
                    session.close();
                    
                    
                    
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    
                    SCORE uScore = null;
                    uScore = (SCORE) session.get(SCORE.class, USER_INFO.curUserIndex); 
                    uScore.setLevelID(1);
                    uScore.setCollectedTokens(uScore.getCollectedTokens()+counter);
                    uScore.setCompletedLvl(uScore.getCompletedLvl()+1);
                    session.update(uScore);
                    
                    tx.commit();
                    session.close();
                    
                    ////////////////////////////////////
			try {
                             
				URL url;
				url = new File("src/FXML3.fxml").toURI().toURL();
				Parent root1;
				root1 = FXMLLoader.load(url);
				Scene LogInPage = new Scene(root1);
				Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage1.setScene(LogInPage);
				stage1.show();
                               
                                
                               
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		
        counter=10;
        }
        else{
             AlertPass.setContentText("wrong password, try agine");
             list.clear();
             code.clear();
             counter=0;
        }
     });
        code.addEventFilter(KeyEvent.KEY_TYPED, e -> {
        e.consume();
        });
        AlertPass.showAndWait();

      }

    public void PassSuccess(){
        MediaPlayer mediaPlayer = new MediaPlayer(levelSound2);


        Alert alertSuccess = new Alert(AlertType.INFORMATION);
        alertSuccess.setHeaderText(" ");

        Label levelLabel=new Label("LEVEL 1");
        levelLabel.setStyle("-fx-background-color:#280669");
        levelLabel.setTextFill(Color.web("#62abff"));
        levelLabel.setFont((Font.font("Comic Sans MS", FontWeight.BOLD,FontPosture.ITALIC,20)));

        


        VBox root=new VBox(10);

        Text tokenLabel=new Text("");
        tokenLabel.setText("number of tokens:3/ "+String.valueOf(counter2));
        tokenLabel.setStroke(Color.WHITE);
        tokenLabel.setStrokeWidth(0.5);
        tokenLabel.setFill(Color.web("#62abff"));
        tokenLabel.setFont((Font.font("Comic Sans MS", FontWeight.MEDIUM,FontPosture.ITALIC,15)));

        Text comLabel=new Text("LEVEL COMPLETE");
        comLabel.setStroke(Color.WHITE);
        comLabel.setStrokeWidth(0.5);
        comLabel.setFill(Color.web("#7e3ad7"));
        comLabel.setFont((Font.font("Comic Sans MS", FontWeight.MEDIUM,FontPosture.ITALIC,20)));

        root.getChildren().addAll(comLabel,tokenLabel);
        root.setAlignment(Pos.CENTER);
        root.setTranslateY(30);


        BorderPane infoRoot=new BorderPane();
        infoRoot.setTop(levelLabel);
        infoRoot.setCenter(root);

        Image image = new Image("stars.jpg");
        ImageView iimgge = new ImageView(image);
        StackPane stackPane22 = new StackPane();
        iimgge.setFitHeight(300);
        iimgge.setFitWidth(400);

        stackPane22.getChildren().addAll(iimgge,infoRoot);
        alertSuccess.setGraphic(stackPane22);
        mediaPlayer.play();
        alertSuccess.showAndWait();


    }









}
