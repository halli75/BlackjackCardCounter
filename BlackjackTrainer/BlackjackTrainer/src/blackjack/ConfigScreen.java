// ConfigScreen.java
package blackjack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ConfigScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Blackjack Card Counting Trainer");
        Label decksLabel = new Label("Number of Decks (4–8):");
        Spinner<Integer> decksSpinner = new Spinner<>(4, 8, 6);

        Label playersLabel = new Label("Number of Players (1–8):");
        Spinner<Integer> playersSpinner = new Spinner<>(1, 8, 1);

        Label difficultyLabel = new Label("Difficulty:");
        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Medium");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            int decks = decksSpinner.getValue();
            int players = playersSpinner.getValue();
            String difficulty = difficultyBox.getValue();

            BlackjackTableUI gameUI = new BlackjackTableUI(primaryStage, decks, players, difficulty);
            gameUI.show();
        });


        VBox root = new VBox(15, titleLabel, decksLabel, decksSpinner, playersLabel, playersSpinner, difficultyLabel, difficultyBox, startButton);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Blackjack Trainer Setup");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
