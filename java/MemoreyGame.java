
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class MemoreyGame {
    private int turns = 0;
    private int points = 0;
    private final int boardLength = 4;
    private final int boardSize = 9;
    private final Random random = new Random();
    private final ArrayList<String> memoryBoard = new ArrayList(Arrays.asList("", "", "", "", "", "", "", ""));
    private final ArrayList<String> memoryOptions = new ArrayList(Arrays.asList("a", "a", "b", "b", "c", "c", "d", "d"));

    public MemoreyGame() {
    }

    public void setupGame() {
        this.setupMemoryBoard();
        System.out.println(this.memoryBoard);
    }

    public String getOptionAtIndex(int index) {
        return (String)this.memoryBoard.get(index);
    }

    private void setupMemoryBoard() {
        for(int i = 0; i < 8; ++i) {
            String memoryOption = (String)this.memoryOptions.get(i);

            int position;
            for(position = this.random.nextInt(8); !Objects.equals(this.memoryBoard.get(position), ""); position = this.random.nextInt(8)) {
            }

            this.memoryBoard.set(position, memoryOption);
        }

    }

    public boolean checkTwoPositions(int firstIndex, int secondIndex) {
        if (((String)this.memoryBoard.get(firstIndex)).equals(this.memoryBoard.get(secondIndex))) {
            this.points++;
            if (this.points == this.boardLength) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations");
                alert.setHeaderText("Congratulations, you found the second number in the code.");
                alert.setContentText("You won!");
                ImageView imageView = new ImageView("vid (2).gif");
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                alert.getDialogPane().setGraphic(imageView);
                ButtonType okButton = new ButtonType("close");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
            return true;
        }
        return false;
    }
    }


